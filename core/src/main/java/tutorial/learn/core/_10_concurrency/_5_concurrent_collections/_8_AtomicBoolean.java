package tutorial.learn.core._10_concurrency._5_concurrent_collections;

import java.util.concurrent.atomic.AtomicBoolean;

public class _8_AtomicBoolean {
    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        // Returns the value of a variable, with memory semantics of reading
        // as if the variable was declared volatile.
        atomicBoolean.get();

        // Atomically sets the value to newValue if the current value == expectedValue,
        // with memory semantics of setting as if the variable was declared volatile.
        // Useful when we first take the value -> do some operations -> later want to update
        // Returns `true` if successful.
        // https://stackoverflow.com/a/37716606/10582056
        atomicBoolean.compareAndSet(false, true);

        // Sets the value to newValue, with memory semantics of setting
        // as if the variable was declared volatile.
        atomicBoolean.set(true);

        /*
        This is a niche method that is sometimes useful when fine-tuning code using non-blocking data structures.
        The semantics are that the write is guaranteed not to be re-ordered with any previous WRITE,
        but may be reordered with SUBSEQUENT OPERATIONS (or equivalently, might not be visible to other threads)
        until some other volatile write or synchronizing action occurs).

        You can think about lazySet() as "semi" volatile: it's basically a non-volatile variable in terms of
        reading by other threads, i.e. the value set by lazySet may not be visible to to other threads.
        But it becomes volatile when another write operation occurs (may be from other threads).
        The only impact of lazySet I can imagine is compareAndSet.

        So if you use lazySet(), get() from other threads may still get the old value, but compareAndSet() will
        always have the new value since it is a write operation.

        https://stackoverflow.com/a/30455785/10582056

        LMAX disruptor uses it
         */
        atomicBoolean.lazySet(true);

        // Atomically sets the value to newValue and returns the old value,
        // with memory semantics of setting as if the variable was declared volatile.
        // https://stackoverflow.com/a/64914933/10582056
        atomicBoolean.getAndSet(true);

    }
}
