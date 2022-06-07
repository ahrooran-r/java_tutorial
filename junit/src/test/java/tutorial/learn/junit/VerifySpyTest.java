package tutorial.learn.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VerifySpyTest {

    @Mock
    List<String> mockList;

    @Spy
    List<String> spiedList = new ArrayList<>();

    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {

        mockList.add("one");

        // https://www.baeldung.com/mockito-verify
        // we're interacting with the mock and verifying some of these interactions
        // – just to make sure that the mock is behaving correctly
        verify(mockList).add("one");

        assertEquals(0, mockList.size());

        when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    // Now – let's see how to use @Spy annotation to spy on an existing instance.
    // Without annotation
    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {

        // A Mockito spy is a partial mock. We can mock a part of the object by stubbing few methods,
        // while real method invocations will be used for the other. By saying so, we can conclude
        // that calling a method on a spy will invoke the actual method, unless we explicitly stub
        // the method, and therefore the term partial mock.

        // Mockito. spy() is a recommended way of creating partial mocks. The reason is it guarantees
        // real methods are called against correctly constructed object because you're responsible for
        // constructing the object passed to spy() method. Spy can be useful when you want to create unit
        // tests for legacy code.

        // List list = new LinkedList();
        // List spy = spy(list);
        // -> in same lin:              List<String> spyList = spy(new ArrayList<String>());
        //
        // Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        // when(spy.get(0)).thenReturn("foo");
        //
        // You have to use doReturn() for stubbing
        // doReturn("foo").when(spy).get(0);

        List<String> spyList = spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());

        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    // WITH annotation
    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {

        spiedList.add("one");
        spiedList.add("two");

        verify(spiedList).add("one");
        verify(spiedList).add("two");

        assertEquals(2, spiedList.size());

        doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());

        // Note how, as before – we're interacting with the spy here to make sure that it behaves correctly.
        // In this example we:
        // Used the real method spiedList.add() to add elements to the spiedList.
        // Stubbed the method spiedList.size() to return 100 instead of 2 using Mockito.doReturn().
    }
}
