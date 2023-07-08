package tutorial.learn.guice.example_2.model;

public record Receipt(String receiptMessage) {

    public static Receipt forSuccessfulCharge(Double amount) {
        return new Receipt("Receipt: Received " + amount);
    }

    public static Receipt forDeclinedCharge(String declineMessage) {
        return new Receipt(declineMessage);
    }

    public static Receipt forSystemFailure(String message) {
        return new Receipt(message);
    }
}
