package tutorial.learn.performance_and_memory_management._2_java_memory_model._3_escaping_reference._3_example;

import java.util.HashMap;
import java.util.Map;

public class _2_CustomerRecords_Duplicate {

    private Map<String, Customer> records;

    public _2_CustomerRecords_Duplicate() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer c) {
        this.records.put(c.getName(), c);
    }

    /**
     * this has performance implications since we are creating new hashmap
     * everytime we call the getRecords method.
     * <p>
     * Although in this case instead of copying objects, we're just copying references every time
     * Hence, this is an elegant and acceptable in most cases
     * <p>
     * Java uses 4 or 8 bytes per reference. So even if original collection has 1000 objects,
     * each time only 4000 or 8000 bytes shall be copied
     * <p>
     * Also in most cases, these objects are short-lived. So, in terms of garbage collection, this is actually good
     */
    public Map<String, Customer> getRecords() {
        return new HashMap<>(this.records);
    }

    /**
     * Duplicating the object itself to prevent escaping reference
     */
    public Customer find(String name) {
        return new Customer(records.get(name));
    }
}
