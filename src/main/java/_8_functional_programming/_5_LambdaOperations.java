package _8_functional_programming;

import java.util.List;

public class _5_LambdaOperations {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 45, 32, 3, 7, 56, 100, 27);

    }

    private static int sum(int aggregate, int nextNumber) {
        return aggregate + nextNumber;
    }

    /**
     * We use reduce() to aggregate a list. This may seem counterintuitive, but it helps to parallelize the method
     */
    public static Integer addList(List<Integer> nums) {

        // this is most straightforward. but need to add a separate method
        int result1 = nums
                .stream()
                .reduce(0, _5_LambdaOperations::sum); // first value give is called `identity`

        // we are defining the inner method ourselves -> no need to have a separate method
        int result2 = nums
                .stream()
                // `a` is initially assigned to the first value we give (0)
                .reduce(0, (a, b) -> a + b);

        // such methods are already defined in java. so we can use them
        int result3 = nums
                .stream()
                .reduce(0, Integer::sum);

        return result3;
    }

    public static Integer findMax(List<Integer> nums) {
        int result = nums
                .stream()
                .reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);

        return result;
    }

    public static Integer findMin(List<Integer> nums) {
        int result = nums
                .stream()
                .reduce(Integer.MAX_VALUE, (x, y) -> x > y ? y : x);

        return result;
    }

    public static Integer findSumOfOddNumbers(List<Integer> nums) {
        int result = nums
                .stream()
                .filter(x -> x % 2 != 0)
                .reduce(0, Integer::sum);

        return result;
    }

    public static Integer findSumOfSquares(List<Integer> nums) {
        int result = nums
                .stream()
                .map(x -> x * x)
                .reduce(0, Integer::sum);

        return result;
    }

    public static Integer findSumOfCubes(List<Integer> nums) {
        int result = nums
                .stream()
                .map(x -> (int) Math.pow(x, 3))
                .reduce(0, Integer::sum);

        return result;
    }
}
