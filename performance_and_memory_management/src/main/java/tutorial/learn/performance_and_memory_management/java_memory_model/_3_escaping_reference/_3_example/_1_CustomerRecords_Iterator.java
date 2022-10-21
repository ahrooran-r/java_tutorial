package tutorial.learn.performance_and_memory_management.java_memory_model._3_escaping_reference._3_example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class _1_CustomerRecords_Iterator implements Iterable<Customer> {

    private Map<String, Customer> records;

    public _1_CustomerRecords_Iterator() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer c) {
        this.records.put(c.getName(), c);
    }

    /**
     * only exposing iterator of underlying collection
     */
    @Override
    public Iterator<Customer> iterator() {
        return records.values().iterator();
    }
}
