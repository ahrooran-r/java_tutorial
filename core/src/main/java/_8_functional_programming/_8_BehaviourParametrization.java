package _8_functional_programming;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class _8_BehaviourParametrization {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 45, 32, 3, 12, 3, 7, 56, 100, 27);

    }

    public static void printEvenOddTraditional(List<Integer> numbers) {

        // first print even numbers and then print odd numbers
        numbers
                .stream()
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        numbers
                .stream()
                .filter(x -> x % 2 != 0)
                .forEach(System.out::println);

        // you can see there is a lot of code duplication in here
    }

    private static void printWithCommonFilter(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers
                .stream()
                .filter(predicate)
                .forEach(System.out::println);
        // this method will print any number that satisfies the predicate
        // we just have to call this twice with different predicates
    }

    public static void printEvenOddRefactored(List<Integer> numbers) {
        // we are passing logic of the method as parameters
        printWithCommonFilter(numbers, x -> x % 2 == 0);
        printWithCommonFilter(numbers, x -> x % 2 != 0);
    }

    // use map as parameter
    private static void printWithCommonMap(List<Integer> numbers, Function<Integer, Integer> mapper) {
        numbers
                .stream()
                .map(mapper)
                .forEach(System.out::println);
    }

    public static void printSquareNumbers(List<Integer> numbers) {
        printWithCommonMap(numbers, x -> x * x);
    }
}