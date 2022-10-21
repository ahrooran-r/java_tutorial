package tutorial.learn.performance_and_memory_management.java_memory_model._3_escaping_reference._3_example;

public class Main {
    public static void main(String[] args) {

        // Method 0
        _0_CustomerRecords_Raw records = new _0_CustomerRecords_Raw();

        // this is an issue with Escaping references
        // since user is given internal datastructures -> he can now do anything with it
        // such as deleting the entire records -> which the programmer would not have wanted
        records.getRecords().clear();

        records.addCustomer(new Customer("John"));
        records.addCustomer(new Customer("Simon"));
        for (Customer next : records.getRecords().values()) {
            System.out.println(next);
        }

        // another escaping reference
        records.find("John").setName("null");
        // now customer with name 'null' is actually mapped to name 'John' in records map

        // Method 1
        _1_CustomerRecords_Iterator records_iterator = new _1_CustomerRecords_Iterator();
        records_iterator.forEach(System.out::println);

        // this also has issues since we can get the iterator and delete the values
        records_iterator.iterator().remove();


        // Method 2
        _2_CustomerRecords_Duplicate duplicate = new _2_CustomerRecords_Duplicate();
        duplicate.getRecords().forEach((key, val) -> System.out.println(val));

        // Method 3
        _3_CustomerRecords_Immutable immutable = new _3_CustomerRecords_Immutable();
        immutable.getRecords().forEach((key, val) -> System.out.println(val));
    }
}
