package _1_basic;

import java.awt.*;

public class _3_Types {
    public static void main(String[] args) {
        byte x = 1;
        byte y = x;
        x = 2;
        System.out.printf("%d, %d \n", x, y);

        Point point1 = new Point(1, 2);
        Point point2 = point1;
        point1.x = 3;
        System.out.println(point2);

        // reference types are copied by the `references`
        // If we change original reference, the other references that points to the original reference will also change
        // primitive types are copied by `value`

    }
}
