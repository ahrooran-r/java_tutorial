package tutorial.learn.guava._2_collections._2_sets;

import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * A RangeSet is a set comprising zero or more non-empty, disconnected ranges.
 * When adding a range to a mutable RangeSet, any connected ranges are merged together while empty ranges are ignored.
 * <p>
 * <a href="https://www.baeldung.com/guava-rangeset">tutorial</a>
 */
public class _3_RangeSetClass {
    public static void main(String[] args) {

        RangeSet<Integer> nums = TreeRangeSet.<Integer>create();

    }
}
