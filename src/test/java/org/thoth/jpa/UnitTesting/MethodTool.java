package org.thoth.jpa.UnitTesting;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class MethodTool {
    public static <T extends Annotation> T getAnnotation(Class<?> c, String methodName, Class<T> annotation) {
        try {
            Method m = c.getDeclaredMethod(methodName); 
            return (T)m.getAnnotation(annotation);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }   
    }
}
