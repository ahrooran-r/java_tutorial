package tutorial.learn.guice.example_2.service.billing_service;

import lombok.Getter;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.CreditCardProcessor;
import tutorial.learn.guice.example_2.service.log_service.TransactionLog;
import tutorial.learn.guice.example_2.model.ChargeResult;
import tutorial.learn.guice.example_2.model.CreditCard;
import tutorial.learn.guice.example_2.model.PizzaOrder;
import tutorial.learn.guice.example_2.model.Receipt;

/**
 * Real billing service implementation
 */
@Getter
public class RealBillingService implements BillingService {

    private CreditCardProcessor processor;
    private TransactionLog transactionLog;

    /**
     * @param processor processor
     * @param transactionLog transactionLog
     */

    public RealBillingService(CreditCardProcessor processor, TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
            ChargeResult result = processor.charge(creditCard, order.amount());
            transactionLog.logChargeResult(result);

            return result.wasSuccessful()
                   ? Receipt.forSuccessfulCharge(order.amount())
                   : Receipt.forDeclinedCharge(result.getDeclineMessage());
        } catch (RuntimeException e) {
            transactionLog.logException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }

}
