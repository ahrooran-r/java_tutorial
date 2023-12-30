package tutorial.learn.design_patterns._1_strategy.duck;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tutorial.learn.design_patterns._1_strategy.fly.FlyBehaviour;
import tutorial.learn.design_patterns._1_strategy.quack.QuackBehaviour;

@Setter
@Slf4j
public abstract class Duck {

    @Getter(AccessLevel.NONE)
    private FlyBehaviour flyBehaviour;

    @Getter(AccessLevel.NONE)
    private QuackBehaviour quackBehaviour;

    public void swim() {
        log.info("SWIM");
    }

    public void display() {
        log.info("DISPLAY");
    }

    public void performQuack() {
        quackBehaviour.quack();
    }

    public void performFly() {
        flyBehaviour.fly();
    }
}
