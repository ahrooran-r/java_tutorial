package tutorial.learn.collections.apache;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class _1_CollectionUtils {
    public static void main(String[] args) {

        List<Integer> sortedList1 = List.of(1, 2, 7);
        List<Integer> sortedList2 = List.of(0, 4, 6);

        // Merges two sorted Collections, a and b, into a single, sorted List
        // Equivalent of merge method in merge sort
        // given lists should be already sorted
        CollectionUtils.collate(sortedList1, sortedList2, true);

        CollectionUtils.intersection(sortedList1, sortedList2);
        CollectionUtils.disjunction(sortedList1, sortedList2);
        CollectionUtils.subtract(sortedList1, sortedList2);
        CollectionUtils.union(sortedList1, sortedList2);

    }
}
