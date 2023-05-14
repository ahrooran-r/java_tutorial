package tutorial.learn.performance_and_memory_management._3_garbage_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class _2_GCExample {

    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();

        long availableBytes = runtime.freeMemory();
        System.out.println("Available memory at start: " + availableBytes / 1024 + "k");

        // let's create lots of objects....
        List<Customer> customers = IntStream
                .range(0, 1_000_000)
                .mapToObj(i -> new Customer("John"))
                .toList();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  when customers created: " + availableBytes / 1024 + "k");

        customers = new ArrayList<>();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  when customers no longer referenced: " + availableBytes / 1024 + "k");

        Thread.sleep(1000);

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  1 second later: " + availableBytes / 1024 + "k");

        // garbage collection
        System.gc();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  after GC command: " + availableBytes / 1024 + "k");
    }

    public record Customer(String name) {
        public Customer(Customer oldCustomer) {
            this(oldCustomer.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /*
     * In Java 11 and above, once a GC happens, Java will actually
     * return the freed memory back to the OS
     * This causes a slight performance hindrance that memory allocation
     * and reallocation slightly more expensive
     * So we can make java have an initial set of memory by using a flag
     *
     * -Xms300m
     *
     */

}