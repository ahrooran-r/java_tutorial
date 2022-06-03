package tutorial.learn.core._10_concurrency._5_concurrent_collections;

import java.util.concurrent.Exchanger;

/**
 * 2 threads can exchange object with the help of exchanger class
 * exchange() -> exchanging objects can be done via one of the two exchange() methods
 * e.g.: genetic algorithms, training neural networks...
 */
public class _7_Exchanger {
    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }
}

// two threads are going to work here on counter variable
// one thread will add counter and pass it to the other thread
// other will decrement counter

class FirstThread implements Runnable {

    private final Exchanger<Integer> exchanger;
    private int counter = 0;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("First thread incremented the counter: " + ++counter);

            // exchanging counter
            try {
                // counter is updated with value from other thread
                counter = exchanger.exchange(counter);
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {

    private final Exchanger<Integer> exchanger;
    private int counter = 0;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Second thread decremented the counter: " + --counter);

            // exchanging counter
            try {
                counter = exchanger.exchange(counter);
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
