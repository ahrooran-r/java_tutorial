package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.*;

import java.util.Map;

/**
 * <a href="https://www.baeldung.com/guava-maps">tutorial</a>
 */
public class _1_ImmutableMapClass {
    public static void main(String[] args) {

        // building an immutable map
        Map<String, Integer> salary = ImmutableMap.<String, Integer>builder()
                .put("John", 1000)
                .put("Jane", 1500)
                .put("Adam", 2000)
                .put("Tom", 2000)
                .build();

        // creating a sorted map according to ordering
        ImmutableSortedMap<String, Integer> salary2 = new ImmutableSortedMap
                .Builder<String, Integer>(Ordering.natural())
                .put("John", 1000)
                .put("Jane", 1500)
                .put("Adam", 2000)
                .put("Tom", 2000)
                .build();
    }
}
