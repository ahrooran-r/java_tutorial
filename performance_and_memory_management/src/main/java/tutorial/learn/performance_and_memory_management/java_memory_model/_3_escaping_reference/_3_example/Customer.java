package tutorial.learn.performance_and_memory_management.java_memory_model._3_escaping_reference._3_example;

public class Customer implements ImmutableCustomer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    /**
     * This returns a duplicate of customer.
     * But will become cumbersome when there are lots of fields.
     */
    public Customer(Customer other) {
        this.name = other.getName();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer(name=" + this.getName() + ")";
    }
}
