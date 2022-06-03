package tutorial.learn.core._8_functional_programming;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _6_SortOperations {

    // Intermediate operations -> Operations take in a stream and returns another stream

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 45, 32, 3, 12, 3, 7, 56, 100, 27);

        List<String> courses = List.of("Spring Boot", "Java 101", "Functional Programming", "enums",
                "Spring JPA", "Concurrency", "Java 101");

    }

    public static List<String> sort(List<String> courses) {
        return courses
                .stream()
                .sorted()
                .collect(Collectors.toList()); // collector is used to accumulate result stream to a list
    }

    public static List<String> customSort(List<String> courses) {
        return courses
                .stream()
                .sorted(Comparator.comparing(str -> str.length())) // comparing method can be used to write our custom sort
                .collect(Collectors.toList());
    }
}
