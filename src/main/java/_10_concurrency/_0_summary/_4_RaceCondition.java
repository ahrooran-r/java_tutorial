package _10_concurrency._0_summary;

class BankAccount implements Runnable {

    private long balance;

    public BankAccount(long balance) {
        this.balance = balance;
        System.out.printf("Current balance in account is %d\n", this.balance);
    }

    public void makeWithdrawal(long amount) {
        if (this.balance >= amount) {
            System.out.printf("%s is about to withdraw %d\n", Thread.currentThread().getName(), amount);
            this.balance -= amount;
            System.out.printf("Current balance in account is %d\n", this.balance);
        } else {
            System.out.printf("Sorry! Not enough balance for %s\n", Thread.currentThread().getName());
        }
    }

    @Override
    public void run() {
        makeWithdrawal(75);
        if (this.balance < 0) System.out.println("Money overdrawn");
    }
}

public class _4_RaceCondition {
    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(100);
        Thread john = new Thread(bankAccount);
        Thread anita = new Thread(bankAccount);
        john.setName("John");
        anita.setName("Anita");

        john.start();
        anita.start();
    }
}
