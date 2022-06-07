package tutorial;

import org.checkerframework.checker.index.qual.Positive;

// public class Main {
//
//     public static @Nullable Object nullable = null;
//     public Map<Object, Object> map = new HashMap<>();
//
//     public static void main(final String[] args) {
//         System.out.println("Hello World!");
//
//         StrBuilder stb = new StrBuilder();
//
//         @NonNull Object nn = nullable; // error on this line
//         System.out.println(nn);
//     }
//
//     // Test for -J--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED.
//     void mapTest(@KeyFor("map") Object k) {
//     }
// }

public class Main {

    public static @Positive int positiveInteger;

    public static void main(final String[] args) {

        positiveInteger = -4;

        System.out.println(positiveInteger);
        // should show underline on positiveInteger ???
    }
}