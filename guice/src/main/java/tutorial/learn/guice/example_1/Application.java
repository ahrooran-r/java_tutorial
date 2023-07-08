package tutorial.learn.guice.example_1;

import com.google.inject.Guice;
import com.google.inject.Injector;
import tutorial.learn.guice.example_1.modules.AppModule;

public class Application {

    public static final String SQUARE_REQ = "square";

    public static void main(String[] args) {
        sendReq(SQUARE_REQ);
    }

    public static void sendReq(String request) {
        switch (request) {
            case SQUARE_REQ -> {
                // Draw d = new DrawSquare();
                // Request r = new Request(d);
                // r.request();

                Injector injector = Guice.createInjector(new AppModule());
                Request r = injector.getInstance(Request.class);
                r.request();
            }
        }
    }
}
