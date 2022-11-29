package tutorial.learn.design_patterns._3_decorator.decorator;

import tutorial.learn.design_patterns._3_decorator.object.Beverage;

/**
 * Definition of a decorator -> It has an is-A and has-A relationship with the object.
 * <p>
 * The object here is: {@link Beverage}. So {@link AddonDecorator} should extend {@link Beverage}
 * and has a reference to {@link Beverage}
 */
public abstract class AddonDecorator extends Beverage {

    public Beverage beverage;

    public AddonDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * Using has-A relationship to override the cost dynamically.
     * <p>
     * So instead of getting cost from super.cost() -> this will return whatever the decorated beverage we give in
     *
     * @return computed cost of any beverage
     */
    @Override
    public int cost() {
        return beverage.cost();
    }

    @Override
    public String getDesc() {
        return beverage.getDesc();
    }
}
