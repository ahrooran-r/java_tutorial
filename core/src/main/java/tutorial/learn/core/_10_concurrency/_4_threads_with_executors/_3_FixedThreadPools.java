package tutorial.learn.core._10_concurrency._4_threads_with_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _3_FixedThreadPools {
    public static void main(String[] args) {

        // a single thread that will execute task sequentially one after the other
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Task(i));
            // you can see only 3 thread ids are shown again and again
        }

    }
}
