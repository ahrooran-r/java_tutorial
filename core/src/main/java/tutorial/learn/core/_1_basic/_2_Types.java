package tutorial.learn.core._1_basic;

import java.util.Date;

public class _2_Types {
    public static void main(String[] args) {

        // PRIMITIVE TYPES
        // storing whole numbers
        byte age = 20; // byte -> takes 1 byte: range[-128, 127]
        short salary = 32000; // short -> takes 2 bytes: range[-32K, 32K]
        int milliseconds = 1_000_000_000; // int -> takes 4 bytes: range[-2B, 2B]
        long nanoseconds = 3_123_456_789L; // long -> takes 8 bytes; add an L in last

        // storing decimal point numbers
        float bankBalance = 2100.992F; // float -> takes 4 bytes; add an F in last
        double avgSalary = 1_320_120.276; // double -> takes 8 bytes

        // storing characters
        char A = 'A'; // char -> takes 2 bytes

        // true/false
        boolean isCorrect = true; // boolean -> takes 1 byte: (true, false)

        // REFERENCE TYPES
        Date now = new Date(); // 'new' -> use to allocate memory
        long nowTime = now.getTime();
        System.out.println(now);
    }
}
