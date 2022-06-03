package tutorial.learn.core._3_oop.Interface;

class CloneableClass implements Cloneable {

    // Overriding Object.clone()
    @Override
    public CloneableClass clone() {
        try {
            return (CloneableClass) super.clone();
        } catch (CloneNotSupportedException e) {
            // `CloneNotSupportedException` is invoked when `Object` class sees if `CloneableClass` does not
            // implement `Cloneable` interface
            return null;
        }
    }

}

public class _4_Cloneable_Interface {
}
