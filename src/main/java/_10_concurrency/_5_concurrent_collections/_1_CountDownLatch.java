package _10_concurrency._5_concurrent_collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * used to synchronize one or more tasks by forcing them to wait for the completion
 * of set of operations being performed by other tasks
 * <p></p>
 * you give an initial count to the {@link CountDownLatch} object
 * then any task that calls await() on that object will wait until the count reaches zero
 * <p>
 * other tasks may call countdown() on the object to reduce the count, for example when they finish
 * -> the count cannot be reset
 * -> {@link java.util.concurrent.CyclicBarrier} can have the reset count functionality
 * <p>
 * tasks that call {@link CountDownLatch#countDown()} are not blocked from executing further
 * <p>
 * only tasks that call {@link CountDownLatch#await()} are blocked until the countdown reaches zero
 * <p>
 * Use case:
 * Divide a task into N solvable sub-tasks
 * <p>
 * -> Create a CountDownLatch with value of N
 * <p>
 * -> When each task is finished, it calls countdown() on the latch
 * <p>
 * -> Tasks waiting for the problem to be solved (the other tasks) calls an await() on the latch to
 * <p>
 * -> hold themselves back until it is completed
 * <p>
 * e.g.: do something (say delete the image) after an image is downloaded 10_000 times...
 * <p>
 * -> so divide 10,000 into 100 sub tasks
 * <p>
 * -> do an await on delete() method and countdown() on each sub-task at the end
 * <p>
 * -> now once all sub tasks are completed, delete() method will run
 * <p>
 * -> until then it is blocked at the gate of CountDownLatch
 */
public class _1_CountDownLatch {
    public static void main(String[] args) {

        Downloader downloader = new Downloader();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(downloader::subTask);
        }
        executor.execute(downloader::delete);

        executor.shutdown();
    }
}

class Downloader {

    private final CountDownLatch latch = new CountDownLatch(10);

    private void download() {
        System.out.println("Image downloaded...by task: "
                + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId());
        try {
            Thread.sleep(300);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    // now 10 sub-tasks running concurrently can achieve image count of 100
    // if each sub-task runs 10 times!
    // so this method is going to run in 10 threads concurrently
    public void subTask() {
        for (int i = 0; i < 10; i++) download();
        latch.countDown();

        System.out.println("SubTask " + Thread.currentThread().getName()
                + " id: " + Thread.currentThread().getId() + " completed!\n");
    }

    // delete method waits until all subtasks are completed
    // so now CountDownLatch is at 0
    // then this method run
    // this method is going to run in a separate thread concurrent to sub-task threads
    public void delete() {
        try {
            latch.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("current latch count: " + latch.getCount() + " deleting image!");
    }

    /*
     * e.g.:
     * consider in a family of father, mother and 3 children and food is there for all
     * suppose father takes food at last (four people are waiting for the food
     * father thread and 3 children thread -> altogether 4 threads are running at the same time)
     * so a latch is created with count 3 and each child should count down the latch once taken the food
     * father will be blocked and watch the latch until countdown hits 0
     * then he will take his part
     *
     * this is used to control threads as groups by executing a group while other is blocked and waiting!!!
     * */
}
