package tutorial.learn.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import tutorial.learn.mockito_business_logic.TodoImpl;
import tutorial.learn.mockito_business_logic.TodoInterface;

import java.util.Arrays;
import java.util.List;

public class WhenThenGivenWillReturnMatchers {

    @Test
    public void testMock() {

        System.out.println("Executing Mock Test");

        TodoInterface todoInterfaceMock = mock(TodoInterface.class);
        List<String> returnValues = Arrays.asList("Learn Spring MVC", "Learn spring", "learn to dance");
        // when the mock is ran with DUMMY_USR, then return these values...
        when(todoInterfaceMock.retrieveTodos("DUMMY_USR")).thenReturn(returnValues);

        // Matchers -> anyString, anyInt etc.
        when(todoInterfaceMock.retrieveTodos(anyString())).thenReturn(returnValues);

        TodoImpl logic = new TodoImpl(todoInterfaceMock);

        List<String> list = logic.retrieveTodosRelatedToSpring("DUMMY_USR");

        assertArrayEquals("Arrays are not equal", list.toArray(), list.toArray());
    }

    // Handling exceptions...
    @Test(expected = RuntimeException.class)
    public void testMock2() {

        System.out.println("Executing Mock Test 2");

        TodoInterface todoInterfaceMock = mock(TodoInterface.class);

        when(todoInterfaceMock.retrieveTodos(anyString())).thenThrow(new RuntimeException("MOCK THROW!!!"));

        // both above and below are same
        // below is more related to BDD style
        given(todoInterfaceMock.retrieveTodos(anyString())).willThrow(new RuntimeException("MOCK THROW!!!"));

        TodoImpl logic = new TodoImpl(todoInterfaceMock);

        logic.retrieveTodosRelatedToSpring("DUMMY_USR");
    }
}
