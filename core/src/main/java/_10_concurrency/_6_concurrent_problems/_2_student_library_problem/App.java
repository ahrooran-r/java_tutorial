package _10_concurrency._6_concurrent_problems._2_student_library_problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {

        Book[] books = new Book[Constants.NUMBER_OF_BOOKS];
        for (int i = 0; i < Constants.NUMBER_OF_BOOKS; i++) books[i] = new Book(i);

        Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS; i++) students[i] = new Student(i, books);

        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS; i++) {
            executorService.execute(students[i]);
        }

        Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
        for (Student student : students) student.setMaxBooksRead();

        executorService.shutdown();
        while (!executorService.isTerminated()) Thread.sleep(1000);

        for (Student student : students) {
            System.out.printf("Student %s has read %d books\n", student, student.getNumOfBooksRead());
        }
    }
}
