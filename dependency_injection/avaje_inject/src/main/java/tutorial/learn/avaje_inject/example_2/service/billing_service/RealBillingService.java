package tutorial.learn.avaje_inject.example_2.service.billing_service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.Getter;
import tutorial.learn.avaje_inject.example_2.annotations.Paypal;
import tutorial.learn.avaje_inject.example_2.model.ChargeResult;
import tutorial.learn.avaje_inject.example_2.model.CreditCard;
import tutorial.learn.avaje_inject.example_2.model.PizzaOrder;
import tutorial.learn.avaje_inject.example_2.model.Receipt;
import tutorial.learn.avaje_inject.example_2.service.credit_card_processor_service.CreditCardProcessor;
import tutorial.learn.avaje_inject.example_2.service.log_service.TransactionLog;

/**
 * Put @Singleton on beans that we want dependency injection on.
 * These are beans that are created ("wired") by dependency injection and put into the scope.
 * They are then available to be injected into other beans.
 * <p>
 * Spring @Component, @Service and @Repository are all singleton scoped.
 * With avaje-inject these would map to @Singleton.
 */
@Singleton
@Getter
public class RealBillingService implements BillingService {

    private final CreditCardProcessor processor;

    private final TransactionLog transactionLog;

    /**
     * Put {@link Inject} on the constructor that should be used for constructor dependency injection.
     * <p>
     * If we want to use field injection put the {@link Inject} on the field.
     * Note that the field must not be private and must not be final for field injection.
     * <p>
     * For method injection annotate the method with {@link Inject}.
     * <pre>
     * {@code
     * @Inject
     * void setGrinder(Grinder grinder) {
     *     this.grinder = grinder;
     * }}
     * </pre>
     * <p>
     * Use Optional to specify optional dependencies. This is like @Autowired(required=false) in spring boot.
     * <pre>
     * {@code
     *
     * private final Optional<Widget> widget;
     *
     * @Inject
     * Pump(Optional < Widget > widget) {
     *     this.widget = widget;
     * }
     *
     * public void pump() {
     *     if (widget.isPresent()) {
     *         widget.get().doStuff();
     *     }
     *     ...
     * }
     * }
     * </pre>
     * OR we can use {@link io.avaje.lang.Nullable} from avaje to show the field.
     * <p>
     * We can inject a java.util.List<T> of beans that implement an interface.
     * <p>
     * Note: If there is only one constructor we don't need this annotation.
     * Since this class has only one constructor we don't need this.
     * <p>
     * There is no special annotation to show something like prototype scope in spring.
     * We can extend {@link jakarta.inject.Provider} class and do so.
     * Look at <a href="https://avaje.io/inject/">documentation</a> webpage and search for Provider
     * <p>
     * To emulate @Configuration, @Bean in spring, we have {@link io.avaje.inject.Factory} and {@link io.avaje.inject.Bean}
     * in avaje. Check the documentation.
     */
    @Inject
    public RealBillingService(@Paypal CreditCardProcessor processor, TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    @Override
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
