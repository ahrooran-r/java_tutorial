package _10_concurrency._6_dining_philosophers_problem;

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
