package tutorial.learn.core._10_concurrency._6_concurrent_problems._1_dining_philosophers_problem;

public enum State {
    LEFT("left"), RIGHT("right");

    private final String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
