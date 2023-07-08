package tutorial.learn.guice.example_2.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tutorial.learn.guice.example_2.annotations.Paypal;
import tutorial.learn.guice.example_2.service.billing_service.BillingService;
import tutorial.learn.guice.example_2.service.billing_service.RealBillingService;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.BankCreditCardProcessor;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.CreditCardProcessor;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.PaypalCreditCardProcessor;
import tutorial.learn.guice.example_2.service.log_service.InMemoryTransactionLog;
import tutorial.learn.guice.example_2.service.log_service.TransactionLog;


public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreditCardProcessor.class).annotatedWith(Paypal.class).to(PaypalCreditCardProcessor.class);
        bind(CreditCardProcessor.class).annotatedWith(Names.named("Bank")).to(BankCreditCardProcessor.class);
        bind(CreditCardProcessor.class).to(BankCreditCardProcessor.class);
        bind(TransactionLog.class).to(InMemoryTransactionLog.class);
    }

    //.. or here!

    /**
     * This is like @Bean in spring boot.
     */
    @Provides
    @Singleton
    public BillingService getBillingService(CreditCardProcessor ccp, TransactionLog transactionLog) {
        return new RealBillingService(ccp, transactionLog);
    }
}
