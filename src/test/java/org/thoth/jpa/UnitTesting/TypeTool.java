package org.thoth.jpa.UnitTesting;

import java.lang.annotation.Annotation;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TypeTool {
    public static <T extends Annotation> T getAnnotation(Class<?> c, Class<T> annotation) {
        return (T)c.getAnnotation(annotation);
    }
}
