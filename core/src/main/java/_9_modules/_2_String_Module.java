package _9_modules;

public class _2_String_Module {
    public static void main(String[] args) {
        String msg1 = new String("Hello World");
        String msg2 = "Hello World!!!";
        // both are same

        // escape chars in a string
        String msg3 = "Name: \n\"Ahrooran\"";

        // concatenating strings
        String msg4 = "Hello" + " World " + "!!!";
        String msg5 = "Hello" + "World!" + 12345;
        String msg6 = "Hello" + "World!" + 100 + 25.5; // -> HelloWorld!10025.5
        String msg7 = 100 + 25.5 + "Hello" + "World!"; // -> 125.5HelloWorld!

        // strings are IMMUTABLE
        // but mutability can be achieved through StringBuilder, StringBuffer class
        StringBuilder sb = new StringBuilder();
        sb.append("hello").append("hello").append("world").toString();
        // Other methoods of StringBuilder -> length(), delete(), reverse(), replace()
        // only use StringBuffer in case of concurrent programming. Otherwise use StringBuilder

        // STRING METHODS
        String message = "Hello World !!!";
        // basic
        message.length(); // -> 15
        message.isEmpty(); // -> false (check if string is empty or not)
        message.isBlank(); // -> false (check if string has anything other than whitespace)

        // comparison
        message.equals("hello world !!!"); // -> false (case sensitive)
        message.equalsIgnoreCase("hello world !!!"); // -> true

        // searching
        message.contains("hello"); // -> false (case sensitive)
        message.startsWith("He");
        message.endsWith("!!!");
        message.indexOf("lo"); // -> 3
        message.lastIndexOf("o"); // -> 7

        // extracting individual chars
        message.charAt(10); // -> d
        message.toCharArray(); // -> [H, e, l, l, o,  , W, o, r, l, d,  , !, !, !]

        // extracting substrings
        message.substring(4); // -> o World!!!
        message.substring(4, 9); // -> o Wor

        // replace chars
        message.replace('!', '*');
        message.replaceAll("llo", "lo");

        // case conversion
        message.toLowerCase();
        message.toUpperCase();

        // strip and split
        message.strip();
        message.stripLeading();
        message.stripTrailing();
        message.split("o"); // -> [Hell,  W, rld !!!]
    }
}
