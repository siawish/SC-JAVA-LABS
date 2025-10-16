import java.util.HashMap;


public class Bank {
    private HashMap<String, Customer> customers;
    private HashMap<String, BankAccount> accounts;
    
    public Bank() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Create sample customers
        Customer customer1 = new Customer("C001", "Malik Muhammad Sisaiwsh", "1234");
        Customer customer2 = new Customer("C002", "Malik Muhammad Sisaiwsh", "5678");
        Customer customer3 = new Customer("C003", "Malik Muhammad Sisaiwsh", "9999");
        
        // Create accounts
        SavingsAccount savings1 = new SavingsAccount("SA001", 2000.0, "C001");
        CheckingAccount checking1 = new CheckingAccount("CA001", 1500.0, "C001");
        SavingsAccount savings2 = new SavingsAccount("SA002", 3000.0, "C002");
        CheckingAccount checking2 = new CheckingAccount("CA002", 800.0, "C002");
        SavingsAccount savings3 = new SavingsAccount("SA003", 5000.0, "C003");
        
        // Add accounts to customers
        customer1.addAccount(savings1);
        customer1.addAccount(checking1);
        customer2.addAccount(savings2);
        customer2.addAccount(checking2);
        customer3.addAccount(savings3);
        
        // Store in HashMaps
        customers.put("C001", customer1);
        customers.put("C002", customer2);
        customers.put("C003", customer3);
        
        accounts.put("SA001", savings1);
        accounts.put("CA001", checking1);
        accounts.put("SA002", savings2);
        accounts.put("CA002", checking2);
        accounts.put("SA003", savings3);
    }
    
    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }
    
    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }
    
    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }
}