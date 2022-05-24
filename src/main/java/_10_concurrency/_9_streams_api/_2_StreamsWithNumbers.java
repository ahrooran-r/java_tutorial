package _10_concurrency._9_streams_api;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _2_StreamsWithNumbers {

    public static void main(String[] args) {

        final int[] nums = {1, 3, 2, 4, 5, 6, -3, -30, 4, 50};

        // convert array to stream
        // foreach requires a method -> provided by lambda methods
        Arrays.stream(nums).forEach(_2_StreamsWithNumbers::myFunc);
        Arrays.stream(nums).forEach(System.out::println);

        // finding sum
        int sum = Arrays.stream(nums).sum();
        System.out.println("SUM: " + sum);

        // we can use lambda as well
        IntStream.range(0, 10).forEach(x -> System.out.print(x + " "));
        System.out.println();

        // all ABOVE are terminal operations
        // we can use INTERMEDIATE operations as well such as filter and reduce
        IntStream.range(0, 10).filter(x -> x > 4).forEach(x -> System.out.print(x + " "));

    }

    private static void myFunc(int i) {
        System.out.println(i);
    }
}
