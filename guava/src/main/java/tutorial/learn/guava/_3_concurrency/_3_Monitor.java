package tutorial.learn.guava._3_concurrency;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * The monitor class is regarded as the replacement of ReentrantLock
 * also it is a bit more readable and less error-prone.
 * <p>
 * <a href="https://www.baeldung.com/guava-21-util-concurrent">tutorial</a>
 */
public class _3_Monitor {

    private static final int MAX_SIZE = 100;
    private final List<String> students = new ArrayList<>();
    private final Monitor monitor = new Monitor();

    public void addToCourse(String item) throws InterruptedException {

        // Monitor.Guard studentsBelowCapacity = monitor.newGuard(this::isStudentsCapacityUptoLimit);

        Monitor.Guard studentsBelowCapacity = monitor.newGuard(() -> students.size() > MAX_SIZE);
        monitor.enterWhen(studentsBelowCapacity);

        try {
            students.add(item);
        } finally {
            monitor.leave();
        }
    }

    // public Boolean isStudentsCapacityUptoLimit() {
    //     return students.size() > MAX_SIZE;
    // }
}
