package tutorial.learn.performance_and_memory_management._6_monitoring_heap._1_soft_leak;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class CustomerManager {

    private List<Customer> customers = new ArrayList<>();
    private int nextAvalailbleId = 0;
    private int lastProcessedId = -1;

    public void addCustomer(Customer customer) {
        synchronized (this) {
            customer.withId(nextAvalailbleId);
            synchronized (customers) {
                customers.add(customer);
            }
            nextAvalailbleId++;
        }

    }

    @Deprecated
    public Optional<Customer> getNextCustomer() {

        // this is where soft leak occurs
        // instead of removing customer from list, this method just returns the next customer
        if (lastProcessedId + 1 > nextAvalailbleId) {
            lastProcessedId++;
            return Optional.of(customers.get(lastProcessedId));
        }
        return Optional.empty();
    }

    // /**
    //  * correct way
    //  */
    // public Optional<Customer> getNextCustomer() {
    //     synchronized (this) {
    //         if (!customers.isEmpty()) {
    //             lastProcessedId++;
    //             return Optional.of(customers.remove(0));
    //         }
    //         return Optional.empty();
    //     }
    // }


    public void howManyCustomers() {
        int size = customers.size();
        System.out.println("" + new Date() + " Customers in queue : " + size + " of " + nextAvalailbleId);
    }

}
