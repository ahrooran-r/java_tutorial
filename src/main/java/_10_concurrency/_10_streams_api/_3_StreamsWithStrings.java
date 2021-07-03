package _10_concurrency._10_streams_api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class _3_StreamsWithStrings {

    public static void main(String[] args) {

        final String[] names = {"barbossa", "hector", "sparrow", "perl", "jack", "eli"};

        // convert array to stream
        Arrays.stream(names).forEach(System.out::print);

        // or more preferable method for OBJECTS...
        Stream.of(names).forEach(System.out::print);

        // sorted function
        Stream.of(names).sorted().forEach(System.out::println);
        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // we can filter names too
        Stream.of(names).filter(name -> name.startsWith("s")).forEach(System.out::println);

    }

    private static void myFunc(int i) {
        System.out.println(i);
    }
}
