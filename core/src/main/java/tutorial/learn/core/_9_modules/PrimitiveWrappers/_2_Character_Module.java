package tutorial.learn.core._9_modules.PrimitiveWrappers;

public class _2_Character_Module {
    public static void main(String[] args) {

        // initialization
        Character character = 'A';

        Character.isLetter('2'); // -> false
        Character.isDigit('2'); // -> true
        Character.isLetterOrDigit('2'); // -> true

        Character.isWhitespace(' '); // -> true

        Character.isUpperCase('A'); // -> true
        Character.isLowerCase('A'); // -> false

        Character.toUpperCase('a'); // -> 'A'
        Character.toLowerCase('A'); // -> 'a'
    }
}
