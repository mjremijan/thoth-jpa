package org.thoth.jpa.UnitTesting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PersonTest {

    @Test
    public void typeAnnotations() {
        // assert
        AssertType.assertAnnotations(
            Person.class, new Class[]{Entity.class, Table.class});
    }

    @Test
    public void fieldAnnotations() {
        // setup
        Class[] none = new Class[]{};
        // assert
        AssertField.assertFields(Person.class, "id", "firstName", "lastName", "phones");
        AssertField.assertAnnotations(Person.class, "id", none);
        AssertField.assertAnnotations(Person.class, "firstName", none);
        AssertField.assertAnnotations(Person.class, "lastName", none);
        AssertField.assertAnnotations(Person.class, "phones", none);
    }

    @Test
    public void getterAnnotations() {
        // assert
        AssertMethod.assertAnnotations(Person.class, "getId", new Class[]{Id.class, GeneratedValue.class});
        AssertMethod.assertAnnotations(Person.class, "getFirstName", new Class[]{Column.class});
        AssertMethod.assertAnnotations(Person.class, "getLastName", new Class[]{Column.class});
        AssertMethod.assertAnnotations(Person.class, "getPhones", new Class[]{OneToMany.class});
    }
    
    
    @Test
    public void entity() {
        // setup
        Entity a
            = TypeTool.getAnnotation(Person.class, Entity.class);

        // assert
        Assert.assertEquals("", a.name());
    }
    
    @Test
    public void table() {
        // setup
        Table t
            = TypeTool.getAnnotation(Person.class, Table.class);

        // assert
        Assert.assertEquals("T_PERSON", t.name());
    }
    
    @Test
    public void id() {
        // setup
        GeneratedValue a
            = MethodTool.getAnnotation(Person.class, "getId", GeneratedValue.class);
        
        // assert
        Assert.assertEquals("", a.generator());
        Assert.assertEquals(GenerationType.AUTO, a.strategy());
    }
    
    @Test
    public void firstName() {
        // setup
        Column c
            = MethodTool.getAnnotation(Person.class, "getFirstName", Column.class);

        // assert
        Assert.assertEquals("FIRST_NAME", c.name());
    }
    
    @Test
    public void lastName() {
        // setup
        Column c
            = MethodTool.getAnnotation(Person.class, "getLastName", Column.class);

        // assert
        Assert.assertEquals("LAST_NAME", c.name());
    }
    
    @Test
    public void phones() {
        // setup
        OneToMany a
            = MethodTool.getAnnotation(Person.class, "getPhones", OneToMany.class);

        // assert
        Assert.assertEquals("person", a.mappedBy());
        Assert.assertEquals(FetchType.LAZY, a.fetch());
    }
    
}
