package tutorial.learn.guava._1_basics;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;

/**
 * Google has come up with its own Objects class.
 * <p>
 * But JDK already has one and that is preferred
 * <p>
 * <a href="https://github.com/google/guava/wiki/CommonObjectUtilitiesExplained">tutorial</a>
 */
@AllArgsConstructor
public class _3_ObjectsClass {
    private int someValue;
    private String someOtherValue;

    /**
     * see {@link MoreObjects#toStringHelper(Class)}. It helps create easier toString methods
     */
    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("someValue", someValue)
                .add("someOtherValue", someOtherValue)
                .toString();
    }

    public static void main(String[] args) {
        _3_ObjectsClass obj = new _3_ObjectsClass(20, "HelloWorld!");
        System.out.println(obj);
    }
}
