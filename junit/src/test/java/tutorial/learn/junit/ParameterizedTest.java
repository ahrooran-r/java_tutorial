package tutorial.learn.junit;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    private Integer inputNumber;
    private Boolean expectedResult;
    private ParameterizedLogic primeNumberChecker;

    @Before
    public void initialize() {
        primeNumberChecker = new ParameterizedLogic();
    }

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method

    public ParameterizedTest(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }

    @BeforeClass
    public static void before() {
        System.out.println("Executing Parameterized Test");
    }

    // This test will run 4 times since we have 5 parameters defined
    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult, primeNumberChecker.validate(inputNumber));
    }
}