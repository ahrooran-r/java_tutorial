package _3_oop.Interface;

interface P {

    // fields are public, static and final
    int VAL = 80;
}

interface Q {
    int VAL = 81;
}

class R implements P, Q {
    void foo() {
        // int i = VAL; will throw error <- because VAL is used in both P and Q and I import both of them here
        // if either one has different name, then direct usage will work
        int i = P.VAL;
    }
}

public class _3_Interface {
}
