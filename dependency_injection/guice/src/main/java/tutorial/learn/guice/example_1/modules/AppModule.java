package tutorial.learn.guice.example_1.modules;

import com.google.inject.AbstractModule;
import tutorial.learn.guice.example_1.draw.ColourValue;
import tutorial.learn.guice.example_1.draw.Draw;
import tutorial.learn.guice.example_1.draw.DrawSquare;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Draw.class).to(DrawSquare.class);

        // bind to already created objects
        bind(String.class).annotatedWith(ColourValue.class).toInstance("Red");
        bind(int.class).toInstance(20);
    }
}
