package tutorial.learn.junit.mockito_business_logic;

import java.util.List;

// TodoService class -> Dependency for SUT
public interface TodoInterface {
    List<String> retrieveTodos(String user);
}