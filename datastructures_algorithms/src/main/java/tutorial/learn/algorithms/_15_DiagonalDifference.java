package tutorial.learn.algorithms;

import java.util.List;

/*
 * https://www.hackerrank.com/challenges/diagonal-difference/problem?isFullScreen=true
 * */
public class _15_DiagonalDifference {
    public static void main(String[] args) {

        var arr_1 = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(9, 8, 9)
        );

        var dd_1 = diagonalDifference(arr_1);
        System.out.println(dd_1);


        var arr_2 = List.of(
                List.of(11, 2, 4),
                List.of(4, 5, 6),
                List.of(10, 8, -12)
        );

        var dd_2 = diagonalDifference(arr_2);
        System.out.println(dd_2);


    }

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        int diag1 = 0;
        int diag2 = 0;

        for (int row = 0; row < arr.size(); row++) {
            for (int col = 0; col < arr.get(row).size(); col++) {
                if (row == col) diag1 += arr.get(row).get(col);
                if (row + col == arr.size() - 1) diag2 += arr.get(row).get(col);
            }
        }

        return Math.abs(diag2 - diag1);
    }
}
