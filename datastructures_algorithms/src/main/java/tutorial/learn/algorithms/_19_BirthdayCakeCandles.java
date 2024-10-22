package tutorial.learn.algorithms;

import java.util.List;

public class _19_BirthdayCakeCandles {
    public static void main(String[] args) {

        System.out.println(birthdayCakeCandles(List.of(4, 4, 1, 3, 5, 5, 5, 4)));

    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        int tallest = candles.get(0);
        int count = 0;

        for (Integer size : candles) {
            if (size > tallest) {
                tallest = size;
                count = 1;
            } else if (size == tallest) {
                count++;
            }
        }
        return count;
    }
}
