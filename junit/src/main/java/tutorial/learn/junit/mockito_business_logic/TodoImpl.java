package tutorial.learn.junit.mockito_business_logic;

import java.util.ArrayList;
import java.util.List;

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

        List<String> filteredTodos = new ArrayList<>();
        List<String> allTodos = todoInterface.retrieveTodos(user);
        for (String todo : allTodos) {
            if (todo.contains("Spring")) {
                filteredTodos.add(todo);
            }
        }

        return filteredTodos;
    }
}