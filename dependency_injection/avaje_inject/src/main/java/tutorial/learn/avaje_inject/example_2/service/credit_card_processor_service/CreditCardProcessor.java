package tutorial.learn.avaje_inject.example_2.service.credit_card_processor_service;


import tutorial.learn.avaje_inject.example_2.model.ChargeResult;
import tutorial.learn.avaje_inject.example_2.model.CreditCard;

public interface CreditCardProcessor {
    ChargeResult charge(CreditCard creditCard, Double amount);
}
