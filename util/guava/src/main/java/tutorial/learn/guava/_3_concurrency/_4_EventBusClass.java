package tutorial.learn.guava._3_concurrency;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * EventBus which allows publish-subscribe communication between components
 * <p>
 * <a href="https://www.baeldung.com/guava-eventbus">tutorial</a>
 */
public class _4_EventBusClass {

    private static final EventBus eventBus = new EventBus();

    static class EventListener {

        private static int eventsHandled;
        private static int deadEvents;

        @Subscribe
        public void stringEvent(String event) {
            eventsHandled++;
        }

        // DeadEvent class that allows us to handle any events that have no listeners.
        @Subscribe
        public void handleDeadEvent(DeadEvent deadEvent) {
            deadEvents++;
        }
    }

    public static void main(String[] args) {

        EventListener listener = new EventListener();

        // registering listeners
        eventBus.register(listener);

        // un-registering listeners
        eventBus.unregister(listener);


        eventBus.post("SomeEvent");

    }
}
