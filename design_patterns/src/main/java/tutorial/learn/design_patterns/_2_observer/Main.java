package tutorial.learn.design_patterns._2_observer;

import tutorial.learn.design_patterns._2_observer.object.Newspaper;
import tutorial.learn.design_patterns._2_observer.object.Television;
import tutorial.learn.design_patterns._2_observer.suject.WeatherData;

public class Main {
    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();

        Newspaper newspaper = new Newspaper(weatherData);
        Television television = new Television(weatherData);

        weatherData.setMeasurements(80, 65, 30);
        weatherData.setMeasurements(82, 70, 29);
        weatherData.setMeasurements(90, 20, 40);

        television.display();
        newspaper.display();
    }
}
