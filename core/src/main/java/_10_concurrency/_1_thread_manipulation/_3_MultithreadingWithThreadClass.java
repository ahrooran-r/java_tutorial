package _10_concurrency._1_thread_manipulation;

abstract class ThreadRunner extends Thread {

    private void execute() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getClass().getSimpleName() + ": " + i);

            try {
                Thread.sleep(1000);
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

class ThreadRunner1 extends ThreadRunner {
}

class ThreadRunner2 extends ThreadRunner {
}

public class _3_MultithreadingWithThreadClass {
    public static void main(String[] args) {

        // create Threads
        Thread runner1 = new ThreadRunner1();
        Thread runner2 = new ThreadRunner2();

        runner1.start();
        runner2.start();

    }
}
