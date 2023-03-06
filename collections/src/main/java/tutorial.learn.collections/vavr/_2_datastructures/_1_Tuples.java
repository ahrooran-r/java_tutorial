package tutorial.learn.collections.vavr._2_datastructures;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * A Tuple combines a fixed number of elements together so that they can be passed around as a whole.
 * Unlike an array or list, a tuple can hold objects with different types, but they are also immutable.
 * <p>
 * Similar to Apache Pair, Triple or SpringBoot Pair/Triple.
 * But tuple can hold up to 8 different objects
 */
public class _1_Tuples {
    public static void main(String[] args) {

        Tuple2<String, Integer> t2 = Tuple.of("a", 2);
        // get elements directly
        String first = t2._1;
        int second = t2._2;
        // or get elements via methods
        String firstM = t2._1();
        int secondM = t2._2();

        // operate on tuple

        // 1. map
        t2 = t2.map(s -> s + "mapped", i -> i + 1); // map using 2 functions
        t2 = t2.map((s, i) -> Tuple.of(s + "mapped", i + 1)); // map using single mapper

        // 2. transform -> does some operation and return another object
        String str = t2.apply((s, i) -> s + i);

        // up to Tuple 8 is available
        // Tuple3, Tuple4, Tuple5, Tuple5=6, Tuple7, Tuple8

    }
}
