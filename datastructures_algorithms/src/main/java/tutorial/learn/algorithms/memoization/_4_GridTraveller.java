package tutorial.learn.algorithms.memoization;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class _4_GridTraveller {

    private final Map<Grid, Long> cache = new HashMap<>();

    public static void main(String[] args) {

        var obj = new _4_GridTraveller();

        long t1 = System.currentTimeMillis();
        long result = obj.simpleGridTraveller(18, 18);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicGridTraveller(18, 18);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    /**
     * Time complexity: O( 2^(m+n) )
     * Space complexity: O( m+n )
     */
    private long simpleGridTraveller(int m, int n) {
        // base cases
        if (m == 1 && n == 1) return 1;
        else if (m == 0 || n == 0) return 0;

        else return simpleGridTraveller(m - 1, n) + simpleGridTraveller(m, n - 1);
    }

    /**
     * Time complexity: O(m*n)
     * Space complexity: O(m+n)
     */
    private long dynamicGridTraveller(int m, int n) {

        // base cases
        if (m == 1 && n == 1) return 1;
        else if (m == 0 || n == 0) return 0;

        long result = getFromCache(m, n);
        if (result >= 0) return result;

        result = dynamicGridTraveller(m - 1, n) + dynamicGridTraveller(m, n - 1);
        putIntoCache(m, n, result);
        return result;
    }

    private long getFromCache(int m, int n) {
        Grid key = new Grid(m, n);
        Grid reverse = new Grid(n, m);
        if (cache.containsKey(key)) return cache.get(key);
        else if (cache.containsKey(reverse)) return cache.get(reverse);
        return Long.MIN_VALUE;
    }

    private void putIntoCache(int m, int n, long value) {
        Grid key = new Grid(m, n);
        Grid reverse = new Grid(n, m);

        if (!cache.containsKey(key) && !cache.containsKey(reverse)) cache.put(key, value);
    }

    static class Grid {
        int m;
        int n;

        public Grid(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Grid grid = (Grid) o;
            return m == grid.m && n == grid.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(m, n);
        }
    }
}
