package tutorial.learn.core._10_concurrency._1_thread_manipulation;

abstract class Runner {

    public void execute() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getClass().getSimpleName() + ": " + i);
        }
    }
}

class Runner1 extends Runner {
}

class Runner2 extends Runner {
}

public class _1_SequentialProcessing {
    public static void main(String[] args) {

        Runner runner1 = new Runner1();
        Runner runner2 = new Runner2();

        runner1.execute();
        runner2.execute();
    }
}