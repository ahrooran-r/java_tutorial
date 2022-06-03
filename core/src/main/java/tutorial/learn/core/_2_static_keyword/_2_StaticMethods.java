package tutorial.learn.core._2_static_keyword;

class Numbers {

    // to have a `static class` make ALL the methods static
    // also make constructor private so no objects can be created from class

    private Numbers() {

    }

    public static Integer addNumbers(Integer number1, Integer number2) {
        return number1 + number2;
    }
}

public class _2_StaticMethods {
    public static void main(String[] args) {
        int add = Numbers.addNumbers(1, 2);
        System.out.println(add);
    }
}
