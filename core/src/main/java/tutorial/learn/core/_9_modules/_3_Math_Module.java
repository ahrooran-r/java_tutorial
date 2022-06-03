package tutorial.learn.core._9_modules;

public class _3_Math_Module {
    public static void main(String[] args) {

        // Math.round() rounds to nearest int or long value
        // if double -> round to long, float -> round to int
        // use following trick to round to specific decimal place
        float round = Math.round(3.12355F * 1000) / 1000.0F;

        // ceil and floor
        Math.ceil(3.12355F);
        Math.floor(3.12355F);

        // generate double number in range[0,1]
        Math.random();

        // to generate integer random numbers in range[0,100]
        // method 1
        int randInt100 = (int) Math.round(Math.random() * 100);
        // method 2
        randInt100 = (int) (Math.random() * 100);

        // get absolute value of a number
        Math.abs(-1.5F);

        // maximum, minimum
        Math.max(1, 2);
        Math.min(3.5, 4);

        // square root
        Math.sqrt(25);


    }
}
