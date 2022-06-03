package tutorial.learn.core._9_modules.PrimitiveWrappers;

public class _1_Integer_Module {
    public static void main(String[] args) {

        // create integer from int
        Integer number = 1000;

        // METHODS

        // convert to string
        Integer.toString(number); // -> "1000"
        //Integer.toHexString(number);
        //Integer.toOctalString(number);
        //Integer.toBinaryString(number);

        // convert String to int
        Integer.valueOf("100", 2); // -> 4 <- integer object
        Integer.parseInt("100", 2); // -> 4 <- primitive int

        Integer.decode("0x100"); // -> 256

        // compare numerical value
        // number.compareTo(100); // -> 1
        // number.compareTo(1000); // -> 0
        // number.compareTo(1100); // -> -1

        // returns +1, -1, 0 -> useful when comparing numbers
        Integer.compare(23, 34);

        // compare if two objects are the same or not and returns a boolean
        // more efficient
        number.equals("1000"); // -> false

        // checks sign: returns -1 for negative values, 0 for 0 and +1 for values greater than 0.
        Integer.signum(-100); // -> -1

        // Integer.max(1, 2); // -> 2
        // Integer.min(1, 2); // -> 1
    }
}
