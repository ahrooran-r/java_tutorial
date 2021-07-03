package _3_oop.Interface;

interface P {
    int VAL = 80;
}

interface Q {
    int VAL = 81;
}

class R implements P, Q {
    void foo() {
        // int i = VAL; will throw error
        int i = P.VAL;
    }
}

public class _3_Interface {
}
