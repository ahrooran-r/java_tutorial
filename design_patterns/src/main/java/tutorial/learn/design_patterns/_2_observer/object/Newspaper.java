package tutorial.learn.design_patterns._2_observer.object;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.math.Stats;
import lombok.extern.slf4j.Slf4j;
import tutorial.learn.design_patterns._2_observer.Display;
import tutorial.learn.design_patterns._2_observer.suject.Subject;

@Slf4j
public class Newspaper implements Observer, Display {

    private final Multimap<String, Double> details = ArrayListMultimap.create();

    public Newspaper(Subject subject) {
        subject.registerObserver(this);
    }

    /**
     * Newspaper will provide average statistics over a period
     */
    @Override
    public boolean update(double temperature, double humidity, double wind) {

        details.get("temperature").add(temperature);
        details.get("humidity").add(temperature);
        details.get("wind").add(temperature);

        return true;
    }

    @Override
    public void display() {

        double localTemperature = Stats.meanOf(details.get("temperature"));
        double localHumidity = Stats.meanOf(details.get("humidity"));
        double localWind = Stats.meanOf(details.get("wind"));

        log.info("Newspaper: {}, {}, {}",
                localTemperature,
                localHumidity,
                localWind
        );
    }
}
