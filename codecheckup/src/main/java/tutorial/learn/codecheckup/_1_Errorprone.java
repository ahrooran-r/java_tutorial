package tutorial.learn.codecheckup;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * When `mvn clean compile` error-prone should show bugs here
 * <a href="https://errorprone.info/api/latest/com/google/errorprone/annotations/package-summary.html">annotation summary</a>
 */
@SuppressWarnings("SameNameButDifferent")
public class _1_Errorprone {

    @Getter
    private static final Set<Short> s = new HashSet<>();

    public static void main(String[] args) {
        for (short i = 0; i < 100; i++) {
            getS().add(i);
            getS().remove(i - 1);
        }
        System.out.println(s.size());
    }
}