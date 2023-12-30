package tutorial.learn.design_patterns._3_decorator.decorator;

import tutorial.learn.design_patterns._3_decorator.object.Beverage;

public class CaramelDecorator extends AddonDecorator {

    public CaramelDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDesc() {
        return super.getDesc() +  " Caramel decorator ";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
