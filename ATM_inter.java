
package ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class User {
    String userID;
    String userPIN;
    double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }
}

class ATM {
    User currentUser;

    public boolean authenticateUser(String userID, String userPIN) {
        // Simulated authentication (replace with actual authentication logic)
        return userID.equals(currentUser.userID) && userPIN.equals(currentUser.userPIN);
    }

    public double checkBalance() {
        return currentUser.accountBalance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= currentUser.accountBalance) {
            currentUser.accountBalance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawal successful. Remaining balance: " + currentUser.accountBalance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount or insufficient funds");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            currentUser.accountBalance += amount;
            JOptionPane.showMessageDialog(null, "Deposit successful. New balance: " + currentUser.accountBalance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount");
        }
    }
}

public class ATM_inter extends JFrame {
    private JTextField userIDField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton balanceButton;
    private JButton withdrawButton;
    private JButton depositButton;

    private ATM atm;

    public ATM_inter() {
        atm = new ATM();

        // GUI components setup
        // ... (initialize and set up JFrame, JPanel, layout, etc.)

        userIDField = new JTextField(10);
        pinField = new JPasswordField(10);
        loginButton = new JButton("Login");
        balanceButton = new JButton("Check Balance");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");

        // Add action listeners to buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        // ... (add components to the JFrame, set layout, etc.)
    }

    private void login() {
        String userID = userIDField.getText();
        String pin = new String(pinField.getPassword());

        if (atm.authenticateUser(userID, pin)) {
            JOptionPane.showMessageDialog(null, "Login successful");
            // Set the current user for further transactions
            atm.currentUser = new User(userID, pin, 1000.0); // Example initial balance
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Current balance: $" + atm.checkBalance());
    }

    private void withdraw() {
        String amountString = JOptionPane.showInputDialog("Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountString);
            atm.withdraw(amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
    }

    private void deposit() {
        String amountString = JOptionPane.showInputDialog("Enter deposit amount:");
        try {
            double amount = Double.parseDouble(amountString);
            atm.deposit(amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATM_inter().setVisible(true);
            }
        });
    }
}
