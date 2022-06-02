package _10_concurrency._3_multi_threading_concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class _3_DeadlockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        _3_DeadlockExample example = new _3_DeadlockExample();

        new Thread(example::worker1, "worker1").start();
        new Thread(example::worker2, "worker2").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("worker1 has lock1");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(300));

        lock2.lock();
        System.out.println("worker1 has lock2 now");

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock();
        System.out.println("worker2 has lock2");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(300));

        lock1.lock();
        System.out.println("worker2 has lock1 now");

        lock1.unlock();
        lock2.unlock();
    }
}
