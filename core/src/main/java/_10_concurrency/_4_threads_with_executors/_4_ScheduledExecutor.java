package _10_concurrency._4_threads_with_executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

class StockBroker implements Runnable {
    @Override
    public void run() {
        System.out.println("Downloading new stock details...");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
    }
}

public class _4_ScheduledExecutor {
    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(new StockBroker(), 0, 1000, TimeUnit.MILLISECONDS);
    }
}
