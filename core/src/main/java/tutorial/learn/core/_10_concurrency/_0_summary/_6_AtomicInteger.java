package tutorial.learn.core._10_concurrency._0_summary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MeetUpEvent {

    private String name;
    private AtomicInteger count = new AtomicInteger(1);

    public MeetUpEvent(String name) {
        this.name = name;
    }

    public void attending(int guestCount) {
        if (guestCount == 1)

            // incrementAndGet() -> Atomically increments the current value,
            this.count.incrementAndGet();

        else if (guestCount > 1)

            // Atomically adds the given value to the current value
            this.count.addAndGet(guestCount);
    }

    public void notAttending(int guestCount) {
        if (guestCount == 1)

            // decrementAndGet() -> Atomically decrements the current value,
            this.count.decrementAndGet();

        else if (guestCount > 1) {
            boolean updated = false;
            while (!updated) {

                // Returns the current value
                short currentCount = (short) this.count.get();

                short newCount = (short) (currentCount - guestCount);

                // Atomically sets the value to new value if the current value == expectedValue
                // return true if successful.
                // False return indicates that the actual value was not equal to the expected value.
                updated = count.compareAndSet(currentCount, newCount);
            }

            // https://stackoverflow.com/a/37716606/10582056
            // Method local variables are thread safe: https://stackoverflow.com/a/12825906/10582056
        }
    }

    public int getCount() {
        return this.count.get();
    }
}

public class _6_AtomicInteger {

    private static void sleep(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MeetUpEvent meetup = new MeetUpEvent("Java User Group");

        Thread user_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                meetup.attending(4);
                System.out.printf("%s => Attending : %d\n", Thread.currentThread().getName(), meetup.getCount());
            }
        });

        Thread user_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                meetup.attending(4);
                System.out.printf("%s => Attending : %d\n", Thread.currentThread().getName(), meetup.getCount());
                meetup.notAttending(3);
                System.out.printf("%s => Attending : %d\n", Thread.currentThread().getName(), meetup.getCount());
            }
        });

        Thread user_3 = new Thread(new Runnable() {
            @Override
            public void run() {
                meetup.attending(1);
                System.out.printf("%s => Attending : %d\n", Thread.currentThread().getName(), meetup.getCount());
            }
        });

        user_1.setName("User 1");
        user_2.setName("User 2");
        user_3.setName("User 3");

        user_1.start();
        sleep(1);
        user_2.start();
        sleep(2);
        user_3.start();
        sleep(2);

        System.out.printf("%s => Total attending : %d\n", Thread.currentThread().getName(), meetup.getCount());
    }
}
