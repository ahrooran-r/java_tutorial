package tutorial.learn.guice.example_2.service.log_service;

import tutorial.learn.guice.example_2.model.ChargeResult;

public interface TransactionLog {
    void logChargeResult(ChargeResult chargeResult);

    void logException(RuntimeException e);
}
