package tutorial.learn.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/marcs-cakewalk/problem?isFullScreen=true
 */
public class _22_CakeWalk {
    public static void main(String[] args) {

        System.out.println(marcsCakewalk(new ArrayList<>(List.of(5, 10, 7))));
        System.out.println(marcsCakewalk(new ArrayList<>(List.of(1, 3, 2))));
        System.out.println(marcsCakewalk(new ArrayList<>(List.of(7, 4, 9, 6))));

    }

    public static long marcsCakewalk(List<Integer> calorie) {
        long sum = 0;

        // reverse sorted -> O (n log n)
        calorie.sort(Collections.reverseOrder());

        // O(n)
        for (int i = 0; i < calorie.size(); i++) {
            int cal = calorie.get(i);
            sum += (long) (Math.pow(2, i) * cal);
        }
        return sum;
    }

}
