package _10_concurrency._3_multi_threading_concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

class Terminator implements Runnable {

    // using Thread.stop() is depreciated
    // Hence following action is most suitable

    private volatile boolean terminated = false;

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Do some action!");
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
        }
        System.out.println("Ending loop; terminated = " + terminated);
    }
}

public class _1_VolatileKeywordAndTerminatingThreads {
    public static void main(String[] args) {

        Terminator t = new Terminator();

        Thread executor = new Thread(t);

        Thread configurator = new Thread(() -> {
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(2000));
            System.out.println("Setting terminator to true");
            t.setTerminated(true);
        });

        executor.start();
        configurator.start();

        try {
            // main thread joins executor
            executor.join();
            // main thread joins configurator
            configurator.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
