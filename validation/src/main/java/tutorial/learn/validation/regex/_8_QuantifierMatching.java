package tutorial.learn.validation.regex;

import java.util.regex.Pattern;

public class _8_QuantifierMatching {
    public static void main(String[] args) {
        boolean x;

        x = Pattern.matches("[abc]?", "");
        // true
        // reason: `a` or `b` or `c` occurs once or not at all -> input has no letters

        x = Pattern.matches("[abc]?", "aaa");
        // false
        // reason: `a` can come once or not at all. here `a` occurs more than once

        // ------------------------------------------------------------------------------------------------

        x = Pattern.matches("[abc]+", "");
        // false
        // reason: a or b or c should occur one or more times. here empty string. so false

        x = Pattern.matches("[abc]+", "bbaacc");
        // true
        // reason: see above

        System.out.println(x);
    }
}
