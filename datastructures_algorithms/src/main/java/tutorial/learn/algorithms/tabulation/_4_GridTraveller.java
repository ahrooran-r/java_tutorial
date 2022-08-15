package tutorial.learn.algorithms.tabulation;

import java.util.Arrays;

public class _4_GridTraveller {

    public static void main(String[] args) {

        var obj = new _4_GridTraveller();

        long t1 = System.currentTimeMillis();
        long result = obj.gridTraveller(3, 3);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private long gridTraveller(int m, int n) {

        // initialize table of m+1, n+1 with 0
        long[][] table = new long[m + 1][n + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, 0));

        // there is only one way a grid traveller with grid 1,1 can move
        table[1][1] = 1;

        for (int row = 0; row <= m; row++) {
            for (int col = 0; col <= n; col++) {
                if (col + 1 <= n) table[row][col + 1] += table[row][col];
                if (row + 1 <= m) table[row + 1][col] += table[row][col];
            }
        }

        return table[m][n];
    }
}
