package tutorial.learn.design_patterns._2_observer.subject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import tutorial.learn.design_patterns._2_observer.object.Observer;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Data
@Setter(AccessLevel.NONE)
public class WeatherData implements Subject {

    private final Set<Observer> observers = new ConcurrentSkipListSet<>();

    private double temperature;
    private double humidity;
    private double wind;

    @Override
    public boolean registerObserver(Observer observer) {
        return observers.add(Objects.requireNonNull(observer));
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(getTemperature(), getHumidity(), getWind()));
    }

    public void setMeasurements(double temperature, double humidity, double wind) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        notifyObservers();
    }
}
