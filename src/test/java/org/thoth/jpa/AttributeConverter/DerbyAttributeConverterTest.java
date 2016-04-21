package org.thoth.jpa.AttributeConverter;

import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.derby.iapi.services.io.FileUtil;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class DerbyAttributeConverterTest {

    private EntityManager em;

    @Before
    public void deleteFolderIfItExists() {
        // Delete the derby database folder if it exists already
        File databaseFolder = new File(".//target//junit//DerbyAttributeConverterTest//AttributeTable.db");
        if (databaseFolder.exists()) {
            FileUtil.removeDirectory(databaseFolder);
        }
    }
    
    @Before
    public void getTheEntityManager() {
        // Get the objects needed to work with JPA
        EntityManagerFactory emf 
            = Persistence.createEntityManagerFactory("DerbyAttributeConverterTestPu");
        em = emf.createEntityManager();        
    }
    
    @After
    public void closeEntityManager() {
        em.close();
    }

    @Test
    public void testToSeeIfABadConverterWillResultInBadDataInTheDatabase() {
        
        // Insert a new row into to the table.  Begin and end the 
        // transaction so the data gets committed.  
        {
            AttributeTable st = new AttributeTable();
            st.setName("xxx");
            assertEquals((Integer) null, st.getId());

            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(st);
            t.commit();
                
            assertEquals(new Integer(1), st.getId());
            assertEquals("xxx", st.getName());
        }
        
        
        // Next, clear the EntityManager cache so that I force getting the data
        // fresh from the database and the entity object recreated.  Search
        // for the data that I just inserted.  Verify the Converter on the
        // name property of the entity is indeed called and changes the value
        // coming out of the database.
        {
            em.clear();
            AttributeTable findId1
                = em.find(AttributeTable.class, new Integer(1));
            assertNotNull(findId1);
            assertEquals(new Integer(1), findId1.getId());
            
            // Converter should have updated the name attribute
            assertEquals("WoW: xxx", findId1.getName());  
        }
        
        
        // Next, clear the EntityManager cache so that I force getting the data
        // fresh from the database and the entity object recreated.  Search
        // for the data that I just inserted.  Update the value of a property
        // that does not have a converter on it.  Commit the changes.
        {
            em.clear();
            AttributeTable findId1
                = em.find(AttributeTable.class, new Integer(1));
            findId1.setAddress("123 any street");
            
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(findId1);
            t.commit();
        }
        
        
        // Again, clear the EntityManager cache so that I force getting the 
        // data from the database.  Check the value of the name property.  If
        // the bug manifests itself, then the value of the name property should
        // be "double padded" because in the previous step the altered data
        // in the entity was accidentally commited to the database because the
        // AttributeConverter is not coded properly
        {
            em.clear();
            AttributeTable findId1
                = em.find(AttributeTable.class, new Integer(1));
            assertNotNull(findId1);
            assertEquals("WoW: WoW: xxx", findId1.getName());
        }
    }

}
