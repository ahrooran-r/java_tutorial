package tutorial.learn.core._10_concurrency._0_summary;

// Synchronization -> protects critical (shared mutable) data

class SynchronizedBankAccount implements Runnable {

    private long balance;

    public SynchronizedBankAccount(long balance) {
        this.balance = balance;
        System.out.printf("Current balance in account is %d\n", this.balance);
    }

    // 1. uses the synchronized keyword to lock the critical code which is accessed by different threads
    // or alternatively
    // 2. use synchronized keyword in method declaration like this:
    // public synchronized void makeWithdrawal(long amount) { ... }
    public void makeWithdrawal(long amount) {
        System.out.printf("%s is about to withdraw %d\n", Thread.currentThread().getName(), amount);
        synchronized (this) {
            if (this.balance >= amount) {
                this.balance -= amount;
                System.out.printf("%s has withdrawn %d\nCurrent balance in account is %d\n", Thread.currentThread().getName(), amount, this.balance);
            } else {
                System.out.printf("Sorry! Not enough balance for %s\n", Thread.currentThread().getName());
            }
        }
    }

    @Override
    public void run() {
        makeWithdrawal(75);
        if (this.balance < 0) System.out.println("Money overdrawn");
    }
}

public class _8_Synchronization {
    public static void main(String[] args) {

        SynchronizedBankAccount bankAccount = new SynchronizedBankAccount(100);
        Thread john = new Thread(bankAccount);
        Thread anita = new Thread(bankAccount);
        john.setName("John");
        anita.setName("Anita");

        john.start();
        anita.start();
    }
}
