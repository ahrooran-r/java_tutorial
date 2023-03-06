package tutorial.learn.guava._1_basics;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.util.Comparator;

public class _4_ComparisonClass implements Comparable<_4_ComparisonClass> {
    private int someValue;
    private String someOtherValue;

    /**
     * @see ComparisonChain#compare(Object, Object, Comparator)
     */
    @Override
    public int compareTo(_4_ComparisonClass that) {
        return ComparisonChain.start()
                .compare(this.someValue, that.someValue)
                .compare(this.someOtherValue, that.someOtherValue, Ordering.natural().nullsLast())
                .result();
    }
}
