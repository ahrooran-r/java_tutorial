package tutorial.learn.core._10_concurrency._6_concurrent_problems._2_student_library_problem;

import java.util.Random;

public class Student implements Runnable {

    private static final Random random = new Random(System.nanoTime());
    private final int id;
    private final Book[] availableBooks;
    private int numOfBooksRead = 0;
    private boolean maxBooksRead = false;

    public Student(int id, Book[] availableBooks) {
        this.id = id;
        this.availableBooks = availableBooks;
    }

    public void setMaxBooksRead() {
        this.maxBooksRead = true;
    }

    public int getNumOfBooksRead() {
        return numOfBooksRead;
    }


    @Override
    public void run() {
        while (!maxBooksRead) {
            Book selectedBook = availableBooks[random.nextInt(availableBooks.length - 1)];
            try {
                // the selected book is added to the count once it is read
                if (selectedBook.read(this)) numOfBooksRead++;
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
