package tutorial.learn.algorithms;

import java.util.List;

public class _18_MiniMaxSum {
    public static void main(String[] args) {
        miniMaxSum(List.of(1, 3, 5, 7, 9));
    }

    public static void miniMaxSum(List<Integer> arr) {
        long sum = 0;
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;

        for (Integer element : arr) {
            sum += element;
            if (max < element) max = element;
            if (min > element) min = element;
        }

        System.out.printf("%d %d\n", sum - max, sum - min);
    }
}
