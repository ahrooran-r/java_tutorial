package tutorial.learn.avaje_inject.example_2.service.log_service;


import tutorial.learn.avaje_inject.example_2.model.ChargeResult;

public interface TransactionLog {
    void logChargeResult(ChargeResult chargeResult);

    void logException(RuntimeException e);
}
