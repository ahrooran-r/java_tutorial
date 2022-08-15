package tutorial.learn.algorithms.memoization;

import java.util.HashMap;
import java.util.Map;

public class _3_FibonacciSequence {

    private final Map<Integer, Long> cache = new HashMap<>(50);

    public static void main(String[] args) {

        var obj = new _3_FibonacciSequence();

        long t1 = System.currentTimeMillis();
        long result = obj.simpleFib(50);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicFib(50);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    /**
     * Time complexity: O( 2^(m+n) )
     * Space complexity: O( m+n )
     */
    private long simpleFib(int n) {
        if (n <= 2) return 1;
        return simpleFib(n - 1) + simpleFib(n - 2);
    }

    /**
     * This is a dynamic way of solving fibonacci problem
     * This uses a cache / memoization pattern to store fib once found so next time it does not have to calculate
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private long dynamicFib(int n) {

        // search in cache
        if (cache.containsKey(n)) return cache.get(n);

        // same simple fib algo
        if (n <= 2) return 1;
        long result = dynamicFib(n - 1) + dynamicFib(n - 2);

        // at this point n is not in cache <- so add it to cache
        cache.put(n, result);
        return result;
    }
}
