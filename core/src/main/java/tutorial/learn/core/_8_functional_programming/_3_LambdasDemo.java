package tutorial.learn.core._8_functional_programming;

import java.util.ArrayList;

// lambdas only work with functional interfaces
// A sample functional interface (An interface with single abstract method)
interface FuncInterface {

    // An abstract function
    void abstractFun(int x);

    // A non-abstract (or default) function
    default void normalFun() {
        System.out.println("Hello");
    }
}

public class _3_LambdasDemo {
    public static void main(String[] args) {

        // lambda expression to implement above functional interface.
        // This interface by default implements abstractFun()
        FuncInterface fobj = (int x) -> System.out.println(2 * x);

        // This calls above lambda expression and prints 10.
        fobj.abstractFun(5);

        // lambdas on arrays / lists

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        // print all elements
        arrayList.forEach(n -> System.out.print(n));
        System.out.println();

        // print even elements
        arrayList.forEach(n -> {
            if (n % 2 == 0) System.out.print(n);
        });
        System.out.println();
    }
}