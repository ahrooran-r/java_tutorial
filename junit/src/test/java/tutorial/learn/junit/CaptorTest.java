package tutorial.learn.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Stack;

@RunWith(MockitoJUnitRunner.class)
public class CaptorTest {

    @Mock
    Stack<String> stack;

    @Captor
    ArgumentCaptor<String> captor;

    @Test
    public void test() {

        stack.add("Java Code Geeks");
        verify(stack).add(captor.capture());

        // It is recommended to use ArgumentCaptor with verification but not with stubbing.
        // Using ArgumentCaptor with stubbing may decrease test readability because captor
        // is created outside of assert (aka verify or ‘then’) block. Also it may reduce defect
        // localization because if stubbed method was not called then no argument is captured.

        System.out.println(captor.getValue());

        assertEquals("Java Code Geeks", captor.getValue());
    }
}
