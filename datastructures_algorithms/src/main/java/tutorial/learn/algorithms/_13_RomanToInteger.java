package tutorial.learn.algorithms;

public class _13_RomanToInteger {

    public static void main(String[] args) {
        String s = "MCMXCIV";

        int answer = romanToInt(s);
        System.out.println(answer);
    }

    /**
     * https://leetcode.com/problems/roman-to-integer/solutions/2632431/java-90-faster-solution/comments/1641369
     *
     * @param s MCMXCIV
     * @return 1994
     */
    public static int romanToInt(String s) {
        int answer = 0;
        int current = 0;
        int prev = 0;

        for (int r = s.length() - 1; r >= 0; r--) {
            current = switch (s.charAt(r)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> throw new IllegalArgumentException("Unexpected value: " + s.charAt(r));
            };

            if (current < prev) answer -= current;
            else answer += current;

            prev = current;
        }

        return answer;
    }

    /**
     * @param num 1994
     * @return MCMXCIV
     */
    public static String intToRoman(int num) {

        if (num < 1 || num > 3999) throw new IllegalArgumentException("constraint violation");

        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousands = {"", "M", "MM", "MMM"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
}
