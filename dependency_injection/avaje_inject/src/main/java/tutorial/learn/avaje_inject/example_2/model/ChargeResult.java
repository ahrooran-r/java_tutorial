package tutorial.learn.avaje_inject.example_2.model;

public record ChargeResult(
        boolean wasSuccessful,
        Double amount
) {
    public String getDeclineMessage() {
        if (!wasSuccessful) return "Declined transaction";
        return "";
    }
}
