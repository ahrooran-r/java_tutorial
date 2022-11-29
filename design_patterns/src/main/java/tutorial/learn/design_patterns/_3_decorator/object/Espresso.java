package tutorial.learn.design_patterns._3_decorator.object;

public class Espresso extends Beverage {

    @Override
    public String getDesc() {
        return " Espresso ";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
