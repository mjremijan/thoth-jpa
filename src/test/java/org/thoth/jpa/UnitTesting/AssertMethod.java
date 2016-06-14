package org.thoth.jpa.UnitTesting;

import java.util.Arrays;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertMethod extends AssertAnnotation {
    public static void assertAnnotations(Class c, String getterName, Class...annotationClasses) {
        try {
            assertAnnotations(
                Arrays.asList(annotationClasses)
              , Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations())
            );
        } catch (NoSuchMethodException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
}
