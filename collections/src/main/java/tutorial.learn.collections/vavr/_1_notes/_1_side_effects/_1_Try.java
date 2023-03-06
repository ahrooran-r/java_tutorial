package tutorial.learn.collections.vavr._1_notes._1_side_effects;

import io.vavr.control.Try;

public class _1_Try {
    public static void main(String[] args) {

        Try<Integer> result = divideVavr(1, 0);
        System.out.println(result.isSuccess() ? result.get() : "failure");

    }

    /**
     * throws if divisor is zero
     */
    private static int divide(int dividend, int divisor) {
        return dividend / divisor;
    }

    /**
     * we are in the favorable situation to encapsulate the side-effect in a Try
     */
    private static Try<Integer> divideVavr(int dividend, int divisor) {
        return Try.of(() -> dividend / divisor);
    }
}
