package org.thoth.jpa.UnitTesting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */

public class PersonTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    
    @Test
    public void typeAnnotations() {
        // assert
        AssertAnnotation.typeExpected(
            Person.class, new Class[]{Entity.class, Table.class});
    }
    
    
    @Test
    public void fieldAnnotations() {
        // setup
        Class[] none = new Class[]{};
        // assert
        AssertAnnotation.fieldExpected(Person.class, "id", none);
        AssertAnnotation.fieldExpected(Person.class, "firstName", none);
        AssertAnnotation.fieldExpected(Person.class, "lastName", none);
        AssertAnnotation.fieldExpected(Person.class, "phones", none);
    }
    
    @Test
    public void getterAnnotations() {
        // setup
        Class[] none = new Class[]{};
        // assert
        AssertAnnotation.getterExpected(Person.class, "getId", new Class[]{Id.class,GeneratedValue.class});
        AssertAnnotation.getterExpected(Person.class, "firstName", new Class[]{Column.class});
        AssertAnnotation.getterExpected(Person.class, "lastName", new Class[]{Column.class});
        AssertAnnotation.getterExpected(Person.class, "phones", new Class[]{OneToMany.class});
    }
    
}