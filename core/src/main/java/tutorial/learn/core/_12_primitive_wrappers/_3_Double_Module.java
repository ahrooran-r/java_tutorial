package tutorial.learn.core._12_primitive_wrappers;

public class _3_Double_Module {
    public static void main(String[] args) {

        Double number = 100.12345;

        // CONSTANTS
        System.out.println(Double.POSITIVE_INFINITY); // -> Infinity
        System.out.println(Double.NEGATIVE_INFINITY); // -> -Infinity
        System.out.println(Double.NaN); // -> Not A Number, like null

        // METHODS

        Double.isFinite(number); // -> true
        Double.isInfinite(number); // -> false

        Double.isNaN(number); // -> false
    }
}
