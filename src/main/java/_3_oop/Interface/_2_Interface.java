package _3_oop.Interface;

interface A {
    void foo();
}

class B {
    public void foo() {
        System.out.println("foo: from class B");
    }
}

// since B already has an implementation of `foo`,
// X does not need to write concrete method unless X wants to override that method
class X extends B implements A {

}

class Y extends B implements A {
    @Override
    public void foo() {
        System.out.println("overridden foo: from class Y");
    }
}

public class _2_Interface {
    public static void main(String[] args) {
        new X().foo();
        new Y().foo();
    }
}
