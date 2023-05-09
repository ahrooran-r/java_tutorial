package tutorial.learn.core._10_concurrency._0_summary;

// this method is most preferable
// as work and worker are different activities, separating them would be good in OOP standpoint

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

// first way of implementing concurrency
// implement Runnable interface
class Task implements Runnable {
    private static void innerMethod_1() {
        System.out.println("Inside innerMethod_1");
        innerMethod_2();
    }

    private static void innerMethod_2() {
        System.out.println("Inside innerMethod_2");
    }

    @Override
    public void run() {
        System.out.println("Inside run");
        innerMethod_1();
    }
}

public class _1_ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        Task task = new Task();

        // initialize a thread and pass `task` object
        // here `thread` is the worker and `task` is the work
        Thread thread = new Thread(task);

        // start the thread
        // nothing happens until it is started
        thread.start();

        // sleep a thread

        // a static method
        // stops current thread for certain time
        Thread.sleep(1000);
        // disadvantage -> time unit can only be given in milliseconds

        // better way
        TimeUnit.SECONDS.sleep(3);
        // the main method itself runs as a thread
        // so there are two threads running here

        System.out.println("Inside main");
    }
}
