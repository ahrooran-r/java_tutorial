package _1_basic;

public class _11_Method {
    public static void main(String[] args) {

        byte x = 2;
        byte y = 3;

        System.out.println(addNum(x, y));
        System.out.println(addNum(2, 3));
        System.out.println(addNum(1, 2, 3, 4));

    }

    // METHOD OVERLOADING
    public static long addNum(int x, int y) {
        return x + y;
    }

    public static int addNum(short x, short y) {
        return x + y;
    }

    public static short addNum(byte x, byte y) {
        return (short) (x + y);
    }

    // get multiple inputs
    public static long addNum(int... numbers) {
        long total = 0;
        for (int number : numbers) total += number;
        return total;
    }

    // NOTE: can not have multiple vararg inputs
    // public static long addNum(int... ints, long... longs) {}
    // Above will throw exception
}
