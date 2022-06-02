package _8_functional_programming;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class _7_FunctionalInterfaces {

    // Intermediate operations -> Operations take in a stream and returns another stream

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 45, 32, 3, 12, 3, 7, 56, 100, 27);

        List<String> courses = List.of("Spring Boot", "Java 101", "Functional Programming", "enums",
                "Spring JPA", "Concurrency", "Java 101");

    }

    public static void normalFunctionalExecution(List<Integer> numbers) {
        numbers
                .stream()
                .filter(num -> num % 2 == 0)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    public static void whatIsHappening(List<Integer> numbers) {

        // Predicate, Function, Consumer are functional interfaces
        // functional interface has exactly ONE abstract method

        // Represents a boolean-valued function of one argument.
        Predicate<Integer> predicate = num -> num % 2 == 0;

        // actually this is what happens in above single line
        Predicate<Integer> deepPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                if (num % 2 == 0) return true;
                return false;
            }
        };

        // Represents a function that accepts one argument and produces a result.
        Function<Integer, Integer> function = x -> x * x;

        Function<Integer, Integer> deepFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer num) {
                return num * num;
            }
        };

        // Represents an operation that accepts a single input argument and returns no result.
        Consumer<Integer> consumer = System.out::println;

        Consumer<Integer> deepConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer num) {
                System.out.println(num);
            }
        };

        numbers
                .stream()
                .filter(predicate)
                .map(function)
                .forEach(consumer);
    }

    public static void reduceMethod(List<Integer> numbers) {
        numbers
                .stream()
                .filter(num -> num % 2 == 0)
                .map(x -> x * x)
                .reduce(0, (x, y) -> x + y);
    }

    public static void whatIsHappeningInReduce(List<Integer> numbers) {

        // Represents an operation upon two operands of the same type,
        // producing a result of the same type as the operands.
        BinaryOperator<Integer> deepReduce = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            }
        };

        // Represents an operation on a single operand that produces
        // a result of the same type as its operand.
        UnaryOperator<Integer> unaryOperator = x -> 3 * x;

        numbers
                .stream()
                .filter(num -> num % 2 == 0)
                .map(x -> x * x)
                .reduce(0, deepReduce);
    }

    public static void supplierMethod(List<Integer> numbers) {

        // Takes NO input and return an output
        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };
    }
}
