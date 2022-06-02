package _7_generics;

class DateStore {
    private java.util.Date someObj;

    public java.util.Date get() {
        return someObj;
    }

    public void set(java.util.Date someObj) {
        this.someObj = someObj;
    }
}

class ObjectStore {
    private Object someObj;

    public Object get() {
        return someObj;
    }

    public void set(Object someObj) {
        this.someObj = someObj;
    }
}

public class _1_Motivation {
    public static void main(String[] args) {

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // consider the store class
        DateStore store = new DateStore();
        store.set(utilDate); //
        // this class can only work with java.sql.Date objects
        // hence other objects such as String, Integer, etc. cannot be stored

        // or we can use generic class such as Object class to store other objects
        ObjectStore objectStore = new ObjectStore();
        objectStore.set(utilDate);

        // get date object by explicit casting
        java.util.Date getDate = (java.util.Date) objectStore.get();
        // -> this works because of correct casting

        // but this results to conflict when programmer does not know what date type to expect from get() method
        // since the variable that stores object is too generic
        java.sql.Date getNewDate = (java.sql.Date) objectStore.get();
        // -> Exception in thread "main" java.lang.ClassCastException: class java.util.Date cannot be cast to class java.sql.Date

        // There is a need to identify these errors at compile time
        // Hence the need for Generics
    }
}
