package _10_concurrency._2_inter_thread_communication;

import lombok.SneakyThrows;

public class _1_MemoryManagementIssues {

    public static int sum = 0;

    @SneakyThrows
    public static int process() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sum++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sum++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return sum;
    }

    public static void reset() {
        sum = 0;
    }

    public static void main(String[] args) {
        int iter = 0;
        while (true) {
            iter++;
            int result = process();
            if (result == 200) reset();
            else {
                System.out.printf("Sum is: %d, num. of iterations taken is: %d", sum, iter);
                return;
            }
        }

    }
}
