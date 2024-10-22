package tutorial.learn.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class _16_PlusMinus {
    public static void main(String[] args) {

        plusMinus(List.of(1, 1, 0, -1, -1));

    }

    public static void plusMinus(List<Integer> arr) {
        double plusCount = 0;
        double minusCount = 0;
        double zeroCount = 0;

        for (Integer element : arr) {
            if (element < 0) minusCount++;
            else if (element == 0) zeroCount++;
            else plusCount++;
        }

        System.out.println(BigDecimal.valueOf(plusCount / arr.size()).setScale(6, RoundingMode.HALF_UP));
        System.out.println(BigDecimal.valueOf(minusCount / arr.size()).setScale(6, RoundingMode.HALF_UP));
        System.out.println(BigDecimal.valueOf(zeroCount / arr.size()).setScale(6, RoundingMode.HALF_UP));
    }
}
