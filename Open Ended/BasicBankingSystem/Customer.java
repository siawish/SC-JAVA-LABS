import java.util.ArrayList;

/**
 * Customer class encapsulating customer information and accounts
 */
public class Customer {
    private String customerId;
    private String name;
    private String pin;
    private ArrayList<BankAccount> accounts;
    private int failedLoginAttempts;
    private boolean isBlocked;
    
    public Customer(String customerId, String name, String pin) {
        this.customerId = customerId;
        this.name = name;
        this.pin = pin;
        this.accounts = new ArrayList<>();
        this.failedLoginAttempts = 0;
        this.isBlocked = false;
    }
    
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
    
    public boolean authenticate(String inputPin) {
        if (isBlocked) {
            return false;
        }
        
        if (pin.equals(inputPin)) {
            failedLoginAttempts = 0;
            return true;
        } else {
            failedLoginAttempts++;
            if (failedLoginAttempts >= 3) {
                isBlocked = true;
            }
            return false;
        }
    }
    
    public void unblock() {
        isBlocked = false;
        failedLoginAttempts = 0;
    }
    
    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public ArrayList<BankAccount> getAccounts() { return accounts; }
    public boolean isBlocked() { return isBlocked; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
}