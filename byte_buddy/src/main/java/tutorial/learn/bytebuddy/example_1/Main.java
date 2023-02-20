package tutorial.learn.bytebuddy.example_1;

import ch.qos.logback.core.net.ObjectWriter;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {

        DynamicType.Unloaded<Animal> dogClassUnloaded = new ByteBuddy()
                .subclass(Animal.class)
                .name("Dog")

                // we can use bytebuddy modifiers
                .defineField("colour", String.class, Visibility.PUBLIC, FieldManifestation.FINAL)

                .defineConstructor(Visibility.PUBLIC)
                // or java modifiers -> Modifier.FINAL + Modifier.PRIVATE like that
                .withParameter(String.class, "colour", Modifier.FINAL)
                .intercept(
                        MethodCall.invoke(Object.class.getConstructor()).onSuper()
                                .andThen(
                                        // field arguments are denoted by their index
                                        FieldAccessor.ofField("colour").setsArgumentAt(0)
                                )
                )

                // There are different ways to match the methods, fields etc.
                // https://www.tabnine.com/code/java/classes/net.bytebuddy.matcher.ElementMatchers

                // intercepting no argument method
                .method(ElementMatchers.named("sound")
                        .and(ElementMatchers.takesNoArguments())
                )
                .intercept(FixedValue.value("woof quiet"))


                // intercepting overloaded method with arguments
                .method(
                        ElementMatchers.named("sound")
                                .and(ElementMatchers.takesArgument(0, boolean.class))
                )
                .intercept(FixedValue.value("woof woof loud"))

                .make();

        Class<? extends Animal> dogClassLoaded = dogClassUnloaded
                .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Animal dog = dogClassLoaded.getDeclaredConstructor(String.class).newInstance("black");
        // Animal dog = dogClassLoaded.getDeclaredConstructor().newInstance();

        var x = dog.sound();
        var y = dog.sound(true);

        System.out.println(x);
        System.out.println(y);


        // What if Dog.class has other methods / fields not in Animal.class
        Field m = dogClassLoaded.getDeclaredField("colour");
        String colour = (String) m.get(dog);
        System.out.println(colour);

    }
}
