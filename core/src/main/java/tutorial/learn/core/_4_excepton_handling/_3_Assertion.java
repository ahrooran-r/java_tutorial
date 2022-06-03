package tutorial.learn.core._4_excepton_handling;


// good to validate method parameters
// don't use exceptions in this case

class A {

    void test(int i) {
        assert i > 0 : "Invalid i in A.test";

        B b = new B();
        b.test(-1);

        C c = new C();
        c.test(-1);

        D d = new D();
        d.test(-1);
    }
}

class B {
    void test(int i) {
        assert i > 0 : "Invalid i in B.test";
    }
}

class C {
    void test(int i) {
        assert i > 0 : "Invalid i in C.test";
    }
}

class D {
    void test(int i) {
        assert i > 0 : "Invalid i in D.test";

        B b = new B();
        b.test(-1);
    }
}

public class _3_Assertion {
    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        A a = new A();
        a.test(-1);
    }
}
