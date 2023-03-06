package tutorial.learn.guava._3_concurrency;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * RateLimiter class is a construct that allows us to regulate the rate at which some processing happens.
 * If we create a RateLimiter with N permits – it means that process can issue at most N permits per second.
 * <p>
 * <a href="https://www.baeldung.com/guava-rate-limiter">tutorial</a>
 */
public class _1_RateLimiterClass {
    public static void main(String[] args) {

        // limit the rate of execution of the doSomeLimitedOperation() to 2 times per second.
        RateLimiter rateLimiter = RateLimiter.create(2);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                rateLimiter.acquire(1);
                // doSomeLimitedOperation()

                rateLimiter.tryAcquire(1, 2, TimeUnit.SECONDS);
                // doSomeLimitedOperation()
            }
        });
    }
}
