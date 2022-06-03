package _11_guava._2_collections._1_maps;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * MultiSet allows for multiple occurrences of the same element by tracking the count of each unique element
 * it contains. <a href="https://www.baeldung.com/guava-multiset">tutorial</a>
 * <p>
 * Think of this as a
 * <pre>{@code HashMap<String, Integer>}</pre>
 * <p>
 * but more specific to elements and their counts
 * Advantages:
 * 1. No special code required when adding an element that is not already in the collection.
 * <p>
 * 2. Methods for handling the count of elements directly: count(E), add(E, int), etc.
 * <p>
 * 3. The intention of the code is clearer. A Multiset<E> obviously maps the elements to their counts.
 * A Map<E, Integer> could map the elements to arbitrary integers.
 */
public class _3_MultiSetClass {
    public static void main(String[] args) {

        Multiset<String> bookStore = HashMultiset.create();
        bookStore.add("Potter");
        bookStore.add("Potter");
        bookStore.add("Potter");

        // Easily get the count -> if it is hashmap we have to do: hashmap.get("Potter");
        // Which is counter intuitive
        bookStore.count("Potter");

        // again clear to read
        bookStore.setCount("Potter", 50);

        // Also supports other standard add, remove methods belong to Collection


        // Using this is in a concurrent environment
        Multiset<String> concurrentSet = ConcurrentHashMultiset.create();
        // Normal Methods work fine in concurrent environment
        // BUT
        // setCount() method will not
        // So we need to follow AtomicReference#compareAndSet() approach
        boolean isSuccess = concurrentSet.setCount("Potter", 20, 10);
        // each thread will check for the old count and if it matches then place new count
        // otherwise will check again
        // so need to run in a loop until success
    }
}
