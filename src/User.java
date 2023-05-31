import java.util.HashMap;
import java.util.Objects;

public class User {
    private String name;
    private int id;
    private String address;
    private HashMap<Integer, Account> accounts;

    public User(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.accounts = new HashMap<>();
    }

    public User(String name, int id) {
        this(name, "unknown", id);
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, Account> getAccounts() {
        return this.accounts;
    }

    public boolean addAccount(int accountNumber, Account account) {
        if (this.accounts.isEmpty()) {
            this.accounts.put(accountNumber, account);
            return true;
        }
        if (!this.accounts.containsKey(accountNumber)) {
            this.accounts.put(accountNumber, account);
            return true;
        }
        return false;
    }

    public boolean closeAccount(int accountNumber) {
        if (this.accounts.containsKey(accountNumber)) {
            this.accounts.remove(accountNumber);
            return true;
        }
        else return false;
    }

    public void printAccounts() {
        this.accounts.values().forEach(System.out::println);
    }

    public void deposit(double amount, int accountNumber) {
        this.accounts.get(accountNumber).deposit(amount);
    }

    public double getTotalMoney() {
        return this.accounts.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    public double getMoneyFromAllAccounts(double money) {
        double moneyNeeded = money;
        if (this.getTotalMoney() >= money) {
            for (Account account : this.accounts.values()) {
                if (moneyNeeded == 0) {
                    break;
                }
                moneyNeeded -= account.getMoney(moneyNeeded);
            }
            return money;
        } else {
            for (Account account : this.accounts.values()) {
                moneyNeeded -= account.getMoney(money);
            }
            return money - moneyNeeded;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(accounts, user.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, address, accounts);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.id + "): " + this.accounts.size() + " accounts";
    }
}
