package tutorial.learn.core._10_concurrency._4_threads_with_executors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    private static final Random random = new Random();
    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.printf("Task with id: %d is executed by thread id: %d thread name: %s\n", id, Thread.currentThread().getId(), Thread.currentThread().getName());
        long randTime = random.nextInt(4) + 1;

        try {
            TimeUnit.SECONDS.sleep(randTime);
            // in this case, an interrupted exception can be thrown as well
            // so we have to manually cause thread interrupt in catch part
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}

public class _2_SingleThreadExecutor {
    public static void main(String[] args) {

        // a single thread that will execute task sequentially one after the other
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 3; i++) {
            executor.execute(new Task(i));

            // this will not stop! because we have to shutdown executor manually
        }

        // to shutdown, we first have to shutdown executor itself
        executor.shutdown();

        // then shutdown actual running threads
        try {

            // this will wait till all threads did their tasks, or timeout occurs
            // whichever comes first
            // also it will throw interrupted exception, if the current thread causes it
            // the result boolean value will reflect on this accordingly
            boolean result = executor.awaitTermination(1000, TimeUnit.MILLISECONDS);

            // now if result is false, this means either timeout occurred or interrupted exception thrown
            // then we have to force shutdown all executing threads
            if (!result) {
                System.out.println("Forcing shutdown because of time out");
                executor.shutdownNow();
            }

        } catch (InterruptedException exception) {

            // in case of exception thrown, we have to force shutdown
            executor.shutdownNow();
        }

    }
}
