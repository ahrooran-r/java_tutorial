package tutorial.learn.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tutorial.learn.mockito_business_logic.TodoImpl;
import tutorial.learn.mockito_business_logic.TodoInterface;

import java.util.Arrays;
import java.util.List;

/*
* should run with this class if we want to use @Mockito
* or we can do like this:
*
    @Before
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
@RunWith(MockitoJUnitRunner.class)
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

        assertArrayEquals("Arrays are not equal", list.toArray(), list.toArray());
    }

    // Handling exceptions...
    @Test(expected = RuntimeException.class)
    public void testMock2() {

        System.out.println("Executing Mock Test 2");

        when(todoInterface.retrieveTodos(anyString())).thenThrow(new RuntimeException("MOCK THROW!!!"));

        impl.retrieveTodosRelatedToSpring("DUMMY_USR");
    }
}
