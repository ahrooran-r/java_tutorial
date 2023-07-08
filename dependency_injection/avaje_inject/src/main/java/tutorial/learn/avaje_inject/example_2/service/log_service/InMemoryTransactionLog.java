package tutorial.learn.avaje_inject.example_2.service.log_service;


import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import tutorial.learn.avaje_inject.example_2.model.ChargeResult;

@Singleton
@Slf4j
public class InMemoryTransactionLog implements TransactionLog {
    @Override
    public void logChargeResult(ChargeResult chargeResult) {
        log.info("Charge has been processed for {}", chargeResult.amount());
    }

    @Override
    public void logException(RuntimeException e) {
        log.error(e.getMessage(), e);
    }
}
