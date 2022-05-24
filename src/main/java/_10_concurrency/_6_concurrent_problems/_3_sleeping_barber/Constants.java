package _10_concurrency._6_concurrent_problems._3_sleeping_barber;

public enum Constants {

    NUMBER_OF_BARBERS(1),
    NUMBER_OF_CHAIRS(5),
    NUMBER_OF_CUSTOMERS(10),
    SIMULATION_RUNNING_TIME(15 * 1000);

    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
