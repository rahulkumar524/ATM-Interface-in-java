package ATM;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double newBalance) {
        this.accountBalance = newBalance;
    }
}

class ATM {
    private Map<String, User> userDatabase;
    private User currentUser;

    public ATM() {
        this.userDatabase = new HashMap<>();
        // Sample user for testing
        userDatabase.put("123456", new User("123456", "7890", 1000.0));
    }

    public void startATM() {
        System.out.println("Welcome to the ATM Interface!");

        // Simulate user login
        if (authenticateUser()) {
            displayMenu();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    private boolean authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        if (userDatabase.containsKey(userID) && userDatabase.get(userID).getUserPIN().equals(userPIN)) {
            currentUser = userDatabase.get(userID);
            System.out.println("Authentication successful. Welcome, " + userID + "!");
            return true;
        } else {
            System.out.println("Invalid credentials. Authentication failed.");
            return false;
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + currentUser.getAccountBalance());
    }

    private void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            double newBalance = currentUser.getAccountBalance() - amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("Withdrawal successful. Remaining Balance: $" + newBalance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    private void depositMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            double newBalance = currentUser.getAccountBalance() + amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("Deposit successful. New Balance: $" + newBalance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }
}

public class ATM_interface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.startATM();
    }
}