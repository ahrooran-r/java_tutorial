package _3_oop;

// cannot instantiate abstract classes
abstract class Fruit {

    // abstract method must be overridden by subclasses
    abstract void isEatable(String fruit);
}

abstract class AbstractFruit extends Fruit {
    @Override
    void isEatable(String fruit) {
        System.out.println("class: AbstractFruit and super class: Fruit, + String: " + fruit);
    }
}

class RealFruit extends AbstractFruit {
    @Override
    void isEatable(String fruit) {
        super.isEatable(fruit);
        System.out.println("class: RealFruit");
    }
}

public class _1_AbstractClass {
    public static void main(String[] args) {

        Fruit someFruit = new RealFruit();
        someFruit.isEatable("someFruit");

        Fruit otherFruit = new RealFruit();
        otherFruit.isEatable("otherFruit");
    }
}
