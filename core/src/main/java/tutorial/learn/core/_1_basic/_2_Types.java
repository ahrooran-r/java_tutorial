void main() {

    // -------- PRIMITIVE TYPES --------

    // Whole-number types

    // 1 byte, range: -128 to 127
    byte age = 20;

    // 2 bytes, range: -32k to +32k
    short salary = 32000;

    // 4 bytes, underscore improves readability
    int milliseconds = 1_000_000_000;

    // 8 bytes, the 'L' marks it as a long literal
    long nanoseconds = 3_123_456_789L;

    // Decimal-number types

    // 4 bytes, requires 'F' suffix to mark float literal
    float bankBalance = 2100.992F;

    // 8 bytes, default type for decimals in Java
    double avgSalary = 1_320_120.276;

    // Character type -> 2 bytes, stores a single UTF-16 character
    char A = 'A';

    // Boolean type -> Can be only true or false
    boolean isCorrect = true;

    // -------- REFERENCE TYPES --------

    // Creates a object (stored on the heap)
    LocalDateTime now = LocalDateTime.now();
    // Extracts milliseconds since Unix epoch
    long nowTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    // Prints readable date/time representation
    System.out.println(now);
}
