package _10_concurrency._0_summary;

// synchronized is way to provide concurrency but it can only be used in methods
// to make a variable thread safe, the keyword `volatile` is used
// volatile makes sure that any thread that reads the variable will get the most recent value from the variable
// it makes the variable to be directly accessible from memory and prevents caching

// synchronized -> guarantees both visibility and mutual exclusion
// volatile -> can only guarantee only visibility

// places to use volatile
// => when one thread writes and other threads read from the variable
//     -> No race condition -> hence volatile can be used

import java.util.concurrent.TimeUnit;

public class _5_VolatileVariable {

    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) System.out.println("In while ...");
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(1);
        stop = true;
    }
}

// can also be achieved by using synchronized

// public class VolatileVariable_5 {
//
//     private static boolean stop = false;
//
//     private static synchronized void requestStop() {
//         stop = true;
//     }
//
//     private static synchronized boolean getStop() {
//         return stop;
//     }
//
//     public static void main(String[] args) throws InterruptedException {
//
//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 while (!getStop()) System.out.println("In while ...");
//             }
//         }).start();
//
//         TimeUnit.MILLISECONDS.sleep(1);
//         requestStop();
//     }
// }
