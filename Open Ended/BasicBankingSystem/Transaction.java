import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class for recording banking operations
 */
public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }
    
    public enum TransactionStatus {
        SUCCESS, FAILED_INSUFFICIENT_FUNDS, FAILED_INVALID_ACCOUNT
    }
    
    private static int nextTransactionId = 1000;
    
    private int transactionId;
    private TransactionType type;
    private double amount;
    private LocalDateTime dateTime;
    private String affectedAccount;
    private TransactionStatus status;
    
    public Transaction(TransactionType type, double amount, String affectedAccount, TransactionStatus status) {
        this.transactionId = nextTransactionId++;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.affectedAccount = affectedAccount;
        this.status = status;
    }
    
    // Getters
    public int getTransactionId() { return transactionId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getAffectedAccount() { return affectedAccount; }
    public TransactionStatus getStatus() { return status; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("ID: %d | %s | %s | $%.2f | Account: %s | %s",
                transactionId, dateTime.format(formatter), type, amount, affectedAccount, status);
    }
}