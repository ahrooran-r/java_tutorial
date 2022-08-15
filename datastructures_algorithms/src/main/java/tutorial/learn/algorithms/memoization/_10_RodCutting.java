package tutorial.learn.algorithms.memoization;

import java.util.HashMap;
import java.util.Map;

public class _10_RodCutting {

    private final Map<Integer, Long> cache = new HashMap<>();

    public static void main(String[] args) {

        int n = 200;
        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20};

        var obj = new _10_RodCutting();

        long t1 = System.currentTimeMillis();
        long result = obj.memoizedRodCut(n, p);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private long vanillaRodCut(int n, int[] p) {
        // base case
        if (n <= 0) return 0;

        long price = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            price = Math.max(price, p[i] + vanillaRodCut(n - i - 1, p));
        }
        return price;
    }

    private long memoizedRodCut(int n, int[] p) {

        // base case
        if (n <= 0) return 0;
        if (cache.containsKey(n)) return cache.get(n);

        long price = Long.MIN_VALUE;
        for (int i = 0; i < Math.min(n, p.length); i++) {
            price = Math.max(price, p[i] + memoizedRodCut(n - i - 1, p));
        }

        cache.put(n, price);
        return price;
    }
}
