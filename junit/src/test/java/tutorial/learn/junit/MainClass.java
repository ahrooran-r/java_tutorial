package tutorial.learn.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainClass {
    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(
                AnnotationTest.class,
                AssertTest.class,
                ParameterizedTest.class,
                StubTest.class,
                WhenThenGivenWillReturnMatchers.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Result == " + result.wasSuccessful());
    }
}
