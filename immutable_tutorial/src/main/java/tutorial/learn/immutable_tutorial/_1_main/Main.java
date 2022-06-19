package tutorial.learn.immutable_tutorial._1_main;

import lombok.extern.slf4j.Slf4j;
import tutorial.learn.immutable_tutorial._2_utils.ImmutableEvent;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {

        List<ImmutableEvent> events1 = List.of(
                ImmutableEvent.builder().date(LocalDate.now()).event("Happy Diwali").build(),
                ImmutableEvent.builder().date(LocalDate.now()).event("Happy New Year").build()
        );
        List<ImmutableEvent> events2 = List.of(
                ImmutableEvent.builder().date(LocalDate.now()).event("Happy Pongal").build()
        );

        Person person2 = ImmutablePerson.builder()
                .name("Kong")
                .age(10)
                .addAllEvents(events2)
                .build();

        Person person1 = ImmutablePerson.builder()
                // Note: I haven't added age to person1. But it copies that from person2 automatically
                // because it is `from` person 2
                .name("King")
                .from(person2)
                .addSiblings(person2)
                .events(events1)
                .build();

        log.info(String.valueOf(person1));

        // .of() -> constructor
        ImmutablePerson.of(100, "SomeName");
    }
}
