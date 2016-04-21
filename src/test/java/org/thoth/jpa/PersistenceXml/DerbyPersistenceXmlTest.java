package org.thoth.jpa.PersistenceXml;

import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.derby.iapi.services.io.FileUtil;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DerbyPersistenceXmlTest {

    private File databaseFolder;

    @Before
    public void before() {
        databaseFolder = new File(".//target//junit//DerbyPersistenceXmlTest//AlphaTable.db");
        if (databaseFolder.exists()) {
            FileUtil.removeDirectory(databaseFolder);
        }
    }

    @Test
    public void createDatabaseAndInsert() {

        // Get all the objects needed to work with JPA
        EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("DerbyPersistenceXmlTestPu");
        EntityManager em
            = emf.createEntityManager();
        EntityTransaction t
            = em.getTransaction();

        // Insert a new row into to the table.  Begin and end the 
        // transaction so the data gets committed.  
        {
            AlphaTable st = new AlphaTable();
            st.setName("alpha name");
            assertEquals((Integer) null, st.getId());

            t.begin();
            em.persist(st);
            t.commit();

            assertEquals(new Integer(1), st.getId());
            assertEquals("alpha name", st.getName());
        }

        em.close();
        emf.close();
    }
}
