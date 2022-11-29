package tutorial.learn.design_patterns._2_observer.subject;

import tutorial.learn.design_patterns._2_observer.object.Observer;

public interface Subject {

    boolean registerObserver(Observer observer);

    boolean removeObserver(Observer observer);

    void notifyObservers();
}
