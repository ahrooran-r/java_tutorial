package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.List;

public class _8_MapsClass {
    public static void main(String[] args) {

        // Maps.uniqueIndex(Iterable, Function) addresses the common case of having a bunch of objects that
        // each have some unique attribute, and wanting to be able to look up those objects based on that attribute.

        // Let's say we have a bunch of strings that we know have unique lengths, and we want to be
        // able to look up the string with some particular length.

        Iterable<String> anyIterable = List.of("a", "b", "c");

        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(
                anyIterable,
                new Function<String, Integer>() {
                    public Integer apply(String string) {
                        return string.length();
                    }
                });

    }
}
