package tutorial.learn.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?isFullScreen=true
 */
public class _21_AbsoluteDifference {
    public static void main(String[] args) {

        System.out.println(minimumAbsoluteDifference(new ArrayList<>(List.of(-2, 2, 4))));

    }

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // sort the array first
        Collections.sort(arr);

        int minAbsDif = Integer.MAX_VALUE;
        for (int i = 1; i < arr.size(); i++) {
            int abs = Math.abs(arr.get(i) - arr.get(i - 1));
            minAbsDif = Math.min(minAbsDif, abs);
        }
        return minAbsDif;
    }

    public static int minimumAbsoluteDifference_1(List<Integer> arr) {
        int minAbsDif = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int abs = Math.abs(arr.get(i) - arr.get(j));
                minAbsDif = Math.min(minAbsDif, abs);
            }
        }
        return minAbsDif;
    }

}
