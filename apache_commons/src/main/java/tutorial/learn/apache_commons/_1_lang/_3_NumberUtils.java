package tutorial.learn.apache_commons._1_lang;

import org.apache.commons.lang3.math.NumberUtils;

public class _3_NumberUtils {
    public static void main(String[] args) {

        // creates a number from string
        NumberUtils.createNumber("25");

        // can we convert this string into number?
        NumberUtils.isCreatable("25");
        NumberUtils.isParsable("25");

        // max-min of arrat
        NumberUtils.max(1, 2, 3, 4, 5);
        NumberUtils.min(1, 2, 3, 4, 5);
    }
}
