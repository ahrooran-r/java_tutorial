package tutorial.learn.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

/**
 * <a href="https://www.baeldung.com/parameterized-tests-junit-5">tutorial</a>
 */
public class ParameterizedTutorialTest {
    private ParameterizedLogic primeNumberChecker;

    @BeforeEach
    public void initialize() {
        primeNumberChecker = new ParameterizedLogic();
    }

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method

    // public ParameterizedTest(Integer inputNumber, Boolean expectedResult) {
    //     this.inputNumber = inputNumber;
    //     this.expectedResult = expectedResult;
    // }

    // @Parameterized.Parameters
    // public static Collection<Object[]> input() {
    //     return Arrays.asList(new Object[][]{
    //             {2, true},
    //             {6, false},
    //             {19, true},
    //             {22, false},
    //             {23, true}
    //     });
    // }

    @BeforeAll
    public static void before() {
        System.out.println("Executing Parameterized Test");
    }

    /**
     * we can only pass one argument to the test method each time.
     * we can't pass null through a @ValueSource, even for String and Class.
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 19, 23})
    public void testPrimeNumberChecker(int inputNumber) {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertTrue(primeNumberChecker.validate(inputNumber));
    }

    /**
     * for null values
     */
    @ParameterizedTest
    @NullSource
    public void testNullChecker(String input) {
        System.out.println("Parameterized string is : " + input);
        assertNull(input);
    }

    /**
     * we can pass empty values using the @EmptySource annotation
     * this parameter source can provide empty values for Collection types and arrays.
     */
    @ParameterizedTest
    @EmptySource
    public void testEmptyChecker(String input) {
        System.out.println("Parameterized string is : " + input);
        assertTrue(input.isBlank());
    }

    /**
     * we can pass empty values using the @EmptySource annotation
     * this parameter source can provide empty values for Collection types and arrays.
     */
    @ParameterizedTest
    @NullAndEmptySource
    public void testNullAndEmptyChecker(String input) {
        System.out.println("Parameterized string is : " + input);
        assertTrue(input.isBlank());
    }

    /**
     * pass a few more empty string variations to the parameterized test,
     * we can combine @ValueSource, @NullSource, and @EmptySource together
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void testNullAndEmptyVariationChecker(String input) {
        System.out.println("Parameterized string is : " + input);
        assertTrue(input.isBlank());
    }

    @ParameterizedTest
    @MethodSource("customMethod")
    public void testGetSourceFromCustomMethod(int input, boolean expected) {
        System.out.println("Parameterized string is : " + input);
        assertEquals(expected, primeNumberChecker.validate(input));
    }

    /**
     * The argument sources we've covered so far are somewhat simple and share one limitation.
     * It's hard or impossible to pass complex objects using them.
     * <p>
     * <p>
     * <p>
     * Here we're literally returning a stream of arguments, but it's not a strict requirement.
     * For example, we can return any other collection-like interfaces like List.
     * <p>
     * Arguments is an abstraction that provides access to an array of objects
     * to be used for invoking a @ParameterizedTest method.
     */
    private static Stream<Arguments> customMethod() {
        return Stream.of(
                Arguments.of(2, true),
                Arguments.of(6, false),
                Arguments.of(19, true),
                Arguments.of(22, false),
                Arguments.of(23, true)
        );
    }
}