package tutorial.learn.avaje_inject.example_2.service.credit_card_processor_service;


import jakarta.inject.Singleton;
import tutorial.learn.avaje_inject.example_2.annotations.Paypal;
import tutorial.learn.avaje_inject.example_2.model.ChargeResult;
import tutorial.learn.avaje_inject.example_2.model.CreditCard;

@Singleton
@Paypal
public class PaypalCreditCardProcessor implements CreditCardProcessor {

    @Override
    public ChargeResult charge(CreditCard creditCard, Double amount) {
        // Paypal debits the amount
        return new ChargeResult(true, amount);
    }
}
