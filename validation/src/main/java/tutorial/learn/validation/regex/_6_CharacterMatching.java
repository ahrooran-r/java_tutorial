package tutorial.learn.validation.regex;

import java.util.regex.Pattern;

public class _6_CharacterMatching {
    public static void main(String[] args) {
        boolean x;

        x = Pattern.matches("[abc]", "abc");
        // false
        // reason: adf is not among a or b or c

        x = Pattern.matches("[abc]", "abc");
        // false
        // reason: abc is not among a or b or c

        x = Pattern.matches("[abc]", "b");
        // true
        // reason: b is among a or b or c

        System.out.println(x);
    }
}
