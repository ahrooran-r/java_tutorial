package tutorial.learn.core._10_concurrency._2_inter_thread_communication;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {

    private static final Lock lock = new ReentrantLock(true);

    /*
    Usually every object has a `wait()` and `notify()` methods associated with it (see _6_ProducerConsumer.java)
    These methods come from Object class which every subclass extends from implicitly
    Since `Lock lock` also extends Object class, it also has such methods
    Here we associate a Condition class to sort of extract those methods and put it into Condition class
    Now the code is much cleaner to write and Condition class provides some additional methods as well

    Condition factors out the Object monitor methods (wait, notify and notifyAll) into distinct objects to give
    the effect of having multiple wait-sets per object, by combining them with the use of arbitrary Lock
    implementations. Where a Lock replaces the use of synchronized methods and statements, a Condition replaces
    the use of the Object monitor methods.

    See javadoc of Condition for further details
    */
    private static final Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {

        lock.lock();
        System.out.println("Producer method...");
        condition.await();
        // this is same as initial `lock.wait()` or `this.wait()` or `obj.wait()` method but with another name
        // now the lock associated with this condition is AUTOMATICALLY released!
        // and thread is put into a wait stage

        System.out.println("Producer again...");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer method...");
        condition.signal();
        // this is same as initial lock.notify() method but with another name

        System.out.println("Releasing lock on consumer");
        lock.unlock();
    }
}

public class _8_ProducerConsumerWithLocks {

    @SneakyThrows
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
        Thread.sleep(100);
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
