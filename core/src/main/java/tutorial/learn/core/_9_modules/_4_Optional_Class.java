package tutorial.learn.core._9_modules;

import java.util.Optional;

public class _4_Optional_Class {
    public static void main(String[] args) {

        // assigning 'null' value
        String emptyNormal = null;
        Optional<String> emptyOptional = Optional.empty();

        // assigning a 'nonNull' value
        String assignNormal = "ahrooran";
        // However, the argument passed to the of() method can't be null.
        // Otherwise, we'll get a NullPointerException
        Optional<String> assignOptional = Optional.of(assignNormal);

        // But in case we expect some null values, we can use the ofNullable() method
        Optional<String> assignNullableOptional = Optional.ofNullable(emptyNormal);

        // When we have an Optional object returned from a method or created by us,
        // we can check if there is a value in it or not with the isPresent() / isEmpty() method:
        // isEmpty() is only for Java 11
        emptyOptional.isPresent(); // -> false
        assignOptional.isPresent(); // -> true

        emptyOptional.isEmpty(); // -> true
        assignOptional.isEmpty(); // -> false

        // Conditional Action With ifPresent ()
        /*
            Before Optional, we'd do:
            if (assignNormal != null) {
                System.out.println(assignNormal.length());
            }
        */


    }
}
