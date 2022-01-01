package _8_functional_programming;

import java.util.List;
import java.util.stream.Collectors;

public class _5_StreamOperations {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 45, 32, 3, 12, 3, 7, 56, 100, 27);

    }

    public static List<Integer> findDistinct(List<Integer> numbers) {
        return numbers
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
