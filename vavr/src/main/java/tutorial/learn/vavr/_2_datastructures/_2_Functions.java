package tutorial.learn.vavr._2_datastructures;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;

/**
 * Java 8 just provides a Function which accepts one parameter and a
 * BiFunction which accepts two parameters.
 * Vavr provides functions up to a limit of 8 parameters.
 * <p>
 * If you need a function which throws a checked exception you can use
 * CheckedFunction1, CheckedFunction2 and so on.
 */
public class _2_Functions {
    public static void main(String[] args) {

        // last is return type of function
        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
        sum.apply(2, 3, 3);

        // create a function from any method reference.
        Function2<Integer, Integer, Integer> intSum = Function2.of(Integer::sum);
        Function2<Integer, Integer, Integer> intMul = (a, b) -> a * b;

        // 1. COMPOSITION

        // function composition is the application of one function to the result of
        // another to produce a third function.
        // For instance, the functions f: X -> Y and g: Y -> Z can be composed
        // to yield a function h: g(f(x)) which maps X -> Z.
        Function2<Integer, Integer, Integer> sumAndMul = intSum.andThen(x -> intMul.apply(x, 2));

        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        // applies given function before applying this function
        multiplyByTwo.compose(plusOne);

        // 2. LIFTING
        // this divide function will throw error when divided by 0
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        // following lifts the given partialFunction into a total function that returns an Option result.
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

    }
}
