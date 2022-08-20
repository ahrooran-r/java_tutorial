package tutorial.learn.apache_commons.lang;

import org.apache.commons.lang3.StringUtils;

/**
 * StringUtils allows us to perform a bunch of null-safe strings operations that
 * complement/extend the ones that java.lang.String provides out of the box.
 */
public class _1_StringUtils {
    public static void main(String[] args) {

        // Abbreviates a String using ellipses
        StringUtils.abbreviate("Now is the time for all good men", 10); // =  Now is ...
        // Appends the suffix to the end of the string if the string does not already end with the suffix.
        StringUtils.appendIfMissing("Zebra", "s"); // = Zebras
        // Centers a String in a larger String of size size using the space character (' ').
        StringUtils.center("a", 5); // = "  a  "
        // Removes one newline from end of a String if it's there, otherwise leave it alone.
        // A newline is "\n", "\r", or "\r\n".
        StringUtils.chomp("Hello World\n"); // = HelloWorld
        StringUtils.deleteWhitespace("Hello World");
        StringUtils.normalizeSpace("Hello   World  !!!"); // = Hello World !!!

        StringUtils.containsIgnoreCase("Hello World", "He");
        StringUtils.containsAny("Hello World", "He", "Hello");
        StringUtils.containsAnyIgnoreCase("Hello World", "He", "Hello");
        StringUtils.containsNone("Hello World", 'H', 'e');
        StringUtils.containsWhitespace("Hello World");

        // endsWithAny methods
        // equalsAny methods

        StringUtils.defaultIfBlank(" ", "blah");
        // Returns either the passed in String, or if the String is null, an empty String ("").
        StringUtils.defaultString(null); // = ""
        StringUtils.defaultIfEmpty("", "blah"); // above both methods combined
        StringUtils.getIfBlank(" ", () -> "someSupplier");
        StringUtils.getIfEmpty(" ", () -> "someSupplier");

        // Checks if a String str contains Unicode digits,
        // if yes then concatenate all the digits in str and return it as a String.
        // Good for getting numbers from phoneNumbers etc.
        StringUtils.getDigits("(541) 754-3010"); // = "5417543010"
    }
}
