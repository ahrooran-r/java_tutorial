package tutorial.learn.guice.example_1;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import tutorial.learn.guice.example_1.draw.Draw;

@Singleton
public class Request {

    private final Draw draw;

    @Inject
    public Request(Draw draw) {
        this.draw = draw;
    }

    public void request() {
        draw.draw();
    }
}
