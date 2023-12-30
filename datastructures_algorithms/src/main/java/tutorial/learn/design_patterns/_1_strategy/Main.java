package tutorial.learn.design_patterns._1_strategy;

import tutorial.learn.design_patterns._1_strategy.duck.DecoyDuck;
import tutorial.learn.design_patterns._1_strategy.duck.Duck;
import tutorial.learn.design_patterns._1_strategy.fly.FlyNoWay;
import tutorial.learn.design_patterns._1_strategy.quack.Squeak;

public class Main {
    public static void main(String[] args) {

        Duck duck = new DecoyDuck();
        duck.setFlyBehaviour(new FlyNoWay());
        duck.setQuackBehaviour(new Squeak());

        duck.display();
        duck.performFly();
        duck.performQuack();

    }
}
