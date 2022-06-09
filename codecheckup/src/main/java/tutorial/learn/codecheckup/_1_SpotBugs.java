package tutorial.learn.codecheckup;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * When `mvn clean verify` spotbugs should show bugs here
 */
@SuppressFBWarnings(value = "MS_EXPOSE_REP", justification = "an example to use spotbugs annotations")
public class _1_SpotBugs {

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