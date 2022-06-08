package tutorial.learn.codecheckup;

import java.util.HashSet;
import java.util.Set;

/**
 * When compiling error-prone should show bugs here
 */
public class _1_Errorprone {

    public static void main(String[] args) {
        Set<Short> s = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            s.add(i);
            s.remove(i - 1);
        }
        System.out.println(s.size());
    }
}