package tutorial.learn.collections.agrona;

import org.agrona.concurrent.EpochClock;
import org.agrona.concurrent.SystemEpochClock;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * A duty cycle is the primary loop a system component undertakes.
 * In the loop some tasks are performed and then it may optionally wait for some period of time.
 */
public class _1_DutyCycles {

    public static void main(String[] args) throws ParseException, InterruptedException {

        EpochClock clock = new SystemEpochClock();
        while (true) {
            System.out.println("Do Something");
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        }

        /*
         * The duty cycle directly impacts the service's capability in terms of messages processed
         * per second as well CPU consumption of the process.
         * What we want is to optimize the sleep strategy to minimize waiting time
         * Inside Agrona Agents, with the sleep of the above sample managed by the Idle Strategy.
         */

        /*
         * Business logic duty cycle
         * This is a typical duty cycle driven by some input message. To achieve a high throughput, the sleep (that is, idle strategy) applied would spend very little to no time delaying the duty cycle.
         *
         * while (true)
         * {
         *     Command command = demuxInputBuffer();
         *     routeToAppropriateBusinessLogic(command);
         * }
         */

        /*
         * Connectivity duty cycle
         * This duty cycle is not driven by messages, but rather by a desire to manage connectivity to something.
         * There is little point in this being called thousands of times a second,
         * so the sleep applied in the duty cycle could be in the hundreds of milliseconds.
         *
         * EpochClock clock = new SystemEpochClock();
         * long openConnectionTime = [some value];
         * long closeConnectionTime = [some value];
         *
         * while (true)
         * {
         *     long now = clock.time();
         *     if (!connected)
         *     {
         *         if (now >= openConnectionTime && now < closeConnectionTime)
         *         {
         *             connect();
         *         }
         *     }
         *     else
         *     {
         *         if (now < openConnectionTime || now >= closeConnectionTime)
         *         {
         *             disconnect();
         *         }
         *     }
         * }
         *
         */
    }
}
