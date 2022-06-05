package tutorial.learn.guava._2_collections._2_sets;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * <a href="https://www.baeldung.com/guava-sets">tutorial</a>
 */
public class _1_SetsClass {
    public static void main(String[] args) {

        Set<String> set1 = Sets.newHashSet("John", "Jane", "Adam");
        Set<String> set2 = Sets.newHashSet("Tom", "Viki", null, "Tyler");

        // union of set
        Set<String> union = Sets.union(set1, set2);

        // cartesian product
        // https://www.baeldung.com/java-generics
        Set<List<String>> cartesianProduct = Sets.cartesianProduct(set1, set2);

        // intersection
        Set<String> intersection = Sets.intersection(set1, set2);

        // symmetric difference -> all elements that are contained in either set 1 or set 2 but not in both:
        Set<String> symmetricDifference = Sets.symmetricDifference(set1, set2);

        // power set –> the set of all possible subsets of that set.
        Set<Set<String>> powerSet = Sets.powerSet(union);
    }
}
