package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;

import java.util.Map;

/**
 * A RangeMap is a special kind of mapping from disjoint non-empty ranges to non-null values.
 * <p>
 * Using queries, we may look up the value for any particular range in that map.
 * <p>
 * For example we suppose we take road lanes: bikes can travel in lanes from 0-3 and cars from 4-6 and lorries from 7-9
 */
public class _4_RangeMapClass {
    public static void main(String[] args) {

        RangeMap<Integer, String> vehiclePlace = ImmutableRangeMap.<Integer, String>builder()
                .put(Range.closed(0, 3), "Bikes")
                .put(Range.closed(4, 6), "Cars")
                .put(Range.closed(7, 9), "Lorries")
                .build();

        vehiclePlace.get(5);

        // even after partially removing values from a range,
        // we still can get the values if the range is still valid.
        vehiclePlace.remove(Range.closed(8, 9));
        // still returns Lorries if -> vehiclePlace.get(7)

        // get the overall span of a RangeMap
        Range<Integer> span = vehiclePlace.span();

        // get a sub range map
        RangeMap<Integer, String> subRangeMap
                = vehiclePlace.subRangeMap(Range.closed(4, 14));

        // get entire entry
        Map.Entry<Range<Integer>, String> experienceEntry
                = vehiclePlace.getEntry(5);
    }
}
