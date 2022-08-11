package tutorial.learn.immutable._2_utils;

import org.immutables.value.Value;

import java.time.LocalDate;

@Value.Immutable
public abstract class Event {

    abstract LocalDate date();

    abstract String event();
}
