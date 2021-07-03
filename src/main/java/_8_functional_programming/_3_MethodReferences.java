package _8_functional_programming;

import java.util.Arrays;
import java.util.List;

class ArbitraryClass {
    String str;

    ArbitraryClass(String s) {
        this.str = s;
    }

    // instance function to be called
    void someArbitraryFunction() {
        System.out.print(this.str);
    }
}

class SuperClass {

    // super function to be called
    void someSuperFunction(String str) {
        System.out.print(str);
    }
}

public class _3_MethodReferences extends SuperClass {

    public _3_MethodReferences(String s) {
        System.out.print(s);
    }

    // static function to be called
    static void someStaticFunction(String s) {
        System.out.print(s);
    }

    public static void main(String[] args) {

        List<String> chars = Arrays.asList("A", "H", "R", "O", "O", "R", "A", "N");

        // normal way of printing stream of strings
        chars.forEach(s -> System.out.print(s));
        System.out.println();

        // Method reference or double colon operator can be used to refer:
        //     1. an instance method
        //     2. a static method,
        //     3. Super method
        //     4. Instance method of an arbitrary object of a particular type
        //     5. a constructor.

        // printing stream of strings with method reference -> 1
        chars.forEach(System.out::print);
        System.out.println();

        // printing stream of strings with static method -> 2
        chars.forEach(_3_MethodReferences::someStaticFunction);
        System.out.println();

        // printing stream of strings with super method -> 3
        // since print() is defined in the super class => method is called from super class
        chars.forEach(new _3_MethodReferences("hello")::someSuperFunction);
        System.out.println();

        // printing stream of strings with arbitrary object -> 4
        List<ArbitraryClass> list = Arrays.asList(
                new ArbitraryClass("A"),
                new ArbitraryClass("B"),
                new ArbitraryClass("C"),
                new ArbitraryClass("D"),
                new ArbitraryClass("E")
        );
        list.forEach(ArbitraryClass::someArbitraryFunction);
        System.out.println();

        // printing stream of strings with class constructor method -> 5
        chars.forEach(_3_MethodReferences::new);
        System.out.println();

    }
}
