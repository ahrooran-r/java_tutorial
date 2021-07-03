package _9_modules.collections;

import java.util.Enumeration;
import java.util.Hashtable;

public class _3_Hash_Table {
    public static void main(String[] args) {

        final String[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        Hashtable<Integer, String> alphabet = new Hashtable<>();

        // add elements
        for (int index = 1; index <= ALPHABET.length; index++)
            alphabet.put(index, ALPHABET[index - 1]);

        // clone hashtable
        Hashtable<Integer, String> alphabet_clone = (Hashtable<Integer, String>) alphabet.clone();

        // allows you to compute value of a mapping for specified key if key is not already associated with a value
        // (or is mapped to null).
        alphabet.computeIfAbsent(27, k -> "AA");

        // check whether a value is present in Hashtable
        alphabet.contains("I"); // true

        // check whether a key is present in Hashtable
        alphabet.containsKey(20); // -> true

        // returns values of Hashtable
        Enumeration e = alphabet.elements();

        System.out.println(e.toString());

        // clear hash table
        alphabet.clear();
    }
}
