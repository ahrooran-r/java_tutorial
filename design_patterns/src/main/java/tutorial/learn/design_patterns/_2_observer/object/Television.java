package tutorial.learn.design_patterns._2_observer.object;

import lombok.extern.slf4j.Slf4j;
import tutorial.learn.design_patterns._2_observer.Display;
import tutorial.learn.design_patterns._2_observer.subject.Subject;

@Slf4j
public class Television implements Observer, Display {

    private String toDisplay;

    public Television(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public boolean update(double temperature, double humidity, double wind) {
        toDisplay = String.format("%.2f, %.2f, %.2f", temperature, humidity, wind);
        return true;
    }

    @Override
    public void display() {
        log.info("Television {}", toDisplay);
    }
}
