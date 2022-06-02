package _7_generics;

/*
 * The question mark (?) is known as the wildcard in generic programming.
 * It represents an unknown type.
 * The wildcard can be used in a variety of situations such as the type of a parameter, field, or local variable; sometimes as a return type.
 * Unlike arrays, different instantiations of a generic type are not compatible with each other, not even explicitly.
 * This incompatibility may be softened by the wildcard if ? is used as an actual type parameter.
 * */

/*

1. Upper Bounded Wildcards

These wildcards can be used when you want to relax the restrictions on a variable.

For example, say you want to write a method that works on
    List < integer >,
    List < double >, and
    List < number >,
you can do this  using an upper bounded wildcard.

2. Lower Bounded Wildcards

It is expressed using the wildcard character (‘?’), followed by the super keyword,
followed by its lower bound: <? super A>.

3. Unbounded Wildcard

This wildcard type is specified using the wildcard character (?), for example, List.
This is called a list of unknown type. These are useful in the following cases
    1. When writing a method which can be employed using functionality provided in Object class.
    2. When the code is using methods in the generic class that don’t depend on the type parameter

* */

import java.util.Arrays;
import java.util.List;

public class _4_Wildcard {
    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(2.3, 4.5, 6.7, 1.2, 3.3);
        List<Number> numberList = Arrays.asList(1.0, 2, 3.3, 4.0, 1);

        double[] sumList = new double[3];
        sumList[0] = add(intList);
        sumList[1] = add(doubleList);
        sumList[2] = add(numberList);

        System.out.printf("SumList: %s\n\n", Arrays.toString(sumList));

        printOnlyIntegerClassOrSuperClass(intList);
        printOnlyIntegerClassOrSuperClass(numberList);
        // printOnlyIntegerClassOrSuperClass(doubleList); -> compiler error

        printList(intList);
        printList(numberList);
        printList(doubleList);
    }

    // To declare an upper-bounded wildcard, use the wildcard character (‘?’),
    // followed by the extends keyword, followed by its upper bound.
    public static double add(List<? extends Number> someList) {
        // now a list having objects with types such as Integer, Double and Number can be added together

        double sum = 0.0;
        for (Number number : someList) sum += number.doubleValue();
        return sum;
    }

    public static void printOnlyIntegerClassOrSuperClass(List<? super Integer> list) {
        // prints list which is either Integer or super class of Integer (such as Number etc.)
        System.out.printf("%sList: %s\n", list.get(0).getClass().getSimpleName(), list);
    }

    public static void printList(List<?> list) {
        // prints any list as inner type is not specified
        System.out.printf("%sAnonymousList: %s\n", list.get(0).getClass().getSimpleName(), list);
    }
}
