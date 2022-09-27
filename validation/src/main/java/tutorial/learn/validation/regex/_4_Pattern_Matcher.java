package tutorial.learn.validation.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _4_Pattern_Matcher {
    public static void main(String[] args) {

        // Compiles the given regular expression and returns an instance of pattern
        Pattern pattern = Pattern.compile(".xx.");

        // Creates a matcher that will match the given input against this pattern
        Matcher matcher = pattern.matcher("AxxB");

        // Attempts to match the entire region against the pattern.
        matcher.matches();


        System.out.println(matcher.matches());

    }
}
