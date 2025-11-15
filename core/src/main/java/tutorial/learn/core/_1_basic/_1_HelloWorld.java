
// unnamed classes and simplified launch
// This only works when the file is run directly with java _1_HelloWorld.java
void main() {
    // access modifier -> public, private
    System.out.println("Hello World");

    greet();
}

// allow running top-level methods in unnamed classes:
void greet() {
    System.out.println("Greetings from Java !!!");
}
