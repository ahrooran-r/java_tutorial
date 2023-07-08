package tutorial.learn.guice.example_1.draw;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class DrawSquare implements Draw {

    private final String colour;
    private final int edge;

    @Inject
    public DrawSquare(String colour, int edge) {
        this.colour = colour;
        this.edge = edge;
    }

    @Override
    public void draw() {
        log.info("drawing SQUARE of colour: {} and edge: {}", colour, edge);
    }
}
