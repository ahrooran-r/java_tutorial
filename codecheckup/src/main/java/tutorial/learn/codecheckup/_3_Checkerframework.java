package tutorial.learn.codecheckup;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * When `mvn clean compile` error-prone should show bugs here
 * <a href="https://errorprone.info/api/latest/com/google/errorprone/annotations/package-summary.html">annotation summary</a>
 */
@SuppressWarnings("SameNameButDifferent")
public class _3_Checkerframework {

    public static @Nullable Object nullable = null;

    public static void main(final String[] args) {
        System.out.println("Hello World!");

        @NonNull Object nn = nullable; // error on this line
        System.out.println(nn);
    }
}