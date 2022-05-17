package _9_modules.collections;

import java.util.ArrayList;
import java.util.Arrays;

public class _1_Array_List {
    public static void main(String[] args) {

        final String[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        // creating empty array list, specify initial capacity
        ArrayList<String> alphabet = new ArrayList<>(26);

        // add element to last
        alphabet.add("Banana");

        // add all elements of another list from ith position
        alphabet.addAll(0, Arrays.asList(ALPHABET));
        // here new list is added before "Banana" in old list as index=0

        // add element at specific location
        alphabet.add(7, "Orange");

        // view elements
        alphabet.toString(); // -> [A, B, C, ... Orange, H, I, ... X, Y, Z, Banana]

        // get an element
        alphabet.get(5); // -> D

        // change an element
        alphabet.set(0, "Apple"); // -> [Apple, B, .... ]

        // removes and returns element
        alphabet.remove(25); // -> [Apple, B, ..... Y]

        // get size of list
        alphabet.size(); // -> 25

        // Returns the index of the first occurrence of the specified element
        alphabet.indexOf("Apple"); // -> 0

        // clear all elements
        alphabet.clear(); // -> []

        alphabet.contains("Apple"); // -> false

        /* NOTE:
        Elements in an ArrayList are actually objects. In the examples above, we created elements (objects) of
        type "String". Remember that a String in Java is an object (not a primitive type). To use other types, such
        as int, you must specify an equivalent wrapper class: Integer. For other primitive types, use: Boolean for
        boolean, Character for char, Double for double, etc.
        */
    }
}
