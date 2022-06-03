package tutorial.learn.core._10_concurrency._1_thread_manipulation;

abstract class WaitRunner extends Thread {

    final int timeout;

    public WaitRunner(int timeout) {
        this.timeout = timeout;
    }

    private void execute() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getClass().getSimpleName() + ": " + i);

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Starting " + this.getClass().getSimpleName() + " in new Thread");
        execute();
    }
}

class WaitRunner1 extends WaitRunner {
    public WaitRunner1(int timeout) {
        super(timeout);
    }
}

class WaitRunner2 extends WaitRunner {
    public WaitRunner2(int timeout) {
        super(timeout);
    }
}

public class _4_WaitForThreadToFinish {
    public static void main(String[] args) {

        Thread w1 = new WaitRunner1(100);
        Thread w2 = new WaitRunner2(1000);

        w1.start();
        w2.start();

        // we can wait for a thread to finish
        try {
            w1.join();

            // Now current 'main' thread is going to wait until w1 finishes
            // Then it is going to run after w1 finishes
            // in nutshell 'main' thread is going to JOIN w1

            // I'm telling the thread that executes this code (which is the MAIN thread)
            // To join W1 -> To wait till W1 completes and then execute
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("This line is executed by MAIN thread");

        // A Thread `dies` if all the lines in run() method are executed!
    }
}
