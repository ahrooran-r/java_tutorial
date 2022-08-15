package tutorial.learn.algorithms.memoization;

import java.util.*;

public class _7_BestSum {

    private final Map<Integer, List<Integer>> cache = new HashMap<>();

    public static void main(String[] args) {

        var obj = new _7_BestSum();

        long t1 = System.currentTimeMillis();
        List<Integer> result = obj.simpleBestSum(300, Arrays.asList(7, 14));
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicBestSum(300, Arrays.asList(7, 14));
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private List<Integer> simpleBestSum(int target, List<Integer> list) {

        // return an empty array
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;

        // now reduce the size of target by subtracting each element in the array
        List<Integer> bestList = null;
        for (Integer element : list) {
            List<Integer> result = simpleBestSum(target - element, list);
            if (null != result) {
                result.add(element);
                if (null == bestList || bestList.size() > result.size()) bestList = result;
            }
        }
        return bestList;
    }

    private List<Integer> dynamicBestSum(int target, List<Integer> list) {

        // base condition
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        if (cache.containsKey(target)) return cache.get(target);

        // now reduce the size of target by subtracting each element in the array
        List<Integer> bestList = null;
        for (int element : list) {
            List<Integer> result = dynamicBestSum(target - element, list);
            if (null != result) {
                result.add(element);
                if (null == bestList || bestList.size() > result.size()) bestList = result;
            }
        }

        cache.put(target, bestList);
        return bestList;
    }
}
