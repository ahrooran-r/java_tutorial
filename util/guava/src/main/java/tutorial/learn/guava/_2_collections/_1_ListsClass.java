package tutorial.learn.guava._2_collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <a href="https://www.baeldung.com/guava-lists">tutorial</a>
 */
public class _1_ListsClass {
    public static void main(String[] args) {

        // create lists
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom", "Viki", null, "Tyler");

        // reverse lists
        Lists.reverse(names);

        // generate char list from string
        List<Character> charList = Lists.charactersOf("HelloWorld");

        // partition a list
        List<List<String>> result = Lists.partition(names, 2);

        // remove duplicates from list -> Guava vs Standard Java way
        ImmutableSet.copyOf(charList).asList();
        Set.of(charList).toArray();

        // remove null from list -> Guava vs Standard Java way
        Iterables.removeIf(names, Objects::isNull);
        names.stream().filter(Objects::nonNull).toList();

        // convert to immutable list
        ImmutableList.copyOf(names);
    }
}
