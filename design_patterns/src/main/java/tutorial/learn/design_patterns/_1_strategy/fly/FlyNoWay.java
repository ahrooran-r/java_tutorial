package tutorial.learn.design_patterns._1_strategy.fly;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlyNoWay implements FlyBehaviour {

    @Override
    public void fly() {
        log.info("Can't fly");
    }
}
