import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private UserManager manager;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.manager = new UserManager();
    }

    public void start() {
        System.out.println("""
                            
                            Welcome to the bank!
                            
                            """);
        while (true) {
            System.out.println("""
                                
                                What would you like to do?
                                1. Get information
                                2. Manage users and accounts
                                3. Deposit or transfer money
                                
                                """);
            System.out.print(":");
            int input = Integer.parseInt(this.scanner.next());

            switch (input) {
                case 1:
                    this.getStats();
                    break;
                case 2:
                    this.userHandling();
                    break;
                case 3:
                    this.moneyHandling();
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }

    }

    public void getStats() {
        System.out.println("""
                        
                        1. View all users in the bank
                        2. Print user balance
                        3. Print total balance in bank
                        4. Back
                        
                        """);
        System.out.print(":");
        int input = Integer.parseInt(this.scanner.next());
        System.out.println();

        switch (input) {
            case 1:
                this.manager.printAllUsers();
                break;
            case 2:
                System.out.println("Who's balance do you want to check?");
                System.out.print(":");
                String balanceInput = this.scanner.next();
                this.manager.printUserTotal(balanceInput);
                break;
            case 3:
                System.out.println("Total in the bank: " + this.manager.getAllTotals());
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid selection");
        }
    }

    public void userHandling() {
        System.out.println("""
                        
                        1. Add new user
                        2. Add new account
                        3. Close account
                        4. Remove user
                        5. Back
                        
                        """);
        System.out.print(":");
        int input = Integer.parseInt(this.scanner.next());
        System.out.println();

        String name = "";
        int accountNumber = 0;
        String decision = "";
        switch (input) {
            case 1:
                System.out.print("Name: ");
                name = this.scanner.next();
                System.out.print("ID: ");
                int id = Integer.parseInt(this.scanner.next());

                if (manager.addUser(new User(name, id))) {
                    System.out.println("User added successfully");
                } else System.out.println("User was not added");
                System.out.println();
                System.out.println("You need to create an account also");
                System.out.print("Account number: ");
                accountNumber = Integer.parseInt(this.scanner.next());
                System.out.println("Would you like to deposit money into this account? (y/n)");
                System.out.print(":");
                decision = this.scanner.next();
                if (decision.equals("y")) {
                    System.out.print("Amount: ");
                    double amount = Double.parseDouble(this.scanner.next());
                    if (manager.addAccount(name, accountNumber, new Account(accountNumber, amount))) {
                        System.out.println("Account added successfully");
                    } else System.out.println("Account was not added");
                } else {
                    if (manager.addAccount(name, accountNumber, new Account(accountNumber))) {
                        System.out.println("Account added successfully");
                    } else System.out.println("Account was not added");
                }
                break;
            case 2:
                System.out.print("Name of user: ");
                name = this.scanner.next();
                System.out.print("Account number: ");
                accountNumber = Integer.parseInt(this.scanner.next());
                System.out.println("Would you like to deposit money into this account? (y/n)");
                System.out.print(":");
                decision = this.scanner.next();
                if (decision.equals("y")) {
                    System.out.print("Amount: ");
                    double amount = Double.parseDouble(this.scanner.next());
                    if (manager.addAccount(name, accountNumber, new Account(accountNumber, amount))) {
                        System.out.println("Account added successfully");
                    } else System.out.println("Account was not added");
                } else {
                    if (manager.addAccount(name, accountNumber, new Account(accountNumber))) {
                        System.out.println("Account added successfully");
                    } else System.out.println("Account was not added");
                }
                break;
            case 3:
                System.out.print("Name of account holder: ");
                name = this.scanner.next();
                System.out.print("Account number: ");
                accountNumber = Integer.parseInt(this.scanner.next());

                if (manager.removeAccount(name, accountNumber)) {
                    System.out.println("Account removed");
                } else System.out.println("Account was not removed");
                break;
            case 4:
                System.out.print("Name of user: ");
                name = this.scanner.next();

                if (manager.removeUser(name)) {
                    System.out.println("User removed");
                } else System.out.println("User was not removed");
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid selection");
        }
    }

    public void moneyHandling() {
        System.out.println("""
                    
                    1. Transfer money
                    2. Deposit money
                    3. Back
                    
                    """);
        System.out.print(":");
        int input = Integer.parseInt(this.scanner.next());
        System.out.println();
        String name = "";
        int accountNumber = 0;
        double amount = 0;

        switch (input) {
            case 1:
                System.out.print("Name of user to transfer from: ");
                name = this.scanner.next();
                System.out.print("Name of user to transfer to: ");
                String nameTo = this.scanner.next();
                System.out.print("User to transfer to account number: ");
                accountNumber = Integer.parseInt(this.scanner.next());
                System.out.print("Amount: ");
                amount = Double.parseDouble(this.scanner.next());
                if (this.manager.transferTo(name, nameTo, accountNumber, amount)) {
                    System.out.println("Amount successfully transferred");
                } else System.out.println("Amount could not be transferred");
                break;
            case 2:
                System.out.print("Name: ");
                name = this.scanner.next();
                System.out.print("Account number: ");
                accountNumber = Integer.parseInt(this.scanner.next());
                System.out.print("Amount: ");
                amount = Double.parseDouble(this.scanner.next());
                if (this.manager.depositMoney(name, accountNumber, amount)) {
                    System.out.println("Amount successfully deposited");
                } else System.out.println("Amount could not be deposited");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid selection");
            }
    }

}
