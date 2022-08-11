package tutorial.learn.core._10_concurrency._1_thread_manipulation;

abstract class NewRunner implements Runnable {

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

class NewRunner1 extends NewRunner {
}

class NewRunner2 extends NewRunner {
}

public class _2_MultithreadingWithRunnable {
    public static void main(String[] args) {

        // create Threads
        Thread runner1 = new Thread(new NewRunner1());
        Thread runner2 = new Thread(new NewRunner2());

        runner1.start();
        runner2.start();

        // NOTE: this is not parallel execution, just time slicing

        // Parallel vs Time-slicing
        // In parallel programming, two different cores execute two threads at the same exact time
        // With time slicing, only one core is employed. When one thread is executing, other one has to wait
    }
}
