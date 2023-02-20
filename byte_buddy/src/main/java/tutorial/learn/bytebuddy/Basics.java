package tutorial.learn.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;

public class Basics {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        DynamicType.Unloaded<?> unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("HelloWorld"))
                .make();

        Class<?> dynamicType = unloadedType
                .load(Basics.class.getClassLoader())
                .getLoaded();

        var x = dynamicType.getDeclaredConstructor().newInstance();


        System.out.println(x);
    }
}
