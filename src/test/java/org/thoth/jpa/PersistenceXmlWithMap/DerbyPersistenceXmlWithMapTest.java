package org.thoth.jpa.PersistenceXmlWithMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class DerbyPersistenceXmlWithMapTest {

    @Test
    public void autoCreateAndSelect() throws IOException {
        // This is where the database files will be located.
        File f =
            new File(String.format(".\\target\\junit\\DerbyPersistenceXmlWithMapTest\\BetaTable.db",
                            System.currentTimeMillis())).getCanonicalFile();

        // This is a Map of properties which do NOT exist in the persistence.xml file
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.connection.url", String.format("jdbc:derby:%s", f.getAbsolutePath()));

        // Simulate updating the map properties to create a new db
        {
            // Set the hbm2ddl property to create so JPA will create the database
            map.put("hibernate.hbm2ddl.auto", "create");

            // Update the "hibernate.connection.url" and append the create parameter
            map.put("hibernate.connection.url", String.format("%s;create=true", map.get("hibernate.connection.url")));
        }

        // Get EntityManagerFactory and EntityManager
        // This should initiate the creation of the DB
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("DerbyPersistenceXmlWithMapTestPu", map);
        EntityManager em = emf.createEntityManager();

        // Get all rows from Some_Table. Though the table will be empty, this test will
        // confirm the table was created because if not the query will fail.
        EntityTransaction t = em.getTransaction();
        t.begin();
        List<BetaTable> list = null;
        {
            Query q = em.createQuery("select s from BetaTable s", BetaTable.class);
            list = q.getResultList();
        }
        t.commit();
        em.close();
        emf.close();

        assertNotNull(list);
        assertEquals(0, list.size());
    }
}
