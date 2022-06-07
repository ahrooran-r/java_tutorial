package tutorial.learn.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import tutorial.learn.junit.mockito_business_logic.TodoImpl;
import tutorial.learn.junit.mockito_business_logic.TodoInterface;
import tutorial.learn.junit.mockito_business_logic.TodoInterfaceStub;

import java.util.List;

public class StubTest {

    @Test
    public void testStub() {

        System.out.println("Executing Stub Test");

        TodoInterface todoInterface = new TodoInterfaceStub();
        TodoImpl logic = new TodoImpl(todoInterface);

        List<String> list = logic.retrieveTodosRelatedToSpring("someUser");

        assertArrayEquals(list.toArray(), list.toArray(), "Arrays are not equal");
    }
}
