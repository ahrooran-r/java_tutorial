package tutorial.learn.avaje_inject.example_2.annotations;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link Qualifier} is like @BindingAnnotation from guice.
 * This gives a strongly typed approach to qualifying the beans rather than using string literals in @Named
 */
@Qualifier
@Retention(RUNTIME)
public @interface Paypal {
}
