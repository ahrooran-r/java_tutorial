package tutorial.learn.guice.example_2;

import com.google.inject.Guice;
import com.google.inject.Injector;
import tutorial.learn.guice.example_2.model.CreditCard;
import tutorial.learn.guice.example_2.model.PizzaOrder;
import tutorial.learn.guice.example_2.model.Receipt;
import tutorial.learn.guice.example_2.modules.BillingModule;
import tutorial.learn.guice.example_2.service.billing_service.BillingService;

/**
 * Code adapted from: git clone https://github.com/sankalpbhatia/FreshGuice.git
 */
public class Application {

    public static void main(String[] args) {
        /*
         * Guice.createInjector() takes your Modules, and returns a new Injector
         * instance. Most applications will call this method exactly once, in their
         * main() method.
         */
        Injector injector = Guice.createInjector(new BillingModule());

        /*
         * Now that we've got the injector, we can build objects.
         */
        BillingService billingService = injector.getInstance(BillingService.class);

        PizzaOrder pizzaOrder = new PizzaOrder("Very-Veggie", 100d);

        CreditCard creditCard = new CreditCard("12345");

        Receipt receipt = billingService.chargeOrder(pizzaOrder, creditCard);

        System.out.println(receipt.receiptMessage());
    }
}
