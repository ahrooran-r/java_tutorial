package tutorial.learn.collections.vavr._1_notes._2_functional_data_structures;

import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;

import java.util.Comparator;

public class _3_SortedSet {
    public static void main(String[] args) {

        SortedSet<Integer> sortedSet = TreeSet.of(6, 1, 3, 2, 4, 7, 8, 4, 6);

        // a reversed set
        Comparator<Integer> c = (a, b) -> b - a;
        SortedSet<Integer> reversed = TreeSet.of(c, 2, 3, 1, 2);
    }
}
