package org.thoth.jpa.UnitTesting;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertAnnotation {
    protected static void assertAnnotations(List<Class> annotationClasses, List<Annotation> annotations) {
        // length
        if (annotationClasses.size() != annotations.size()) {
            throw new AssertionError(
                String.format("Expected %d annotations, but found %d"
                    , annotationClasses.size(), annotations.size()
            ));
        }

        // exists
        annotationClasses.forEach(
            ac -> {
                long cnt 
                    = annotations.stream()
                        .filter(a -> a.annotationType().isAssignableFrom(ac))
                        .count();
                if (cnt == 0) {
                    throw new AssertionError(
                        String.format("No annotation of type %s found", ac.getName())
                    );
                }
            }
        );
    }
}
