package org.thoth.jpa.UnitTesting;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.junit.Assert;
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
    
    public PersonTest() {
    }

    @Test
    public void properties() {
        // setup
        List<Field> attributes = Arrays.asList(Person.class.getDeclaredFields());
        
        // action
        List<String> fieldNames 
            = attributes.stream().map(f -> f.getName()).collect(Collectors.toList());
        
        // assert
        Assert.assertEquals("Entity no longer has expected number of properties", 4, attributes.size());        
        Assert.assertTrue(fieldNames.contains("id"));
        Assert.assertTrue(fieldNames.contains("firstName"));
        Assert.assertTrue(fieldNames.contains("lastName"));
        Assert.assertTrue(fieldNames.contains("phones"));
    }
    
    @Test
    public void id() throws NoSuchMethodException {
        methodExpects("getId", Id.class, GeneratedValue.class);
    }
    
    @Test
    public void firstName() throws NoSuchMethodException {
        Method method
            = methodExpects("getFirstName", Column.class);
        Assert.assertEquals(
              "FIRST_NAME", method.getDeclaredAnnotation(Column.class).name()
        );
        
    }
    
    
    protected Method methodExpects(String methodName, Class...expectedAnnotationClasses) throws NoSuchMethodException  {
        // setup
        Method method 
            = Person.class.getDeclaredMethod(methodName);
        Annotation [] annotations 
            = method.getAnnotations();
        
        // assert
        Assert.assertEquals(
              String.format(
                    "[For method \"%s\", expected %d annotations but found %d]"
                  , methodName
                  , expectedAnnotationClasses.length
                  , annotations.length)
            , expectedAnnotationClasses.length, annotations.length);
        
        for (Class expectedAnnotationClass : expectedAnnotationClasses) {
            Annotation annotation 
                = method.getAnnotation(expectedAnnotationClass);
            Assert.assertNotNull(
                  String.format(
                    "[For method \"%s\", An annotation is missing: \"%s\"]"
                    , methodName, expectedAnnotationClass.getName()
                  )
                , annotation
            );
        }
        
        return method;
    }

}