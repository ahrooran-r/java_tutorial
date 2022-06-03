package tutorial.learn.core._10_concurrency._4_threads_with_executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

class Processor implements Callable<String> {

    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Inside call() of object");

        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));
        return "ID of this object is: " + id;
    }
}

public class _6_CallableAndFuture {
    public static void main(String[] args) {

        List<String> result = new ArrayList<>(5);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            try {

                Future<String> output = executor.submit(new Processor(i));
                result.add(output.get());

            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
        }

        System.out.println(result);

        executor.shutdown();
    }
}
