package tutorial.learn.design_patterns._3_decorator.object;

public class Decaf extends Beverage {

    @Override
    public String getDesc() {
        return " Decaf ";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
