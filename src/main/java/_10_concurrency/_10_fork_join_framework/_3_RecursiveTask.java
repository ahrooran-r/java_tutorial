package _10_concurrency._10_fork_join_framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork() -> synchronously execute the given task in the pool
 * <p>
 * -> we can call this on RecursiveAction or RecursiveTask<T>
 * <p>
 * join() -> returns result of computation when it is done
 * <p>
 * invoke() -> execute the task + return its result upon completion
 */
public class _3_RecursiveTask {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask<Integer> simpleRecursiveTask = new SimpleRecursiveTask(200);

        int output = forkJoinPool.invoke(simpleRecursiveTask);

        System.out.println("Final output = " + output);
    }

}

class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private final int simulatedWork;

    public SimpleRecursiveTask(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {

        if (this.simulatedWork > 100) {

            System.out.printf("The task weight: %d is split into two for parallel execution\n", simulatedWork);

            SimpleRecursiveTask leftTask = new SimpleRecursiveTask(simulatedWork / 2);
            SimpleRecursiveTask rightTask = new SimpleRecursiveTask(simulatedWork - simulatedWork / 2);

            leftTask.fork();
            rightTask.fork();

            int solution = 0;

            // Returns the result of the computation when it is done.
            solution += leftTask.join();
            solution += rightTask.join();

            return solution;

            // suppose thread X forks threads A and B, and thread A forks threads P and Q
            // so X -> A, B and A -> P, Q
            // now solution in A is initialized to 0;
            // both P and Q return their solution to A (say p, q)
            // now solution in A = p + q
            // similarly solution in X is initialized to zero
            // now both A, B return their solutions respectively (say p + q, b)
            // so finally solution in X would be = p + q + b
            // JUST like RECURSIVE ADDITION using parallel threads...

        } else {
            int result = simulatedWork * 2;
            System.out.println("No need for parallel execution. Returning: " + result);
            return result;
        }
    }
}
