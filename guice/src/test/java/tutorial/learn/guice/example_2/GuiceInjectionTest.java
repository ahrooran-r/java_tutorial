package tutorial.learn.guice.example_2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.junit.jupiter.api.Test;
import tutorial.learn.guice.example_2.annotations.Paypal;
import tutorial.learn.guice.example_2.modules.BillingModule;
import tutorial.learn.guice.example_2.service.billing_service.BillingService;
import tutorial.learn.guice.example_2.service.billing_service.RealBillingService;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.BankCreditCardProcessor;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.CreditCardProcessor;
import tutorial.learn.guice.example_2.service.credit_card_processor_service.PaypalCreditCardProcessor;
import tutorial.learn.guice.example_2.service.log_service.InMemoryTransactionLog;
import tutorial.learn.guice.example_2.service.log_service.TransactionLog;


public class GuiceInjectionTest {

    private static final Injector injector = Guice.createInjector(new BillingModule());

    @Test
    public void TestBillingServiceInstanceOfRealBillingService() {
        BillingService billingService = injector.getInstance(BillingService.class);
        assertTrue(billingService instanceof RealBillingService);
    }

    @Test
    public void TestCreditCardProcessorInstanceOfPaypalCCProcessor() {
        CreditCardProcessor creditCardProcessor = injector.getInstance(Key.get(CreditCardProcessor.class, Paypal.class));
        assertTrue(creditCardProcessor instanceof PaypalCreditCardProcessor);
    }

    @Test
    public void TestCreditCardProcessorInstanceOfBankCCProcessor() {
        CreditCardProcessor creditCardProcessor = injector.getInstance(Key.get(CreditCardProcessor.class, Names.named("Bank")));
        assertTrue(creditCardProcessor instanceof BankCreditCardProcessor);
    }

    @Test
    public void TestBillingServiceHasBankCardProcessor() {
        RealBillingService billingService = (RealBillingService) injector.getInstance(BillingService.class);
        assertTrue(billingService.getProcessor() instanceof BankCreditCardProcessor);
    }

    @Test
    public void TestTransactionLogInstanceOfInMemory() {
        TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
        assertTrue(transactionLog instanceof InMemoryTransactionLog);
    }
}
