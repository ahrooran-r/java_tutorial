package tutorial.learn.apache_commons._1_lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

public class _6_Lazyinitialization {
    public static void main(String[] args) throws ConcurrentException {

        // when the object is needed call #get method
        // automatically implements double locked mechanism
        User user = new LazyUserInitializer().get();
    }
}

class LazyUserInitializer extends LazyInitializer<User> {

    @Override
    protected User initialize() throws ConcurrentException {
        return new User("Barbossa", 45);
    }
}

@AllArgsConstructor
@Data
class User {
    private String name;
    private int age;
}
