package tutorial.learn.apache_commons._1_lang;

import org.apache.commons.lang3.math.Fraction;

public class _4_Fraction {
    public static void main(String[] args) {

        Fraction.getFraction(5, 6);

        Fraction.getFraction("2/3");
        Fraction.getFraction("0.667");
        Fraction.getFraction(2, 1, 3);
        Fraction.getReducedFraction(3, 9); // = 1/3

        var x = Fraction.getFraction("23.56");
        var y = Fraction.getFraction("1.25");

        x.getNumerator();
        x.getDenominator();
        // gets proper fraction format
        x.toProperString();

        x.doubleValue();
        // similarly other values can be returned

        x.invert(); // = 1 / x

        x.add(y);
        x.subtract(y);
        x.multiplyBy(y);
        x.divideBy(y);
        x.pow(3);
    }
}
