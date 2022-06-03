package tutorial.learn.core._10_concurrency._0_summary;

class EmailCampaign implements Runnable {

    @Override
    public void run() {
        System.out.println("Inside EmailCampaign run method");

        for (int i = 0; i < 10; i++) {

            // gets the name of thread set up in main method
            System.out.println(Thread.currentThread().getName());

            // yield() -> A hint to the scheduler that the current thread is willing to yield its current use of a processor.
            //            The scheduler is free to ignore this hint.
            if (i == 5) Thread.yield();
        }
    }
}

class DataAggregator implements Runnable {

    @Override
    public void run() {
        System.out.println("Inside DataAggregator run method");

        for (int i = 0; i < 10; i++) System.out.println(Thread.currentThread().getName());
    }
}

public class _3_ThreadPriority {

    public static void main(String[] args) {

        // Thread.currentThread() -> Returns a reference to the currently executing thread object.
        System.out.println(Thread.currentThread().toString());
        // -> Thread[main,5,main]

        // `main` -> name of the thread
        // `5` -> thread priority => have a default priority of 5
        // `main` -> thread group => obsolete now => Avoid thread groups

        Thread t1 = new Thread(new EmailCampaign());
        Thread t2 = new Thread(new DataAggregator());

        // set name for threads
        t1.setName("EmailCampaign");
        t2.setName("DataAggregator");

        // setPriority() -> Changes the priority of this thread.
        // MAX_PRIORITY = 10, MIN_PRIORITY = 1, NORM_PRIORITY = 5
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

        try {
            // this join() method is written on `main` method -> which means on `main` thread
            // hence `main` thread is suspended until `t2` dies
            // 1 ms means -> scheduler waits at most 1 ms for `t2` thread to die.
            t2.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Inside main");
    }
}
