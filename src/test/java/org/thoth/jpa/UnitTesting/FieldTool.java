package org.thoth.jpa.UnitTesting;

import java.lang.reflect.Field;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class FieldTool {

    public static int getAnnotationCount(Class c, String fieldName) {
        try {
            Field field
                = c.getDeclaredField(fieldName);
            return 
                field.getAnnotations().length;                
        } catch (NoSuchFieldException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
}
