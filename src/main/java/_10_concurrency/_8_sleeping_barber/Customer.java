package _10_concurrency._8_sleeping_barber;

import lombok.Getter;

public class Customer {

    @Getter
    private final int id;

    public Customer(int id) {
        this.id = id;
    }

    public void assigned(Chair chair) {
        System.out.printf("Customer-%d is assigned to Chair-%d", id, chair.getId());
    }

    public void released(Chair chair) {
        System.out.printf("Customer-%d is released from Chair-%d", id, chair.getId());
    }

}
