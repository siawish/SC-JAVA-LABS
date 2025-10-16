import java.util.ArrayList;

/**
 * Abstract BankAccount class with transfer support
 */
public abstract class BankAccount {
    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }
    
    protected String accountNumber;
    protected double balance;
    protected String customerId;
    protected ArrayList<Transaction> transactionHistory;
    protected AccountStatus status;
    
    public BankAccount(String accountNumber, double balance, String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
        this.transactionHistory = new ArrayList<>();
        this.status = AccountStatus.ACTIVE;
    }
    
    // Abstract methods
    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract String getAccountType();
    
    // Common methods
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public String getCustomerId() { return customerId; }
    public ArrayList<Transaction> getTransactionHistory() { return transactionHistory; }
    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }
    
    protected void addTransaction(Transaction.TransactionType type, double amount, Transaction.TransactionStatus status) {
        Transaction transaction = new Transaction(type, amount, accountNumber, status);
        transactionHistory.add(transaction);
    }
    
    protected void addTransferTransaction(Transaction.TransactionType type, double amount, String otherAccount, Transaction.TransactionStatus status) {
        Transaction transaction = new Transaction(type, amount, accountNumber, otherAccount, status);
        transactionHistory.add(transaction);
    }
    
    public boolean isActive() {
        return status == AccountStatus.ACTIVE;
    }
}