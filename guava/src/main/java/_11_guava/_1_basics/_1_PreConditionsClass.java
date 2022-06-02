package _11_guava._1_basics;

import com.google.common.base.Preconditions;

/**
 * <a href="https://www.baeldung.com/guava-preconditions">Tutorial</a>
 * <p>
 * The Preconditions class provides a list of static methods for checking that a method or a constructor is
 * invoked with valid parameter values. If a precondition fails, a tailored exception is thrown.
 */
public class _1_PreConditionsClass {
    public static void main(String[] args) {

        _1_PreConditionsClass driver = new _1_PreConditionsClass();

        driver.m1(-100);

        // driver.m2(-1, new int[]{1, 2, 3});
    }

    private void m1(int age) {

        /*
         * ensures the truthfulness of the parameters passed to the calling method.
         * This method accepts a boolean condition and throws an IllegalArgumentException when the condition is false.
         */
        // suppose we need to make sure age is greater than 0
        Preconditions.checkArgument(age > 0, "%s must be greater than 0", age);
    }

    private void m2(int index, int[] array) {

        /*
         * checks that an index is a valid index in a list
         * You don't pass a list, string or array directly, you just pass its size (inclusive)
         */
        Preconditions.checkPositionIndex(index, array.length - 1);
    }
}
