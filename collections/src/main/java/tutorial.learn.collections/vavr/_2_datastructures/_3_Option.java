package tutorial.learn.collections.vavr._2_datastructures;

import io.vavr.control.Option;

public class _3_Option {
    public static void main(String[] args) {

        String name = null;
        Option<String> nameOption = Option.of(name);

        nameOption.getOrElse("other");

        Option<String> maybeFoo = Option.of("foo");
        Option<String> maybeFooBar = maybeFoo
                .flatMap(s -> Option.of((String) null))
                .map(s -> s.toUpperCase() + "bar");
    }
}
