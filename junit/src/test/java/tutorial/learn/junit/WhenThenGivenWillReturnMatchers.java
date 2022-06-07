package tutorial.learn.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import tutorial.learn.junit.mockito_business_logic.TodoImpl;
import tutorial.learn.junit.mockito_business_logic.TodoInterface;

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

        assertArrayEquals(list.toArray(), list.toArray(), "Arrays are not equal");
    }

    // Handling exceptions...
    @Test
    public void testMock2() {

        final String MESSAGE = "MOCK THROW!!!";

        System.out.println("Executing Mock Test 2");

        TodoInterface todoInterfaceMock = mock(TodoInterface.class);

        when(todoInterfaceMock.retrieveTodos(anyString())).thenThrow(new RuntimeException(MESSAGE));

        // both above and below are same
        // below is more related to BDD style
        given(todoInterfaceMock.retrieveTodos(anyString())).willThrow(new RuntimeException(MESSAGE));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            TodoImpl logic = new TodoImpl(todoInterfaceMock);
            logic.retrieveTodosRelatedToSpring("DUMMY_USR");
        });

        assertEquals(MESSAGE, exception.getMessage());
    }
}
