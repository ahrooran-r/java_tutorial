package _10_concurrency._2_inter_thread_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {

    private static final Lock lock = new ReentrantLock(true);
    private static final Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {

        lock.lock();
        System.out.println("Producer method...");
        condition.await();
        // this is same as initial `lock.wait()` method but with another name
        // now the lock associated with this condition is AUTOMATICALLY released!

        System.out.println("Producer again...");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer method...");
        condition.signal();
        // this is same as initial lock.notify() method but with another name

        lock.unlock();
    }
}

public class _8_ProducerConsumerWithLocks {

    public static void main(String[] args) {

        Worker p = new Worker();

        Thread t1 = new Thread(() -> {
            try {
                p.producer();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                p.consumer();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}

/*
 * Locks vs Synchronized blocks
 *
 *   we can make lock fair -> prevent thread starvation
 *   synchronized blocks are unfair by default
 *
 *   we can check whether given lock is held or not with reentrant locks
 *
 *   we can get the list of threads waiting on lock with reentrant locks
 *
 *   but synchronized blocks are nicer -> we don't need try-catch-finally blocks!
 *
 * */
