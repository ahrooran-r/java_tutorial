package _10_concurrency._5_concurrent_collections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Consider a group of threads where single thread has to perform task A and B in order
 * But if it finishes task A before other threads in its group, then it has to wait till
 * all others have finished the task
 * Then it will start executing task B with the other threads at the same time
 * A {@link CyclicBarrier} is used when you want to create a group of tasks to perform
 * some tasks in parallel + wait until they are all finished before
 * moving onto the next step
 * <p>
 * -> something like join
 * <p>
 * -> something like CountDownLatch
 * <p>
 * CountDownLatch -> one shot event
 * <p>
 * CyclicBarrier -> can be used again and again
 * <p>
 * -> plus this has a barrier action: which is a runnable
 * <p>
 * -> can be used to trigger a method once countdown reaches zero
 * <p>
 * -> we cannot reset latches, but we can reset cyclic barriers with reset()
 * <p>
 * -> new CyclicBarrier(N) => N threads will wait for each other
 * <p>
 * e.g.:
 * consider a family where all 4 of us eating
 * now father and mother eat fast and could finish the plate before children
 * <p>
 * but children are slow
 * so father and mother wait in their seat patiently for children to finish,
 * so they all can wash their plates together
 * <p>
 * here task A -> eating and task B -> washing plate
 * although parents finish task A quick, they wait for children to join in with task B
 * <p>
 * <b>
 * CyclicBarrier allows a number of threads to wait on each other,
 * whereas CountDownLatch allows one or more threads to wait for a number of tasks to complete.
 * <p>
 * In short, CyclicBarrier maintains a count of threads whereas CountDownLatch maintains a count of tasks.
 * </b>
 */
public class _2_CyclicBarrier {
    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("All threads finished the task!"));
        Family family = new Family(cyclicBarrier);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 1; i <= 4; i++) {
            executor.execute(() -> family.eat(Thread.currentThread().getId()));
        }

        executor.shutdown();
    }
}

class Family {

    private static final Random random = new Random();
    private final CyclicBarrier cyclicBarrier;

    public Family(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void eat(long id) {
        System.out.println("Thread with id: " + id + " starts to eat!");
        try {
            Thread.sleep(300 + random.nextInt(700));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("Thread with id: " + id + " finished eating!");

        try {
            // now the thread has to wait for other threads to finish
            cyclicBarrier.await();

            // once all threads finish eating, they should start washing dishes
            washDishes(id);
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }
    }

    private void washDishes(long id) {
        System.out.println("Thread with id: " + id + " starts washing the dishes!");
        try {
            Thread.sleep(300 + random.nextInt(700));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("Thread with id: " + id + " finished washing dishes!");
    }
}


