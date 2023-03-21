package tutorial.learn.core._10_concurrency._10_virtual_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executors = Executors.newVirtualThreadPerTaskExecutor();

    }
}
