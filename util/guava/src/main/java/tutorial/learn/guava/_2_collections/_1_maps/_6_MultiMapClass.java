package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * In the case of Guava Multimap, if we add two values for the same key,
 * the second value will not override the first value.
 * Instead, we will have two values in the resulting map.
 * <p>
 * <a href="https://www.baeldung.com/guava-multimap">tutorial</a>
 */
public class _6_MultiMapClass {
    public static void main(String[] args) {

        // Creating
        String key = "a-key";
        Multimap<String, String> map = ArrayListMultimap.create();

        map.put(key, "firstValue");
        map.put(key, "secondValue");

        // get
        map.get(key);

        // Standard map from java.util package doesn't give us the ability to assign multiple values to the same key.
        // because of a second put() operation that overrides the first value. Should we want to achieve the same
        // behavior as with Guava's Multimap, we would need to create a Map that has a List<String> as a value type

        // Multimaps are commonly used in places where a Map<K, Collection<V>> would otherwise have appeared.

    }
}
