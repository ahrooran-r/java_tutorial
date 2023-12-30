package tutorial.learn.design_patterns._3_decorator;

import lombok.extern.slf4j.Slf4j;
import tutorial.learn.design_patterns._3_decorator.decorator.CaramelDecorator;
import tutorial.learn.design_patterns._3_decorator.decorator.SoyDecorator;
import tutorial.learn.design_patterns._3_decorator.object.Beverage;
import tutorial.learn.design_patterns._3_decorator.object.Decaf;

@Slf4j
public class Main {
    public static void main(String[] args) {

        // this is a basic object
        Beverage basicBeverage = new Decaf();

        // now we want to decorate
        Beverage decoratedBeverageWithCaramel = new CaramelDecorator(basicBeverage);

        // again decorate
        Beverage decoratedBeverageWithCaramelAndSoy = new SoyDecorator(decoratedBeverageWithCaramel);

        log.info("Should return 4 -> returned value: {}", decoratedBeverageWithCaramelAndSoy.cost());
    }
}
