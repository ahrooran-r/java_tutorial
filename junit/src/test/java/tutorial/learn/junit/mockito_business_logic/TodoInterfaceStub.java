package tutorial.learn.junit.mockito_business_logic;

import java.util.Arrays;
import java.util.List;

public class TodoInterfaceStub implements TodoInterface {

    @Override
    public List<String> retrieveTodos(String user) {

        // this method returns names of user as a list
        // hence, we can mock this method by returning pre-configured strings
        return Arrays.asList("Learn Spring MVC", "Learn spring", "learn to dance");
    }
}
