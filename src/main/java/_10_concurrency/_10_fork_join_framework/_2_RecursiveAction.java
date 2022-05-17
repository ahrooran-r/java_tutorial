package _10_concurrency._10_fork_join_framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * fork() -> synchronously execute the given task in the pool
 * <p>
 * -> we can call this on RecursiveAction or RecursiveTask<T>
 * <p>
 * join() -> returns result of computation when it is done
 * <p>
 * invoke() -> execute the task + return its result upon completion
 */
public class _2_RecursiveAction {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(240);

        forkJoinPool.invoke(simpleRecursiveAction);
    }

}

class SimpleRecursiveAction extends RecursiveAction {

    private final int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        // we have to decide whether the workload is too big to execute
        // -> i.e., whether to divide it into further sub-tasks
        // or to execute the workload within this task!
        if (this.simulatedWork > 100) {
            System.out.printf("The task weight: %d is split into two for parallel execution\n", simulatedWork);

            // just like we divided parallel merge sort into two section
            SimpleRecursiveAction leftSection = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction rightSection = new SimpleRecursiveAction(simulatedWork - simulatedWork / 2);

            // Arranges to asynchronously execute this task
            // in the pool the current task is running in
            leftSection.fork();
            rightSection.fork();

        } else {
            System.out.printf("No need for executing task weight: %s in parallel\n", simulatedWork);

            // the BUSINESS LOGIC comes here
        }
    }
}
