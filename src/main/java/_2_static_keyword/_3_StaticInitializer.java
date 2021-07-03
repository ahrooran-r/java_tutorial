package _2_static_keyword;

/*
what is static initializer?

The static initializer is a static {} block of code inside java class, and
run only one time before the constructor or main method is called.

is a block of code static { ... } inside any java class, and executed by virtual machine when class is called.
No return statements are supported.
No arguments are supported.
No this or super are supported.

Where can I use it?

Can be used anywhere you feel ok :) that simple. But I see most of the time it is used when doing database connection,
API init, Logging and etc.
*/

import java.util.ArrayList;

class Fruit {

    static {
        System.out.println("Static Initializer invoked here");

        // fruits array
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Pear");

        // print fruits
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        System.out.println("End Static Initializer.\n");
    }

    public Fruit() {
        System.out.println("Constructor invoked here\n");
    }
}

public class _3_StaticInitializer {

    public static void main(String[] args) {

        Fruit fruit = new Fruit();
        System.out.println("Main Method invoked here\n");
    }
}
