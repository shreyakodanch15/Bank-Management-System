import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankGUI extends JFrame {
    private Bank bank = new Bank();

    public BankGUI() {
        setTitle("Bank Management System");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        JButton openBtn = new JButton("Open Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton closeBtn = new JButton("Close Account");
        JButton showBtn = new JButton("Show All Accounts");
        JButton historyBtn = new JButton("Show Transaction History");

        add(openBtn);
        add(depositBtn);
        add(withdrawBtn);
        add(closeBtn);
        add(showBtn);
        add(historyBtn);

        openBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(JOptionPane.showInputDialog("Enter Account No:"));
                String name = JOptionPane.showInputDialog("Enter Name:");
                double bal = Double.parseDouble(JOptionPane.showInputDialog("Initial Balance:"));
                bank.openAccount(acc, name, bal);
                JOptionPane.showMessageDialog(this, "Account opened successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        depositBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(JOptionPane.showInputDialog("Enter Account No:"));
                double amt = Double.parseDouble(JOptionPane.showInputDialog("Amount to Deposit:"));
                boolean success = bank.deposit(acc, amt);
                JOptionPane.showMessageDialog(this, success ? "Deposited." : "Account not found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(JOptionPane.showInputDialog("Enter Account No:"));
                double amt = Double.parseDouble(JOptionPane.showInputDialog("Amount to Withdraw:"));
                boolean success = bank.withdraw(acc, amt);
                JOptionPane.showMessageDialog(this, success ? "Withdrawn." : "Insufficient balance or not found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        closeBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(JOptionPane.showInputDialog("Enter Account No to close:"));
                boolean closed = bank.closeAccount(acc);
                JOptionPane.showMessageDialog(this, closed ? "Account closed." : "Account not found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        showBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (var acc : bank.getAllAccounts())
                sb.append(acc.toString()).append("\n");
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "All Accounts", JOptionPane.INFORMATION_MESSAGE);
        });

        historyBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(JOptionPane.showInputDialog("Enter Account No:"));
                var account = bank.getAccountByNumber(acc);
                if (account != null) {
                    StringBuilder sb = new StringBuilder();
                    for (String txn : account.getTransactions())
                        sb.append(txn).append("\n");
                    JTextArea area = new JTextArea(sb.toString());
                    area.setEditable(false);
                    JOptionPane.showMessageDialog(this, new JScrollPane(area), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BankGUI();
    }
}
