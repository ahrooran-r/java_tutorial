package tutorial.learn.design_patterns._1_strategy.fly;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlyWithWings implements FlyBehaviour {

    @Override
    public void fly() {
        log.info("Fly with wings");
    }
}
