package tutorial.learn.collections.vavr._2_datastructures;

import io.vavr.API;
import io.vavr.CheckedFunction2;
import io.vavr.Predicates;
import io.vavr.control.Try;

public class _4_Try {
    public static void main(String[] args) throws Throwable {

        CheckedFunction2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Try<Integer> tryInt = API.Try(() -> divide.apply(1, 0))

                // tries to recover error by returning pre-defined values
                .recover(x -> API.Match(x).of(
                        API.Case(API.$(Predicates.instanceOf(ArithmeticException.class)), t -> -1),
                        API.Case(API.$(Predicates.instanceOf(NumberFormatException.class)), t -> -2),
                        API.Case(API.$(Predicates.instanceOf(RuntimeException.class)), t -> -3)
                ));

        tryInt.isSuccess();
        tryInt.isFailure();
        // tryInt.getCause();

        System.out.println(tryInt.get());
    }
}
