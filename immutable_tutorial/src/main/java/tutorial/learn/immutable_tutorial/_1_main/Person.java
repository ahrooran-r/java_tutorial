package tutorial.learn.immutable_tutorial._1_main;

import org.immutables.value.Value;
import tutorial.learn.immutable_tutorial._2_utils.ImmutableEvent;

import java.util.List;
import java.util.Set;

// Tutorial: https://immutables.github.io/immutable.html
// Tutorial: https://www.baeldung.com/immutables
// `prehash` -> Since our generated classes are immutable and can never get modified,
// hashCode results will always remain the same and can be computed only once during the object's instantiation.
@Value.Immutable(prehash = true)
public abstract class Person {

    // any annotated classes should be abstract classes -> Immutables library will generate actual implementations
    // with name `ImmutablePerson` etc.

    // no attributes. only abstract methods

    // A constructor will be generated when a method is annotated with `@Value.Parameter`
    @Value.Parameter
    abstract int age();

    @Value.Parameter
    // When a value is not provided via constructor -> default value is used
    @Value.Default
    String name() {
        return "John Doe";
    }

    // `@Value.Auxiliary` annotation can be used for annotating a property that will be stored in an object's instance,
    // but will be ignored by equals, hashCode and toString implementations.
    @Value.Auxiliary
    abstract Set<Person> siblings();

    abstract List<ImmutableEvent> events();
}
