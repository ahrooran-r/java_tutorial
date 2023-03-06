package tutorial.learn.collections.vavr._1_notes._2_functional_data_structures;

import io.vavr.collection.List;

import java.util.LinkedList;

public class _1_List {
    public static void main(String[] args) {

        // VAVR creates an Immutable linked list
        List<Integer> list = List.of(1, 2, 5, -1);

        // following is java alternative
        java.util.List<Integer> javaList = new LinkedList<>();

        list.head(); // return first element / EXCEPTION if null
        list.headOption(); // return first element / null
        // javaList.get(0); // java alternative

        // return all elements EXCEPT first element <- first element is still there not deleted
        list.tail();

        // returns a new list with 200 before all elements
        list.prepend(200);
        // original list is still there <- Immutability
        // any operation on vavr will return a new list <- no mutation to original list

        // Advantage of VAVR
        List<Integer> list2 = list.tail().prepend(0);
        // The new head element 0 is linked to the tail of the original List. The original List remains unmodified.

        System.out.println(list);
        System.out.println(list2);

        // Building a list of words and appending together into single list
        List.of("hi", "hello", "little", "baby")
                // intersperse would add a single comma between every element
                .intersperse(", ")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
    }
}
