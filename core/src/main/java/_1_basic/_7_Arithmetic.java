package _1_basic;

public class _7_Arithmetic {
    public static void main(String[] args) {

        int result1 = 10 / 3;
        // 10/3 -> 3; by default integer division returns INTEGER; not float

        // alternatives
        double result2 = 10 / 3.0;
        double result3 = (double) 10 / 3;
        double result4 = (double) 10 / (double) 3;

        //difference between ++x, x++

        byte x = 1;
        byte y = x++;
        // x is assigned to y; then x is incremented
        // y=1, x=2

        x = 1;
        y = ++x;
        // x is incremented; then assigned to y
        // x=2, y=2
    }
}
