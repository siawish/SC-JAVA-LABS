import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class for recording banking operations including transfers
 */
public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
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
    private String otherAccount; // For transfers
    private TransactionStatus status;
    
    public Transaction(TransactionType type, double amount, String affectedAccount, TransactionStatus status) {
        this(type, amount, affectedAccount, null, status);
    }
    
    public Transaction(TransactionType type, double amount, String affectedAccount, String otherAccount, TransactionStatus status) {
        this.transactionId = nextTransactionId++;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.affectedAccount = affectedAccount;
        this.otherAccount = otherAccount;
        this.status = status;
    }
    
    // Getters
    public int getTransactionId() { return transactionId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getAffectedAccount() { return affectedAccount; }
    public String getOtherAccount() { return otherAccount; }
    public TransactionStatus getStatus() { return status; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String result = String.format("ID: %d | %s | %s | $%.2f | Account: %s | %s",
                transactionId, dateTime.format(formatter), type, amount, affectedAccount, status);
        
        if (otherAccount != null) {
            result += " | Other Account: " + otherAccount;
        }
        
        return result;
    }
}