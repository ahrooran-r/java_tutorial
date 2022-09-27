package tutorial.learn.validation.regex;

import java.util.regex.Pattern;

public class _9_MetaCharacters {
    public static void main(String[] args) {
        boolean x;

        x = Pattern.matches("\\d", "");
        // false
        // reason: d represent any digit but empty string given

        x = Pattern.matches("\\d", "23a");
        // false
        // reason: d matches only digits

        x = Pattern.matches("\\d", "1");
        // true
        // reason: only digits

        // ------------------------------------------------------------------------------------------------

        x = Pattern.matches("\\D", "bbcc");
        // false
        // reason: \D means non-digits. But here more than one non-digit present

        x = Pattern.matches("[\\D]*", "bbcc");
        // true
        // reason: \D means non-digits. here 0 or any number of non-digits can be present. hence true

        System.out.println(x);
    }
}
