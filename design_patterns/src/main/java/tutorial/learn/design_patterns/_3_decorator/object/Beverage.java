package tutorial.learn.design_patterns._3_decorator.object;

public abstract class Beverage {

    public int cost() {
        // return a base profit of 1 unit
        return 1;
    }

    public abstract String getDesc();
}
