package tutorial.learn.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssertTest {

    String str1 = new String("hi");
    String str2 = new String("hi");
    String str3 = "hello";
    String str4 = "hello";
    String str5 = null;
    int val1 = 5;
    int val2 = 6;
    String[] arr1 = {"one", "two", "three"};
    String[] arr2 = {"one", "two", "three"};

    @Test
    public void testAssertions() {

        System.out.println("Executing Assert Test");

        assertEquals(str1, str2);
        assertTrue(val1 < val2);
        System.out.println("this is working");
        assertFalse(val1 < val2);
        System.out.println("this is also working here");
        assertNull(str5);

        // if one of the above inner method calls FAIL,
        // all calls below that would not execute
    }
}
