package tutorial.learn.core._10_concurrency._3_multi_threading_concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _4_LivelockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        _4_LivelockExample example = new _4_LivelockExample();

        new Thread(example::worker1, "worker1").start();
        new Thread(example::worker2, "worker2").start();
    }

    public void worker1() {

        while (true) {

            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("worker1 has acquired lock1");

            System.out.println("worker1 tries to acquire lock2");
            if (lock2.tryLock()) {
                System.out.println("worker1 has acquired lock2");
                lock2.unlock();
            }
            else {
                System.out.println("worker1 has not acquired lock2");
                continue;
            }

            break;
        }

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {

        while (true) {

            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("worker2 has acquired lock2");

            System.out.println("worker2 tries to acquire lock1");
            if (lock1.tryLock()) {
                System.out.println("worker2 has acquired lock1");
                lock1.unlock();
            }
            else {
                System.out.println("worker2 has not acquired lock2");
                continue;
            }

            break;
        }

        lock1.unlock();
        lock2.unlock();
    }
}
