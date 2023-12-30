package tutorial.learn.design_patterns._2_observer.object;

import lombok.extern.slf4j.Slf4j;
import tutorial.learn.design_patterns._2_observer.Display;
import tutorial.learn.design_patterns._2_observer.subject.Subject;

import java.util.*;

@Slf4j
public class Newspaper implements Observer, Display {

    private final Map<String, List<Double>> details;

    public Newspaper(Subject subject) {
        subject.registerObserver(this);
        details = new HashMap<>();
    }

    /**
     * Newspaper will provide average statistics over a period
     */
    @Override
    public boolean update(double temperature, double humidity, double wind) {

        Object result;

        result = details.putIfAbsent("temperature", new ArrayList<>(List.of(temperature)));
        if (null != result) details.get("temperature").add(temperature);

        result = details.putIfAbsent("humidity", new ArrayList<>(List.of(humidity)));
        if (null != result) details.get("humidity").add(temperature);

        result = details.putIfAbsent("wind", new ArrayList<>(List.of(wind)));
        if (null != result) details.get("wind").add(temperature);

        return true;
    }

    @Override
    public void display() {

        double localTemperature = meanOf(details.get("temperature"));
        double localHumidity = meanOf(details.get("humidity"));
        double localWind = meanOf(details.get("wind"));

        log.info("Newspaper: {}, {}, {}",
                localTemperature,
                localHumidity,
                localWind
        );
    }

    private double meanOf(Collection<Double> collection) {
        return collection.stream().reduce(0.0, Double::sum) / collection.size();
    }
}
