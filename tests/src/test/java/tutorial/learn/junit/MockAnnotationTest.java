package tutorial.learn.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tutorial.learn.junit.mockito_business_logic.TodoImpl;
import tutorial.learn.junit.mockito_business_logic.TodoInterface;

import java.util.Arrays;
import java.util.List;

/*
* should run with this class if we want to use @Mockito
* or we can do like this:
*
    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
*
* or like this:
*
    public class MockitoInitWithMockitoJUnitRuleUnitTest {

        @Rule
        public MockitoRule initRule = MockitoJUnit.rule();

        ...
    }
*
* */
@ExtendWith(MockitoExtension.class)
public class MockAnnotationTest {

    // equivalent to -> TodoInterface todoInterface = mock(TodoInterface.class);
    @Mock
    TodoInterface todoInterface;

    // equivalent to -> TodoImpl impl = new TodoImpl(todoInterface);
    // it automatically search for any dependencies inside TodoImpl class -> which in this case is TodoInterface
    // then it will add above created mocked TodoInterface to that dependency automatically
    @InjectMocks
    TodoImpl impl;

    @Test
    public void testMock() {

        System.out.println("Executing Mock Test");

        List<String> returnValues = Arrays.asList("Learn Spring MVC", "Learn spring", "learn to dance");
        // when the mock is ran with DUMMY_USR, then return these values...
        when(todoInterface.retrieveTodos("DUMMY_USR")).thenReturn(returnValues);

        // Matchers -> anyString, anyInt etc.
        when(todoInterface.retrieveTodos(anyString())).thenReturn(returnValues);

        List<String> list = impl.retrieveTodosRelatedToSpring("DUMMY_USR");

        assertArrayEquals(list.toArray(), list.toArray(), "Arrays are not equal");
    }

    // Handling exceptions...
    @Test
    public void testMock2() {

        final String MESSAGE = "MOCK THROW!!!";

        System.out.println("Executing Mock Test 2");

        when(todoInterface.retrieveTodos(anyString())).thenThrow(new RuntimeException(MESSAGE));


        Exception exception = assertThrows(RuntimeException.class, () -> {
            // code under test
            impl.retrieveTodosRelatedToSpring("DUMMY_USR");
        });

        assertEquals(MESSAGE, exception.getMessage());
    }
}
