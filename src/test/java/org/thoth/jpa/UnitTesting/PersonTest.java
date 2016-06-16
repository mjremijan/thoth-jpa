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
        AssertAnnotations.assertType(
            Person.class, new Class[]{Entity.class, Table.class});
    }


    @Test
    public void fieldAnnotations() {
        // assert
        AssertField.assertFields(Person.class, "id", "firstName", "lastName", "phones");
        AssertAnnotations.assertField(Person.class, "id");
        AssertAnnotations.assertField(Person.class, "firstName");
        AssertAnnotations.assertField(Person.class, "lastName");
        AssertAnnotations.assertField(Person.class, "phones");
    }


    @Test
    public void methodAnnotations() {
        // assert
        AssertAnnotations.assertMethod(Person.class, "getId", Id.class, GeneratedValue.class);
        AssertAnnotations.assertMethod(Person.class, "getFirstName", Column.class);
        AssertAnnotations.assertMethod(Person.class, "getLastName", Column.class);
        AssertAnnotations.assertMethod(Person.class, "getPhones", OneToMany.class);
    }


    @Test
    public void entity() {
        // setup
        Entity a
            = ReflectTool.getClassAnnotation(Person.class, Entity.class);

        // assert
        Assert.assertEquals("", a.name());
    }


    @Test
    public void table() {
        // setup
        Table t
            = ReflectTool.getClassAnnotation(Person.class, Table.class);

        // assert
        Assert.assertEquals("T_PERSON", t.name());
    }


    @Test
    public void id() {
        // setup
        GeneratedValue a
            = ReflectTool.getMethodAnnotation(Person.class, "getId", GeneratedValue.class);

        // assert
        Assert.assertEquals("", a.generator());
        Assert.assertEquals(GenerationType.AUTO, a.strategy());
    }


    @Test
    public void firstName() {
        // setup
        Column c
            = ReflectTool.getMethodAnnotation(Person.class, "getFirstName", Column.class);

        // assert
        Assert.assertEquals("FIRST_NAME", c.name());
    }


    @Test
    public void lastName() {
        // setup
        Column c
            = ReflectTool.getMethodAnnotation(Person.class, "getLastName", Column.class);

        // assert
        Assert.assertEquals("LAST_NAME", c.name());
    }


    @Test
    public void phones() {
        // setup
        OneToMany a
            = ReflectTool.getMethodAnnotation(Person.class, "getPhones", OneToMany.class);

        // assert
        Assert.assertEquals("person", a.mappedBy());
        Assert.assertEquals(FetchType.LAZY, a.fetch());
    }

}
