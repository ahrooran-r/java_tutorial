package tutorial.learn.performance_and_memory_management._2_java_memory_model._3_escaping_reference._3_example;

import java.util.HashMap;
import java.util.Map;

public class _0_CustomerRecords_Raw {

    private Map<String, Customer> records;

    public _0_CustomerRecords_Raw() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer c) {
        this.records.put(c.getName(), c);
    }

    /**
     * This is an escaping reference. We are returning a modifiable customer object
     */
    public Customer find(String name) {
        return records.get(name);
    }

    /**
     * This is an escaping reference.
     * <p>
     * We are returning an entire modifiable collection to an outsider.
     */
    public Map<String, Customer> getRecords() {
        return this.records;
    }
}
