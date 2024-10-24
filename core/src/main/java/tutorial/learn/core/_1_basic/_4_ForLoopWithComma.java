package tutorial.learn.core._1_basic;

public class _4_ForLoopWithComma {
    public static void main(String[] args) {

        // Comma operator (Note that it is not a comma separator).
        // The only place where the comma operator is used in Java
        // is the control expression of the for loop.
        // In the initialization and step control part of the control expression,
        // a series of statements separated by commas can be used.
        for (int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
            System.out.println("i = " + i + " j = " + j);
        }

        // The comma operator in the for loop allows you to:
        //      1. Initialize multiple variables in the initialization section
        //      2. Perform multiple updates in the increment section
    }
}
