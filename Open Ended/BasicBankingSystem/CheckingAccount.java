/**
 * CheckingAccount with overdraft limit
 */
public class CheckingAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 1000.0;
    
    public CheckingAccount(String accountNumber, double balance, String customerId) {
        super(accountNumber, balance, customerId);
    }
    
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            addTransaction(Transaction.TransactionType.DEPOSIT, amount, Transaction.TransactionStatus.FAILED_INVALID_ACCOUNT);
            return false;
        }
        
        balance += amount;
        addTransaction(Transaction.TransactionType.DEPOSIT, amount, Transaction.TransactionStatus.SUCCESS);
        return true;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || (balance - amount) < -OVERDRAFT_LIMIT) {
            addTransaction(Transaction.TransactionType.WITHDRAWAL, amount, Transaction.TransactionStatus.FAILED_INSUFFICIENT_FUNDS);
            return false;
        }
        
        balance -= amount;
        addTransaction(Transaction.TransactionType.WITHDRAWAL, amount, Transaction.TransactionStatus.SUCCESS);
        return true;
    }
    
    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
    
    @Override
    public String toString() {
        return "Checking Account - " + accountNumber + " - Balance: $" + String.format("%.2f", balance);
    }
}