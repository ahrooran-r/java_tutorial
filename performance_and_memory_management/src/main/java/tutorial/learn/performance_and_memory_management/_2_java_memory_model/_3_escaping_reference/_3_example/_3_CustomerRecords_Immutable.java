package tutorial.learn.performance_and_memory_management._2_java_memory_model._3_escaping_reference._3_example;

import java.util.HashMap;
import java.util.Map;

public class _3_CustomerRecords_Immutable {

    private Map<String, Customer> records;

    public _3_CustomerRecords_Immutable() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer c) {
        this.records.put(c.getName(), c);
    }

    /**
     * Most suitable solution. But there is the same performance impact as returning a duplicate collection.
     * However, the goal is achieved.
     */
    public Map<String, Customer> getRecords() {
        return Map.copyOf(records);
    }

    /**
     * Returning a read-only instance of customer.
     * <p>
     * <b> We have extracted an immutable interface out of customer and returned it to the user </b>
     * <p>
     * The reason why we are still returning a new customer because what if the user do a cast and get the customer object?
     */
    public ImmutableCustomer find(String name) {
        return new Customer(records.get(name));
    }
}
