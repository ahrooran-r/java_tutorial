package _10_concurrency._6_concurrent_problems._1_dining_philosophers_problem;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Philosopher implements Runnable {

    private final AtomicBoolean full = new AtomicBoolean(false);
    private final Random random = new Random(System.nanoTime());
    private final int id;
    private Chopstick left;
    private Chopstick right;
    private int eatingCounter;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.eatingCounter = 0;
    }

    @Override
    public void run() {

        // if he eats a lot, then break out of the loop
        while (!isFull()) {
            // if not eaten that much, then philosopher
            // would think and eat again and again
            try {
                think();

                // if both left chopstick and right chopstick
                // are picked up, then he can eat
                if (left.pickUp(this, State.LEFT) && right.pickUp(this, State.RIGHT)) {
                    eat();
                    // after eating, he should put down chopsticks in this exact order
                    // right first and left second
                    right.putDown(this, State.RIGHT);
                    left.putDown(this, State.LEFT);
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

    }

    private void think() throws InterruptedException {
        System.out.printf("Philosopher %s is thinking...\n", this);

        final int thinkingTime = random.nextInt(1000);
        Thread.sleep(thinkingTime);
    }

    private void eat() throws InterruptedException {
        System.out.printf("Philosopher %s is eating...\n", this);
        eatingCounter++;

        final int eatingTime = random.nextInt(1000);
        Thread.sleep(eatingTime);
    }

    public boolean isFull() {
        return full.get();
    }

    public void setFull() {
        full.set(true);
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
