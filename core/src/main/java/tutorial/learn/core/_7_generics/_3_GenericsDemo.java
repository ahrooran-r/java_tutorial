package tutorial.learn.core._7_generics;

import java.util.Arrays;
import java.util.List;

// a generic interface
interface Container<T> {
    void set(T a);

    T get();
}

class Store<T> implements Container<T> {

    private T store;

    @Override
    public void set(T store) {
        this.store = store;
    }

    @Override
    public T get() {
        return this.store;
    }
}

public class _3_GenericsDemo {
    public static void main(String[] args) {

        Container<String> stringStore = new Store<>();
        stringStore.set("someString");
        System.out.println(stringStore.get());

        Container<Integer> integerStore = new Store<>();
        integerStore.set(50);
        System.out.println(integerStore.get());

        Container<List<Integer>> listStore = new Store<>();
        listStore.set(Arrays.asList(1, 2, 3));
        System.out.println(listStore.get().toString());
    }
}
