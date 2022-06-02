package _10_concurrency._0_summary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SomeTask implements Runnable {
    private final String name;

    public SomeTask(String s) {
        name = s;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

            for (int i = 0; i <= 5; i++) {
                //prints the initialization time for every task
                System.out.printf("Init time for %s = %s\n", name, dateFormat.format(new Date()));

                TimeUnit.SECONDS.sleep(1);
            }
            System.out.printf("%s complete\n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _7_ExecutorService {

    static final byte MAX_THREAD = 3;

    public static void main(String[] args) {

        // create five tasks
        Runnable[] runnables = {
                new SomeTask("task 1"),
                new SomeTask("task 2"),
                new SomeTask("task 3"),
                new SomeTask("task 4"),
                new SomeTask("task 5")
        };

        // creates a thread pool with MAX_THREAD no. of threads as the fixed pool size
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREAD);

        // passes the NewTask objects to the pool to execute
        for (byte i = 0; i < 5; i++) threadPool.execute(runnables[i]);

        // pool shutdown
        threadPool.shutdown();
    }
}
