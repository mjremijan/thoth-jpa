package org.thoth.jpa.UnitTesting;

import java.lang.annotation.Annotation;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertAnnotation {

    private static void length(Class[] annotationClasses, Annotation[] annotations) {
        if (annotations.length != annotationClasses.length) {
            throw new AssertionError(
                String.format("Expected %d annotations, but found %d", annotationClasses.length, annotations.length)
            );
        }
    }
    
    private static void found(Class[] annotationClasses, Annotation[] annotations) {
        top: 
        for (Class annotationClass : annotationClasses) {
            for (Annotation annotation : annotations) { 
                if (annotation.annotationType().isAssignableFrom(annotationClass)) {
                    continue top;
                }
            }
            throw new AssertionError(
                String.format("No annotation of type %s found", annotationClass.getName())
            );
        }
    }
    
    public static void typeExpected(Class c, Class...annotationClasses) {
        Annotation[] annotations
            = c.getAnnotations();

        // Lengths are the same.
        length(annotationClasses, annotations);

        // Classes are found
        found(annotationClasses, annotations);
    }
    
    
    public static void fieldExpected(Class c, String fieldName, Class...annotationClasses) {
        try {
            Annotation[] annotations
                = c.getDeclaredField(fieldName).getAnnotations();
            
            // Lengths are the same.
            length(annotationClasses, annotations);

            // Classes are found
            found(annotationClasses, annotations);
        } catch (NoSuchFieldException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
    
    public static void getterExpected(Class c, String getterName, Class...annotationClasses) {
        try {
            if (!getterName.startsWith("get")) {
                getterName =
                    "get"
                    + getterName.substring(0, 1).toUpperCase()
                    + getterName.substring(1)
                ;                    
            }
            Annotation[] annotations
                = c.getDeclaredMethod(getterName).getAnnotations();
            
            // Lengths are the same.
            length(annotationClasses, annotations);

            // Classes are found
            found(annotationClasses, annotations);
        } catch (NoSuchMethodException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
}
