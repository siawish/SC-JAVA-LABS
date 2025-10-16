import java.util.ArrayList;

/**
 * Abstract BankAccount class defining common properties and behaviors
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected String customerId;
    protected ArrayList<Transaction> transactionHistory;
    
    public BankAccount(String accountNumber, double balance, String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
        this.transactionHistory = new ArrayList<>();
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);
    
    // Common methods
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    
    protected void addTransaction(Transaction.TransactionType type, double amount, Transaction.TransactionStatus status) {
        Transaction transaction = new Transaction(type, amount, accountNumber, status);
        transactionHistory.add(transaction);
    }
}