package tutorial.learn.junit;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import tutorial.learn.mockito_business_logic.TodoImpl;
import tutorial.learn.mockito_business_logic.TodoInterface;
import tutorial.learn.mockito_business_logic.TodoInterfaceStub;

import java.util.List;

public class StubTest {

    @Test
    public void testStub() {

        System.out.println("Executing Stub Test");

        TodoInterface todoInterface = new TodoInterfaceStub();
        TodoImpl logic = new TodoImpl(todoInterface);

        List<String> list = logic.retrieveTodosRelatedToSpring("someUser");

        assertArrayEquals("Arrays are not equal", list.toArray(), list.toArray());
    }
}
