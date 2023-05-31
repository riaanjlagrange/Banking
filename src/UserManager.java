import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public boolean addUser(User user) {
        if (!this.users.containsKey(user.getName())) {
            this.users.put(user.getName(), user);
            return true;
        }
        return false;
    }

    public boolean addAccount(String user, int accountNumber, Account account) {
        if (!this.users.containsKey(user)) {
            return false;
        }
        return this.users.get(user).addAccount(accountNumber, account);
    }

    public boolean removeAccount(String user, int accountNumber) {
        if (!this.users.containsKey(user)) {
            return false;
        }
        return this.users.get(user).closeAccount(accountNumber);
    }

    public boolean removeUser(String user) {
        if (this.users.containsKey(user)) {
            this.users.remove(user);
            return true;
        }
        return false;
    }

    public boolean depositMoney(String user, int accountNumber, double amount) {
        if (this.users.containsKey(user)) {
            if (this.users.get(user).getAccounts().containsKey(accountNumber)) {
                this.users.get(user).deposit(amount, accountNumber);
                return true;
            }
        }
        return false;
    }

    public boolean transferTo(String from, String to, int accountNumber, double amount) {
        if (this.users.containsKey(from) && this.users.containsKey(to)) {
            double amountGot = this.users.get(from).getMoneyFromAllAccounts(amount);
            this.users.get(to).deposit(amountGot, accountNumber);
            return true;
        }
        return false;
    }

    public double getAllTotals() {
        if (this.users.isEmpty()) {
            return 0;
        }
        return this.users.values().stream()
                .mapToDouble(User::getTotalMoney)
                .sum();
    }

    public void printUserTotal(String user) {
        if (this.users.isEmpty()) {
            System.out.println("No users in the bank");
            return;
        }
        if (this.users.containsKey(user)) {
            System.out.println(this.users.get(user).getTotalMoney());
        } else {
            System.out.println("Cannot find user");
        }
    }

    public void printAllUsers() {
        if (this.users.isEmpty()) {
            System.out.println("No users in the bank");
            return;
        }
        this.users.values().forEach(System.out::println);
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (User user : this.users.values()) {
            string.append(user).append("\n");
        }
        return string.toString();
    }
}
