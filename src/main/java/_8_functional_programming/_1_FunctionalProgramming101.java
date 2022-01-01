package _8_functional_programming;

import java.util.List;

public class _1_FunctionalProgramming101 {
    public static void main(String[] args) {

        List<Integer> nums = List.of(12, 34, 2, 1, 10);

        List<String> courses = List.of("Spring Boot", "Java 101", "Functional Programming", "enums", "Spring JPA", "Concurrency");

        printSquares(nums);
    }

    /**
     * Traditional approach of printing numbers
     */
    public static void printAllNumbers_traditional(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(i);
        }
    }

    /**
     * Functional approach of printing numbers
     */
    public static void printAllNumbers_functional(List<Integer> nums) {

        // convert list of numbers -> stream of numbers
        // stream -> numbers come one after another in a sequence

        // this is called method reference
        nums
                .stream()
                .forEach(System.out::println);
    }


    // FILTER

    public static void filterEvenNumbers(List<Integer> nums) {

        // convert list of numbers -> stream of numbers
        // stream -> numbers come one after another in a sequence

        // this is called method reference
        nums
                .stream() // convert to stream
                .filter(num -> num % 2 == 0) // lambda expression -> simplified expression of a method
                .forEach(System.out::println); // method reference
    }

    public static void filterOddNumbers(List<Integer> nums) {
        nums
                .stream()
                .filter(num -> num % 2 != 0)
                .forEach(System.out::println);
    }

    public static void printCoursesIndividually(List<String> courses) {
        courses
                .forEach(System.out::println);
    }

    public static void printCoursesContainingWord(List<String> courses) {
        courses
                .stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);
    }

    public static void printCoursesAboveThreshold(List<String> courses) {
        courses
                .stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
    }

    // MAP

    public static void printSquares(List<Integer> nums) {
        nums
                .stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * num)
                .forEach(System.out::println);
    }

    public static void printCubes(List<Integer> nums) {
        nums
                .stream()
                .filter(num -> num % 2 != 0)
                .map(num -> Math.pow(num, 3))
                .forEach(System.out::println);
    }

    public static void printNumberOfChars(List<String> courses) {
        courses
                .stream()
                .map(String::length)
                .forEach(System.out::println);
    }

}
