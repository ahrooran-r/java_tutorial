package tutorial.learn.core._10_concurrency._6_concurrent_problems._1_dining_philosophers_problem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private final int id;
    private final Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock(true);
    }

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {

        // if I try lock.lock(), then there may be deadlock
        // because if each philosopher has one chopstick and
        // waiting for the other to finish, then there would be deadlock
        // as other one would wait as well
        // so here we use lock.trylock() to stop waiting after a given period of time
        boolean result = lock.tryLock(10, TimeUnit.MILLISECONDS);

        // if chopstick has been acquired, then result is true
        // so we print it
        System.out.printf("The philosopher %s acquired the %s chopstick %s\n", philosopher, state.getValue(), this);

        return result;
    }

    public void putDown(Philosopher philosopher, State state) throws InterruptedException {

        // if I try lock.lock(), then there may be deadlock
        // because if each philosopher has one chopstick and
        // waiting for the other to finish, then there would be deadlock
        // as other one would wait as well
        // so here we use lock.trylock() to stop waiting after a given period of time
        lock.unlock();

        // if chopstick has been acquired, then result is true
        // so we print it
        System.out.printf("The philosopher %s put down %s chopstick %s\n", philosopher, state.getValue(), this);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
