package tutorial.learn.guava._2_collections._2_sets;

import com.google.common.collect.Range;
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

        nums.add(Range.singleton(20));
        nums.add(Range.closed(5, 18));
        nums.add(Range.openClosed(2, 7));

        nums.remove(Range.closedOpen(3, 12));

        // span
        nums.span();

        // get a sub range set within specified range
        nums.subRangeSet(Range.closed(10, 13));

        // get a complement which returns every element other than this range
        nums.complement();

        // when we would like to check whether a range interval present in RangeSet intersects
        // with some or all the values in another given range, we can make use of the intersect method
        nums.intersects(Range.closed(19, 200));


        // get all values as list
        // TODO

    }
}
