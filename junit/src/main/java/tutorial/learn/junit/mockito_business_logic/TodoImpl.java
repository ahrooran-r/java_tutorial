package tutorial.learn.junit.mockito_business_logic;

import java.util.List;
import java.util.stream.Collectors;

// TodoBusinessImpl -> called System Under Test (SUT)
public class TodoImpl {

    // Dependency for SUT
    // To test this class: TodoLogic, we should create a stub of implementation of TodoInterface (say TodoServiceStub).
    // Then test TodoLogic with TodoServiceStub
    private final TodoInterface todoInterface;

    public TodoImpl(TodoInterface todoInterface) {
        this.todoInterface = todoInterface;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {

        List<String> allTodos = todoInterface.retrieveTodos(user);

        List<String> filteredTodos = allTodos
                .stream()
                .filter(todo -> todo.contains("Spring"))
                .collect(Collectors.toList());

        return filteredTodos;
    }
}