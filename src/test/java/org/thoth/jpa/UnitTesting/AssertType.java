package org.thoth.jpa.UnitTesting;

import java.util.Arrays;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertType extends AssertAnnotation {

    public static void assertAnnotations(Class c, Class... annotationClasses) {
        assertAnnotations(
              Arrays.asList(annotationClasses)
            , Arrays.asList(c.getAnnotations())
        );
    }
}
