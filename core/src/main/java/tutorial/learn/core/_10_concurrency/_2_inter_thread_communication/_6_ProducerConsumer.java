package tutorial.learn.core._10_concurrency._2_inter_thread_communication;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;

    // Instead of using `this` (as shown in _5_WaitAndNotify.java), I'm using a simple Object
    // Both approaches are same
    // When using this approach -> make sure to synchronize with the Object and not `this` <- This is a mistake people make
    private static final Object obj = new Object();

    private final List<Integer> list = new ArrayList<>(UPPER_LIMIT);

    public void producer() throws InterruptedException {

        synchronized (obj) {
            while (true) {

                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items");
                    obj.wait();

                } else {
                    list.add(0);
                    System.out.printf("Adding 0 to list, current size: %d\n", list.size());
                    obj.notify();
                    // we can call notify
                    // it will be notified only if other thread is in waiting stage
                    // and there are no more steps to be executed by the current thread
                }

                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (obj) {
            while (true) {

                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items");
                    obj.wait();

                } else {
                    list.remove(list.size() - 1);
                    System.out.printf("Removing last element, current size: %d\n", list.size());
                    obj.notify();
                }

                Thread.sleep(500);
            }
        }
    }
}

public class _6_ProducerConsumer {

    public static void main(String[] args) {

        Processor p = new Processor();

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
    }
}
