package tutorial.learn.vavr._2_datastructures;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

import io.vavr.CheckedFunction2;
import io.vavr.control.Try;

public class _4_Try {
    public static void main(String[] args) throws Throwable {

        CheckedFunction2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Try<Integer> tryInt = Try(() -> divide.apply(1, 0))

                // tries to recover error by returning pre-defined values
                .recover(x -> Match(x).of(
                        Case($(instanceOf(ArithmeticException.class)), t -> -1),
                        Case($(instanceOf(NumberFormatException.class)), t -> -2),
                        Case($(instanceOf(RuntimeException.class)), t -> -3)
                ));

        tryInt.isSuccess();
        tryInt.isFailure();
        // tryInt.getCause();

        System.out.println(tryInt.get());
    }
}
