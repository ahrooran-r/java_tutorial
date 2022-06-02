package _10_concurrency._2_inter_thread_communication;

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer is executing!");
            // see doc
            this.wait(); // or simply wait();
            // now this thread switched to a `waiting stage`.
            // its intrinsic lock is released.
            // Since consume() thread is waiting for the lock, that thread acquires the lock and work on it
            System.out.println("Producer is executing again!");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            // once this thread gets the lock, it will run the synchronized block

            System.out.println("Consumer is executing!");
            this.notify(); // or simply notify();
            // once notify() is executed it tells the waiting thread
            // to continue execution (see doc for deep info)
            // However lock is not released yet

            Thread.sleep(1000);
            System.out.println("Consumer is closing now");
        }
        // now that lock is released, waiting thread can acquire lock and continue execution
    }
}

/**
 * How to release lock and notify other threads in java
 * <p>
 * Threads that are locking on the same intrinsic lock can
 * release the lock until other thread calls notify.
 * <p>
 * If we use only synchronized keyword, then it is UP TO the
 * time slicing algorithm to switch threads!
 * <p>
 * However with wait() and notify() methods, we can MANUALLY
 * tell other threads to wait while this thread finishes execution.
 */
public class _5_WaitAndNotify {

    public static void main(String[] args) {

        Process p = new Process();

        Thread t1 = new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
