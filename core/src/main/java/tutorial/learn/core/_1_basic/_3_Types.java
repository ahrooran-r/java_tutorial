import java.awt.*;

void main() {

    // -------- PRIMITIVE TYPE COPY (VALUE COPY) --------

    // x holds the value 1
    byte x = 1;

    // y receives a *copy* of x's value
    byte y = x;

    // modifying x does NOT affect y
    x = 2;
    System.out.printf("%d, %d \n", x, y);
    // Output: 2, 1


    // -------- REFERENCE TYPE COPY (REFERENCE COPY) --------

    // Creates a Point object in memory
    Point point1 = new Point(1, 2);
    // point2 stores the same reference as point1
    Point point2 = point1;

    // Modifying the object through point1
    point1.x = 3;
    // also affects point2 because both refer to the same object

    // Shows updated state of the shared object
    System.out.println(point2);

    // -------- SUMMARY --------
    // Primitive types (byte, int, etc.) store actual values.
    // When assigned, the value is copied → changes stay isolated.

    // Reference types (objects) store a memory reference.
    // When assigned, the reference is copied → both variables point to the same object.
    // Changing the object through one variable is visible through the other.
}
