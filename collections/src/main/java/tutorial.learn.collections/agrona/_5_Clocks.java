package tutorial.learn.collections.agrona;

import org.agrona.concurrent.*;

public class _5_Clocks {
    public static void main(String[] args) {

        // https://aeroncookbook.com/agrona/clocks/

        // Internally, it returns Java's System.currentTimeMillis()
        EpochClock systemEpochClock = SystemEpochClock.INSTANCE;
        systemEpochClock.time();

        CachedEpochClock clock = new CachedEpochClock();
        clock.update(2L);
        clock.advance(98L);
        clock.time(); //returns 100L

        EpochMicroClock microClock = new SystemEpochMicroClock();
        microClock.microTime();

        // SystemEpochNanoClock uses the same underlying technique as SystemEpochMicroClock,
        // and returns the nano epoch as derived from Instant.
        EpochNanoClock nanoClock = new SystemEpochNanoClock();
        nanoClock.nanoTime();

        // OffsetEpochNanoClock performs sampling of System.nanoTime() at a defined interval
        // to produce an accurate nano time clock in pure Java.
        // The sampling interval and measurement parameters can be optionally overridden.
        EpochNanoClock offsetEpochNanoClock = new OffsetEpochNanoClock();
        offsetEpochNanoClock.nanoTime();
    }
}
