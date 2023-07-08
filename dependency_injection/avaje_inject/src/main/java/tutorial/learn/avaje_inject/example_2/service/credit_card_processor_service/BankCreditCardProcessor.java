package tutorial.learn.avaje_inject.example_2.service.credit_card_processor_service;


import jakarta.inject.Named;
import jakarta.inject.Singleton;
import tutorial.learn.avaje_inject.example_2.model.ChargeResult;
import tutorial.learn.avaje_inject.example_2.model.CreditCard;

/**
 * {@link Named} is case-insensitive
 */
@Named("bankCredit")
@Singleton
public class BankCreditCardProcessor implements CreditCardProcessor {
    @Override
    public ChargeResult charge(CreditCard creditCard, Double amount) {
        // Bank debits the amount
        return new ChargeResult(true , amount);
    }

}
