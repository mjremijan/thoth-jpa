package org.thoth.jpa.UnitTesting;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import static org.thoth.jpa.UnitTesting.AssertAnnotation.assertAnnotations;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertField extends AssertAnnotation {

    public static void assertAnnotations(Class c, String fieldName, Class... annotationClasses) {
        try {
            assertAnnotations(
                Arrays.asList(annotationClasses)
              , Arrays.asList(c.getDeclaredField(fieldName).getAnnotations())
            );
        } catch (NoSuchFieldException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
    
    public static void assertFields(Class c, String...fieldNames) {
        List<Field> fieldList 
            = Arrays.asList(c.getDeclaredFields());
        
        List<String> fieldNameList 
            = Arrays.asList(fieldNames);
        
        // length
        if (fieldList.size() != fieldNameList.size()) {
            throw new AssertionError(
                String.format("Expected %d fields, but found %d", fieldNameList.size(), fieldList.size())
            );
        }
        
        // exists
        fieldNameList.forEach(
            fn -> {
                long cnt
                    = fieldList.stream().filter(f -> f.getName().equals(fn)).count();
                if (cnt == 0) {
                    throw new AssertionError(
                        String.format("No field found with name \"%s\"", fn)
                    );
                }
            }       
        );
    }
}
