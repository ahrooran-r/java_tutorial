package tutorial.learn.bytebuddy.example_1;

/**
 * I will be attempting to create some animal implementations
 * that have overridden implementations of this interface
 */
public interface Animal {

    String sound();

    String sound(boolean loud);
}
