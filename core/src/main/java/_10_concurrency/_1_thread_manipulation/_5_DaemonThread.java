package _10_concurrency._1_thread_manipulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

class DaemonWorker implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Daemon Thread is running");
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));
        }
    }
}

class NormalWorker implements Runnable {
    @Override
    public void run() {
        System.out.println("Normal Thread is running");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(3000));
    }
}

public class _5_DaemonThread {
    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread);
        // returns [thread_name,thread_priority,group_name]
        // in this case, that would be `Thread[main,5,main]`

        Thread daemonThread = new Thread(new DaemonWorker());
        daemonThread.setDaemon(true);

        Thread normalThread = new Thread(new NormalWorker());

        daemonThread.start();
        normalThread.start();

        // JVM will terminate all daemon threads when all worker threads are terminated
        // when after 3 seconds, normalThread terminates, daemonThread should also terminate with it
        // so we can expect one line from normalThread and 3 lines from daemonThread
    }
}
