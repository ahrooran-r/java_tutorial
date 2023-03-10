package tutorial.learn.collections.agrona;

import org.agrona.concurrent.IdleStrategy;

/**
 * showing the key logic where the workCount value is checked. If workCount is zero, the agent backs off.
 * <p>
 * Not all idle strategies are thread safe.
 * It is recommended to generally have a distinct idle strategy per agent that is scheduled.
 */
public class _3_CustomIdleStrategy implements IdleStrategy {

    @Override
    public void idle(int workCount) {
        idle();
    }

    @Override
    public void idle() {
        System.out.println("Yielding thread -> " + Thread.currentThread().getName());

        // yield() provides a mechanism to inform the “scheduler” that the current thread is willing to relinquish
        // its current use of processor, but it'd like to be scheduled back soon as possible.

        /*
         * yield() vs sleep()
         * While yield() can only make a heuristic attempt to suspend the execution of the current thread
         * with no guarantee of when will it be scheduled back,
         * sleep() can force the scheduler to suspend the execution of the current thread for at least
         * the mentioned time period as its parameter.
         */

        /*
         * yield() vs join()
         * The current thread can invoke join() on any other thread which makes the current thread
         * wait for the other thread to die before proceeding
         * Optionally it can mention a time period as its parameter which indicates the maximum time
         * for which the current thread should wait before resuming
         */

        Thread.yield();
    }

    @Override
    public void reset() {
        System.out.println("Resetting...");
    }

    @Override
    public String alias() {
        return IdleStrategy.super.alias();
    }

    /*
     * Name	                        Implementation Details
     *
     * SleepingIdleStrategy	        Uses parkNanos to park the thread for the given time period
     *
     * SleepingMillisIdleStrategy	Uses thread.sleep to idle the thread for the given time period.
     *                              Good for using when developing locally on a lower spec machine,
     *                              or with a large number of processes.
     *
     * YieldingIdleStrategy	        Uses thread.yield to yield control of the thread
     *
     * BackoffIdleStrategy	        An aggressive strategy that backs off from spinning to yielding
     *                              and then to parking for a configurable period of nanos.
     *                              This is the default strategy for Aeron Cluster. <-
     *
     * NoOpIdleStrategy	            The most aggressive idle strategy available. This never idles.
     *
     * BusySpinIdleStrategy	        This will call java.lang.Thread.onSpinWait() if available on the running JVM
     *                              i.e. the JVM is running Java 9+.
     *                              This provides a weak hint to the CPU that the thread is in a tight loop
     *                              but busy waiting for something, and the CPU may then assign additional resources
     *                              to another thread without involving the OS scheduler.
     */
}
