package _11_guava._2_collections;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.MinMaxPriorityQueue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Queue;

/**
 * <a href="https://www.baeldung.com/guava-minmax-priority-queue-and-evicting-queue">tutorial</a>
 */
public class _3_QueueClass {
    public static void main(String[] args) {

        // When we want to add a new item to the EvictingQueue, and the queue is full,
        // it automatically evicts an element from its head.

        // When comparing to the standard queue behavior, adding an element to the full
        // queue does not block but removes the head element and adds a new item to the tail.

        // We can imagine the EvictingQueue as a ring to which we are inserting elements
        // in the append-only fashion. If there is an element on the position on which we want to
        // add a new element, we just override the existing element at the given position.

        // NOT thread safe
        Queue<Integer> evictingQueue = EvictingQueue.create(100);

        // MinMaxPriorityQueue provides constant-time access to its least and greatest elements.
        // It is a double ended queue
        MinMaxPriorityQueue<Feed> minMaxPriorityQueue = MinMaxPriorityQueue
                .orderedBy(Comparator.comparing(Feed::getTime))
                .maximumSize(100)
                .create();

        // Get Maximum and Minimum
        minMaxPriorityQueue.peekFirst();
        minMaxPriorityQueue.peekLast();

        minMaxPriorityQueue.pollLast();
        minMaxPriorityQueue.removeLast();
    }


    @Data
    @AllArgsConstructor
    static class Feed {
        private LocalDateTime time;
    }
}
