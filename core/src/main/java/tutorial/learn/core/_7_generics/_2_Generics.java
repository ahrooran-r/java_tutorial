package tutorial.learn.core._7_generics;

import java.util.Date;

class GenericStore<T> {
    private T someObj;


    public T get() {
        return someObj;
    }

    public void set(T someObj) {
        this.someObj = someObj;
    }
}

public class _2_Generics {
    public static void main(String[] args) {

        Date utilDate = new Date();

        // suppose programmer_1 writes the following code
        GenericStore<Date> genericStore = new GenericStore<>();
        genericStore.set(utilDate);

        // now programmer_1 can get the object without casting
        Date newUtilDate = genericStore.get();

        // now suppose programmer_2 tries to add String object which does not belong to Date
        // genericStore.set("someString");
    }
}

/*
 * Generics provide
 *   1. Type safety at compile time
 *   2. Cleaner code
 *   3. Expressive code
 *   4. Generic
 * */
