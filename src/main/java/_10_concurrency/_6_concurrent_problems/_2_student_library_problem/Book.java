package _10_concurrency._6_concurrent_problems._2_student_library_problem;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private static final Random random = new Random(System.nanoTime());
    private final Lock lock = new ReentrantLock(true);
    private final int id;

    public Book(int id) {
        this.id = id;
    }

    public boolean read(Student student) throws InterruptedException {
        boolean success = pickUp(student);
        if (success) {
            Thread.sleep(300 + random.nextInt(1000));
            putDown(student);
        }
        return success;
    }

    private boolean pickUp(Student student) throws InterruptedException {
        boolean isPickedUp = lock.tryLock(10, TimeUnit.MILLISECONDS);
        if (isPickedUp) System.out.printf("Student %s has picked up book %s\n", student, this);
        return isPickedUp;
    }

    private void putDown(Student student) {
        System.out.printf("Student %s has finished reading the book %s\n", student, this);
        lock.unlock();
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
