package tutorial.learn.design_patterns._3_decorator.decorator;

import tutorial.learn.design_patterns._3_decorator.object.Beverage;

public class SoyDecorator extends AddonDecorator{

    public SoyDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDesc() {
        return "Soy decorator";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
