package tutorial.learn.core._1_basic;

public class _6_Constants {
    public static void main(String[] args) {

        // declare constant variables -> these variables cannot be changed
        final float pi = 3.14F;
    }

    // declare constant methods -> these methods cannot be overridden
    final int addNum(int a, int b) {
        return a + b;
    }

    // final class is instantiable
    Test test = new Test();
}

// declare constant classes -> these classes cannot be extended

// also methods of this class can not be overridden
// <- if `final` is used on a class, it is also applied to the respective methods
final class Test {

}
