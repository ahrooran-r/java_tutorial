package _1_basic;

public class _8_Casting {
    public static void main(String[] args) {

        // Implicit casting
        // byte -> short -> int -> long
        short x = 1;
        int y = x + 2;

        // Explicit casting
        // can only happen between compatible types
        double a = 3.1;
        int b = (int) a + 1;

        String p = "1";
        int q = Integer.parseInt(p) + 2;
    }
}
