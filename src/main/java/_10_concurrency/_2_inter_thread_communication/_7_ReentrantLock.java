package _10_concurrency._2_inter_thread_communication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _7_ReentrantLock {

    private static final Lock lock = new ReentrantLock(true);
    private static int counter = 0;

    public static void increment() {
        // this is equivalent to synchronized block
        //lock.lock();
        //for (int i = 0; i < 10000; i++) counter++;
        //lock.unlock();
        // BUT what of the inner code throws exception,
        // then unlock method may not execute and it will be a deadlock situation for other thread

        // therefore always use this lock with try catch block
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) counter++;
        } finally {
            lock.unlock();
            // this makes sure we're going to unlock no matter the exception
        }

        // THE ADVANTAGE of this is that we can lock on one part of code
        // and unlock on another part of code, say another method
        // BUT with synchronized keyword, all code should be in one single block!!!
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(_7_ReentrantLock::increment);

        Thread t2 = new Thread(_7_ReentrantLock::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("Counter: " + counter);
    }
}
