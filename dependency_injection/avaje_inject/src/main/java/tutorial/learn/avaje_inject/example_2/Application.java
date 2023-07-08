package tutorial.learn.avaje_inject.example_2;

import io.avaje.inject.BeanScope;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Singleton;
import tutorial.learn.avaje_inject.example_2.model.CreditCard;
import tutorial.learn.avaje_inject.example_2.model.PizzaOrder;
import tutorial.learn.avaje_inject.example_2.model.Receipt;
import tutorial.learn.avaje_inject.example_2.service.billing_service.BillingService;

/**
 * An avaje inject implementation of FreshJuice.
 * <p>
 * <a href="https://avaje.io/inject/">tutorial here</a>
 */
@Singleton
public class Application {

    private final BillingService billingService;

    // @Inject -> no need
    public Application(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostConstruct
    public void init() {
        PizzaOrder pizzaOrder = new PizzaOrder("Very-Veggie", 100d);
        CreditCard creditCard = new CreditCard("12345");

        Receipt receipt = billingService.chargeOrder(pizzaOrder, creditCard);
        System.out.println(receipt.receiptMessage());
    }

    public static void main(String[] args) {
        try (BeanScope beanScope = BeanScope.builder().build()) {
            Application application = beanScope.get(Application.class);
        }
    }
}
