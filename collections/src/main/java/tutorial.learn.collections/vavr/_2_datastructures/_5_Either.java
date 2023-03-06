package tutorial.learn.collections.vavr._2_datastructures;

import io.vavr.control.Either;

import java.util.HashMap;
import java.util.Map;

/**
 * Either represents a value of two possible data types.
 * An `Either` is either a Left or a Right.
 * <p>
 * <b>
 * By convention, the Left signifies a failure case result and the Right signifies a success.
 * </b>
 */
public class _5_Either {
    public static void main(String[] args) {

        // // Without VAVR - Method 1 ------------------------------
        // Map<String, Object> results = computeWithoutEitherUsingMap(8);
        // String error = (String) results.get("FAILURE");
        // int marks = (int) results.get("SUCCESS");


        // With VAVR - Either -----------------------------------
        computeWithEither(90)
                // by default Either is right biased -> prints 90
                .forEach(System.out::println);

        // prints nothing because in this case 80 < 85 -> so only left is there
        computeWithEither(80).forEach(System.out::println);

        Either<String, Integer> either = computeWithEither(0);
        either.isRight();
        either.isLeft();
        either.get(); // by default  returns right

        // will swap the values -> now String is to the right and Integer is to the left
        either.swap();
    }

    /**
     * Method 3 -> With Either easy
     * <p>
     * <b>LEFT signifies a failure case result and the RIGHT signifies a success.</b>
     */
    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }

    /**
     * Method 1 -> tedious
     */
    public static Map<String, Object> computeWithoutEitherUsingMap(int marks) {
        Map<String, Object> results = new HashMap<>();
        if (marks < 85) {
            results.put("FAILURE", "Marks not acceptable");
        } else {
            results.put("SUCCESS", marks);
        }
        return results;
    }

    /**
     * Method 2 -> tedious
     */
    public static Object[] computeWithoutEitherUsingArray(int marks) {
        Object[] results = new Object[2];
        if (marks < 85) {
            results[0] = "Marks not acceptable";
        } else {
            results[1] = marks;
        }
        return results;
    }
}
