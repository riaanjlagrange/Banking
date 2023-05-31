import java.util.Objects;

public class Account {

    private final int accountNumber;
    private double balance;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(int accountNumber) {
        this(accountNumber, 0.0);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getMoney(double money) {
        if (this.balance - money >= 0) {
            this.balance -= money;
            return money;
        }
        double totalGot = this.balance;
        this.balance -= this.balance;
        return totalGot;
    }


    public double getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber && Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance);
    }

    @Override
    public String toString() {
        return "Account number: " + this.accountNumber + ", Balance: " + this.balance;
    }


}
