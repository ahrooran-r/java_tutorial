package tutorial.learn.apache_commons._2_collections;

import org.apache.commons.collections4.queue.CircularFifoQueue;

/**
 * CircularFifoQueue<E> implements the Queue<E> interface and is a fixed-size, non-blocking queue
 * — when you add an element to a queue that is full, the oldest element is removed to make room for the new element.
 */
public class _2_CircularFifoQueue {
    public static void main(String[] args) {

        CircularFifoQueue<String> colors = new CircularFifoQueue<>(5);
        colors.add("RED");
        colors.offer("GREEN"); // both `add` and `offer` are same in this case

        colors.isFull();
        colors.isAtFullCapacity();
    }
}
