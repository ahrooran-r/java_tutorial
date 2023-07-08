package tutorial.learn.guice.example_2.service.credit_card_processor_service;

import tutorial.learn.guice.example_2.model.ChargeResult;
import tutorial.learn.guice.example_2.model.CreditCard;

public class BankCreditCardProcessor implements CreditCardProcessor {
    @Override
    public ChargeResult charge(CreditCard creditCard, Double amount) {
        // Bank debits the amount
        return new ChargeResult(true , amount);
    }

}
