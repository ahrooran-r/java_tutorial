package tutorial.learn.collections.vavr._1_notes._2_functional_data_structures;

import io.vavr.Tuple2;
import io.vavr.collection.Queue;

import java.util.ArrayDeque;

public class _2_Queue {
    public static void main(String[] args) {

        Queue<Integer> queue = Queue.of(1, 2, 3, 4, 5);

        java.util.Queue<Integer> javaQueue = new ArrayDeque<>();

        // similar to List, the Queue from Vavr is also immutable
        // so every method will return a new method
        queue.enqueue(20);
        javaQueue.add(20);

        // because this is immutable, just returning the integer is not enough
        // the queue after removing element is also returned
        // original queue is unaffected
        Tuple2<Integer, Queue<Integer>> result = queue.dequeue();
        javaQueue.remove();

        // What happens when the Queue is empty?
        // to prevent Exceptions, we have dequeueOption
        queue.dequeueOption();
    }
}
