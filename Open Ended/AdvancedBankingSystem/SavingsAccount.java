/**
 * SavingsAccount with minimum balance requirement
 */
public class SavingsAccount extends BankAccount {
    private static final double MINIMUM_BALANCE = 500.0;
    
    public SavingsAccount(String accountNumber, double balance, String customerId) {
        super(accountNumber, balance, customerId);
    }
    
    @Override
    public boolean deposit(double amount) {
        if (!isActive() || amount <= 0) {
            addTransaction(Transaction.TransactionType.DEPOSIT, amount, Transaction.TransactionStatus.FAILED_INVALID_ACCOUNT);
            return false;
        }
        
        balance += amount;
        addTransaction(Transaction.TransactionType.DEPOSIT, amount, Transaction.TransactionStatus.SUCCESS);
        return true;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (!isActive() || amount <= 0 || (balance - amount) < MINIMUM_BALANCE) {
            addTransaction(Transaction.TransactionType.WITHDRAWAL, amount, Transaction.TransactionStatus.FAILED_INSUFFICIENT_FUNDS);
            return false;
        }
        
        balance -= amount;
        addTransaction(Transaction.TransactionType.WITHDRAWAL, amount, Transaction.TransactionStatus.SUCCESS);
        return true;
    }
    
    @Override
    public String getAccountType() {
        return "Savings Account";
    }
    
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    @Override
    public String toString() {
        return "Savings Account - " + accountNumber + " - Balance: $" + String.format("%.2f", balance) + " - Status: " + status;
    }
}