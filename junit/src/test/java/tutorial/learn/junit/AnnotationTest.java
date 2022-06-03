package tutorial.learn.junit;


/*
 *      Annotations     Description
 * 1.   @Test           tells junit that this method can run as test case
 * 2.   @Before         causes this method to run before every test method
 * 3.   @After          runs after every test case -> used to release allocated resources
 * 4.   @BeforeClass    run this method once before any of test methods of certain class
 * 5.   @AfterClass     runs this method after all methods of certain class. used to perform cleanup
 * 6.   @Ignore         used to ignore the test. this will not be executed
 * */

/**
 * Unit test for simple App.
 */
public class AnnotationTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Executing Annotation Test");
        System.out.println("beforeClass is executing");
    }

    @Before
    public void beforeMethod() {
        System.out.println("beforeMethod is executing");
    }

    @After
    public void afterMethod() {
        System.out.println("afterMethod is executing");
    }

    @AfterClass
    public static void afterClass() {
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
