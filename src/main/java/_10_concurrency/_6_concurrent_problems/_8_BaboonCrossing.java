package _10_concurrency._6_concurrent_problems;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

enum Direction {
    FORWARD,
    BACKWARD,
    NONE
}

class Rope {
    private static final int CAPACITY = 5;

    @Getter
    private final Semaphore capacity = new Semaphore(CAPACITY, true);

    // let 0 -> not set, 1 -> forward direction, 2 -> backward direction
    @Getter
    @Setter
    private Direction direction = Direction.NONE;

    public synchronized boolean isSecure(Direction direction) {
        return this.direction == Direction.NONE || (this.direction == direction && capacity.availablePermits() <= CAPACITY);
    }
}

class Baboon {

    @SneakyThrows
    public void mount(Rope rope, Direction direction) {
        synchronized (Baboon.class) {
            if (rope.isSecure(direction)) {
                rope.setDirection(direction);
                rope.getCapacity().acquire();
                System.out.println("Baboon acquired ROPE. Direction: " + direction.name());
            }
        }
    }

    public void unmount(Rope rope) {
        synchronized (Baboon.class) {
            rope.getCapacity().release();
            System.out.println("Baboon unmounted ROPE");
        }
    }
}

/**
 * https://www.eiffel.org/doc/solutions/Baboon_crossing
 */
public class _8_BaboonCrossing {

    public static void main(String[] args) {

        Rope rope = new Rope();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Baboon baboon = new Baboon();

                    int direction = ThreadLocalRandom.current().nextInt(1, 3);
                    baboon.mount(rope, direction == 1 ? Direction.FORWARD : Direction.BACKWARD);

                    Thread.sleep(1000);

                    baboon.unmount(rope);
                }
            });
        }

        executorService.shutdown();

    }
}
