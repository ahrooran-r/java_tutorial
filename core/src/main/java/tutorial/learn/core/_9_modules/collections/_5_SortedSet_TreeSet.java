package tutorial.learn.core._9_modules.collections;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class _5_SortedSet_TreeSet {
    public static void main(String[] args) {

        /*
        The elements are ordered using their natural ordering, or by a Comparator
        provided at set creation time, depending on which constructor is used.

        SortedSet is the interface and TreeSet is the implementation
         */
        SortedSet<Integer> set = new TreeSet<>();

        set.addAll(List.of(1, 5, 2, 10, 3, 1000, 23, 56, 12));

        System.out.println(set);

    }
}
