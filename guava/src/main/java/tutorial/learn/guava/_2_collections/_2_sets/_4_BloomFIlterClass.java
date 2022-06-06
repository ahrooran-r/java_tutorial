package tutorial.learn.guava._2_collections._2_sets;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * A Bloom filter is a memory-efficient, probabilistic data structure that we can use to
 * answer the question of whether or not a given element is in a set.
 * <p>
 * There are no false negatives with a Bloom filter, so when it returns false,
 * we can be 100% certain that the element is not in the set.
 * <p>
 * However, a Bloom filter can return false positives, so when it returns true,
 * there is a high probability that the element is in the set, but we can not be 100% sure.
 * <p>
 * <a href="https://www.baeldung.com/guava-bloom-filter">tutorial</a>
 */
public class _4_BloomFIlterClass {
    public static void main(String[] args) {

        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01d
        );

        filter.put(1);
        filter.put(2);
        filter.put(3);

        filter.mightContain(2);

        // When we design our Bloom filter, it is important that we provide a reasonably
        // accurate value for the expected number of elements.
        // Otherwise, our filter will return false positives at a much higher rate than desired.
    }
}
