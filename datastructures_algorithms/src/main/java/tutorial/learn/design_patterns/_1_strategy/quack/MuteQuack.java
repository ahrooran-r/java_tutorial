package tutorial.learn.design_patterns._1_strategy.quack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MuteQuack implements QuackBehaviour {

    @Override
    public void quack() {
        log.info("Can't quack!!!");
    }
}
