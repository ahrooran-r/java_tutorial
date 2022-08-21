package tutorial.learn.design_patterns._1_strategy.duck;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoyDuck extends Duck {

    @Override
    public void display() {
        log.info("Looks like a Decoy");
    }
}
