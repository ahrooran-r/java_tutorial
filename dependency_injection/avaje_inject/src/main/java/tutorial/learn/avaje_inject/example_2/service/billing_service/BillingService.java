package tutorial.learn.avaje_inject.example_2.service.billing_service;

import tutorial.learn.avaje_inject.example_2.model.CreditCard;
import tutorial.learn.avaje_inject.example_2.model.PizzaOrder;
import tutorial.learn.avaje_inject.example_2.model.Receipt;

public interface BillingService {
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
