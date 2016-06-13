package org.thoth.jpa.UnitTesting;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TypeTool {

    public static int getAnnotationCount(Class c) {
        return c.getAnnotations().length;
    }
    
    public static <T> T getAnnotation(Class c, Class<? extends T> annotation) {
        return (T)c.getAnnotation(annotation);
    }
    
    public static int getFieldCount(Class c) {
        return c.getDeclaredFields().length;
    }    
}
