package tutorial.learn.core._9_modules.collections;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class _5_TreeSet {
    public static void main(String[] args) {

        /*
        The elements are ordered using their natural ordering, or by a Comparator
        provided at set creation time, depending on which constructor is used.
         */
        Set<Integer> set = new TreeSet<>();

        set.addAll(List.of(1, 5, 2, 10, 3, 1000, 23, 56, 12));

        System.out.println(set);

    }
}
