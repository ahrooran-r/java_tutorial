package tutorial.learn.guice.example_2.service.billing_service;

import com.google.inject.ImplementedBy;
import tutorial.learn.guice.example_2.model.CreditCard;
import tutorial.learn.guice.example_2.model.PizzaOrder;
import tutorial.learn.guice.example_2.model.Receipt;

/**
 * Billing Service.
 */
@ImplementedBy(RealBillingService.class)
public interface BillingService {

    /**
     *
     * @param order order
     * @param creditCard cc
     * @return Receipt
     */
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
