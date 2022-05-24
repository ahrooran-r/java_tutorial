package _10_concurrency._6_concurrent_problems._1_dining_philosophers_problem;

public enum Constants {

    NUMBER_OF_PHILOSOPHERS(5),
    NUMBER_OF_CHOPSTICKS(5),
    SIMULATION_RUNNING_TIME(15 * 1000);

    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
