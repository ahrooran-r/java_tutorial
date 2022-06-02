package _10_concurrency._6_concurrent_problems._3_sleeping_barber;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;

public class Chair {

    @Getter
    private final int id;

    private final AtomicReference<Customer> customer = new AtomicReference<>(null);

    public Chair(int id) {
        this.id = id;
    }

    public boolean isVacant() {
        return customer.get() == null;
    }

    public boolean isTaken() {
        return !isVacant();
    }

    public Customer getCustomer() {
        return customer.get();
    }

    public void occupy(Customer customer) {
        this.customer.set(customer);
        customer.assigned(this);
    }

    public void release() {
        customer.get().released(this);
        customer.set(null);
    }
}
