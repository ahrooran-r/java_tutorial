package tutorial.learn.codecheckup;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.index.qual.Positive;

import java.util.HashSet;
import java.util.Set;

/**
 * When `mvn clean compile` error-prone should show bugs here
 * <a href="https://errorprone.info/api/latest/com/google/errorprone/annotations/package-summary.html">annotation summary</a>
 */
@SuppressWarnings("SameNameButDifferent")
public class _3_Checkerframework {

    @Getter
    @Setter
    public static @Positive int positiveInteger;

    public static void main(final String[] args) {

        setPositiveInteger(-4);

        System.out.println(getPositiveInteger());
        // should show warning underline  or something on positiveInteger ???
    }
}