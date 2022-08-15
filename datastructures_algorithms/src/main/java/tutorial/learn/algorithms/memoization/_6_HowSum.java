package tutorial.learn.algorithms.memoization;

import java.util.*;

public class _6_HowSum {

    private final Map<Integer, List<Integer>> cache = new HashMap<>();

    public static void main(String[] args) {

        var obj = new _6_HowSum();

        long t1 = System.currentTimeMillis();
        List<Integer> result = obj.simpleHowSum(300, Arrays.asList(7, 14));
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicHowSum(300, Arrays.asList(7, 14));
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    /**
     * Time complexity: O(n^m * m)
     * Space complexity: O(m)
     */
    private List<Integer> simpleHowSum(int target, List<Integer> list) {

        // return an empty array
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;

        // now reduce the size of target by subtracting each element in the array
        for (Integer element : list) {
            List<Integer> result = simpleHowSum(target - element, list);
            if (null != result) {
                result.add(element);
                return result;
            }
        }
        return null;
    }

    /**
     * Time complexity: O( n * m*m ) -> O( n * m^2 )
     * Space complexity: O( m*m ) -> O (m^2)
     */
    private List<Integer> dynamicHowSum(int target, List<Integer> list) {

        // base condition
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        if (cache.containsKey(target)) return cache.get(target);

        // now reduce the size of target by subtracting each element in the array
        for (int element : list) {
            List<Integer> result = dynamicHowSum(target - element, list);
            if (null != result) {
                result.add(element);
                cache.put(target, result);
                return result;
            }
        }

        cache.put(target, null);
        return null;
    }
}
