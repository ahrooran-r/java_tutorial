package _3_oop.Interface;

interface Sharable {

    // fields are public, static and final
    // hence field must be initialized with a value
    int number = 0;

    //methods are public and abstract by default
    void getItemData();
}

//interface can extend an interface
interface Comparable extends Sharable {

    // can have static methods with body
    static void isEqual(Object o) {
        System.out.println(o instanceof Bookmark);
    }

}

class Bookmark {
}

class Book extends Bookmark implements Comparable, Sharable {

    @Override
    public void getItemData() {
        System.out.println("implemented from: interface `Sharable`");
    }
}

public class _1_Interface {
    public static void main(String[] args) {
        Book book = new Book();
        book.getItemData();
        Comparable.isEqual(book);
    }
}
