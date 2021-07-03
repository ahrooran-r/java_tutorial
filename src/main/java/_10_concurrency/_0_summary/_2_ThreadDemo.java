package _10_concurrency._0_summary;

// second way of implementing concurrency
// extend Thread class
class NewTask extends Thread {
    @Override
    public void run() {
        System.out.println("Inside run");
        innerMethod_1();
    }

    private static void innerMethod_1() {
        System.out.println("Inside innerMethod_1");
        innerMethod_2();
    }

    private static void innerMethod_2() {
        System.out.println("Inside innerMethod_2");
    }
}

public class _2_ThreadDemo {
    public static void main(String[] args) {

        // we can directly start the NewTask class as a thread
        NewTask task = new NewTask();
        task.start();

        System.out.println("Inside main");

    }
}
