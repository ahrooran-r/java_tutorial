package tutorial.learn.algorithms.tabulation;

import java.util.Arrays;

public class _5_CanSum {

    public static void main(String[] args) {

        var obj = new _5_CanSum();

        long t1 = System.currentTimeMillis();
        // boolean result = obj.canSum(300, new int[]{7, 14});
        boolean result = obj.canSum(7, new int[]{5, 3, 4});
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private boolean canSum(int target, int[] array) {

        boolean[] table = new boolean[target + 1];
        Arrays.fill(table, false);
        table[0] = true;

        for (int i = 0; i < table.length; i++) {

            // if an element in the table can be filled,
            // then adding the given numbers to that element will also return true
            if (table[i] == true) {
                for (int element : array) {
                    if (i + element < table.length) table[i + element] = true;
                }
            }
        }

        return table[target];
    }
}
