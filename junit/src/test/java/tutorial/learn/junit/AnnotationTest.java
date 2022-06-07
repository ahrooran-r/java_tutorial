package tutorial.learn.junit;


/*
 *      Annotations     Description
 * 1.   @Test           tells junit that this method can run as test case
 * 2.   @BeforeEach     causes this method to run before every test method
 * 3.   @AfterEach      runs after every test case -> used to release allocated resources
 * 4.   @BeforeAll      run this method once before any of test methods of certain class
 * 5.   @AfterAll       runs this method after all methods of certain class. used to perform cleanup
 * 6.   @Disabled       used to ignore the test. this will not be executed
 * */

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class AnnotationTest {

    @BeforeAll
    public static void beforeAllMethods() {
        System.out.println("Executing Annotation Test");
        System.out.println("beforeClass is executing");
    }

    @BeforeEach
    public void beforeEachMethod() {
        System.out.println("beforeMethod is executing");
    }

    @AfterEach
    public void afterEachMethod() {
        System.out.println("afterMethod is executing");
    }

    @AfterAll
    public static void afterAllMethods() {
        System.out.println("afterClass is executing");
    }

    @Test
    public void testOne() {
        System.out.println("testOne executing");
        assertTrue(true);
    }

    @Test
    public void testTwo() {
        System.out.println("testTwo executing");
        assertTrue(true);
    }
}
