package tutorial.learn.algorithms.memoization;

import java.util.HashMap;
import java.util.Map;

public class _5_CanSum {

    private final Map<Integer, Boolean> cache = new HashMap<>();

    public static void main(String[] args) {

        var obj = new _5_CanSum();

        long t1 = System.currentTimeMillis();
        boolean result = obj.simpleCanSum(300, new int[]{7, 14});
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicCanSum(300, new int[]{7, 14});
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

    }

    /**
     * Time complexity: O(n^m)
     * Space complexity: O(m)
     */
    private boolean simpleCanSum(int target, int[] array) {

        // base condition
        if (target == 0) return true;
        if (target < 0) return false;

        // now reduce the size of target by subtracting each element in the array
        for (int element : array) {
            boolean result = simpleCanSum(target - element, array);
            if (result) return true;
        }
        return false;
    }

    /**
     * Time complexity: O(n*m)
     * Space complexity: O(m)
     */
    private boolean dynamicCanSum(int target, int[] array) {

        // base condition
        if (target == 0) return true;
        if (target < 0) return false;
        if (cache.containsKey(target)) return cache.get(target);

        // now reduce the size of target by subtracting each element in the array
        for (int element : array) {
            boolean result = dynamicCanSum(target - element, array);
            if (result) {
                cache.put(target, true);
                return true;
            }
        }

        cache.put(target, false);
        return false;
    }
}
