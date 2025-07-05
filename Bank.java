import java.util.ArrayList;

class BankAccount {
    private int accountNumber;
    private String name;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<>();

    public BankAccount(int accountNumber, String name, double initialBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = initialBalance;
        transactions.add("Account created with balance ₹" + initialBalance);
    }

    public int getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited ₹" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew ₹" + amount);
            return true;
        }
        transactions.add("Failed Withdrawal of ₹" + amount + " (Insufficient Balance)");
        return false;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Name: " + name + ", Balance: ₹" + balance;
    }
}

public class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void openAccount(int accNo, String name, double balance) {
        accounts.add(new BankAccount(accNo, name, balance));
    }

    public boolean closeAccount(int accNo) {
        return accounts.removeIf(acc -> acc.getAccountNumber() == accNo);
    }

    public boolean deposit(int accNo, double amount) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                acc.deposit(amount);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(int accNo, double amount) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc.withdraw(amount);
            }
        }
        return false;
    }

    public BankAccount getAccountByNumber(int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo)
                return acc;
        }
        return null;
    }

    public ArrayList<BankAccount> getAllAccounts() {
        return accounts;
    }
}
