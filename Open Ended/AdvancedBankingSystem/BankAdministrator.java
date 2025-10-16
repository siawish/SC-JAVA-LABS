import java.util.Scanner;
import java.util.ArrayList;

/**
 * BankAdministrator class - separate administrative interface
 * Provides distinct admin functionalities separate from customer ATM operations
 */
public class BankAdministrator {
    private Bank bank;
    private Scanner scanner;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";
    
    public BankAdministrator(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Start the administrator interface
     */
    public void startAdminInterface() {
        System.out.println("=== Bank Administrator Interface ===");
        
        if (authenticateAdmin()) {
            System.out.println("Administrator login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid administrator credentials.");
        }
    }
    
    /**
     * Authenticate administrator with hardcoded credentials
     */
    private boolean authenticateAdmin() {
        System.out.print("Enter administrator username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter administrator password: ");
        String password = scanner.nextLine().trim();
        
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
    
    /**
     * Main administrator menu
     */
    private void adminMenu() {
        while (true) {
            showAdminMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewAllCustomers();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    createNewAccount();
                    break;
                case 4:
                    unblockCustomer();
                    break;
                case 5:
                    System.out.println("Administrator logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display administrator menu options
     */
    private void showAdminMenu() {
        System.out.println("\n=== Bank Administrator Menu ===");
        System.out.println("1. View All Customers");
        System.out.println("2. View All Accounts");
        System.out.println("3. Create New Account");
        System.out.println("4. Unblock Customer");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * View all registered customers with their account information
     */
    private void viewAllCustomers() {
        System.out.println("\n=== All Registered Customers ===");
        ArrayList<Customer> customers = bank.getAllCustomers();
        
        if (customers.isEmpty()) {
            System.out.println("No customers found in the system.");
            return;
        }
        
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Status: " + (customer.isBlocked() ? "BLOCKED" : "ACTIVE"));
            System.out.println("Associated Account Numbers:");
            
            ArrayList<BankAccount> accounts = customer.getAccounts();
            if (accounts.isEmpty()) {
                System.out.println("  No accounts found");
            } else {
                for (BankAccount account : accounts) {
                    System.out.println("  - " + account.getAccountNumber() + 
                                     " (" + account.getAccountType() + ")");
                }
            }
            System.out.println("----------------------------------------");
        }
    }
    
    /**
     * View all bank accounts with detailed information
     */
    private void viewAllAccounts() {
        System.out.println("\n=== All Bank Accounts ===");
        ArrayList<BankAccount> accounts = bank.getAllAccounts();
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found in the system.");
            return;
        }
        
        System.out.printf("%-12s %-18s %-12s %-12s %-10s%n", 
                         "Account No", "Account Type", "Customer ID", "Balance", "Status");
        System.out.println("================================================================");
        
        for (BankAccount account : accounts) {
            System.out.printf("%-12s %-18s %-12s $%-11.2f %-10s%n",
                            account.getAccountNumber(),
                            account.getAccountType(),
                            account.getCustomerId(),
                            account.getBalance(),
                            account.getStatus());
        }
    }
    
    /**
     * Create a new account for an existing customer
     */
    private void createNewAccount() {
        System.out.println("\n=== Create New Account ===");
        
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Error: Customer with ID '" + customerId + "' not found.");
            return;
        }
        
        System.out.println("Customer found: " + customer.getName());
        System.out.println("Select account type to create:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Choice (1-2): ");
        
        int typeChoice = getIntInput();
        String accountType;
        
        switch (typeChoice) {
            case 1:
                accountType = "savings";
                break;
            case 2:
                accountType = "checking";
                break;
            default:
                System.out.println("Invalid account type selection.");
                return;
        }
        
        System.out.print("Enter initial balance: $");
        double initialBalance = getDoubleInput();
        
        if (initialBalance < 0) {
            System.out.println("Error: Initial balance cannot be negative.");
            return;
        }
        
        // Validate minimum balance for savings account
        if (accountType.equals("savings") && initialBalance < 500.0) {
            System.out.println("Error: Savings account requires minimum initial balance of $500.");
            return;
        }
        
        String accountNumber = bank.createNewAccount(customerId, accountType, initialBalance);
        
        if (accountNumber != null) {
            System.out.println("✓ Account created successfully!");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Type: " + accountType.substring(0, 1).toUpperCase() + 
                             accountType.substring(1) + " Account");
            System.out.println("Initial Balance: $" + String.format("%.2f", initialBalance));
            System.out.println("Customer: " + customer.getName() + " (" + customerId + ")");
        } else {
            System.out.println("Error: Failed to create account. Please try again.");
        }
    }
    
    /**
     * Unblock a customer account that was blocked due to failed PIN attempts
     */
    private void unblockCustomer() {
        System.out.println("\n=== Unblock Customer Account ===");
        
        System.out.print("Enter Customer ID to unblock: ");
        String customerId = scanner.nextLine().trim();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Error: Customer with ID '" + customerId + "' not found.");
            return;
        }
        
        System.out.println("Customer found: " + customer.getName());
        System.out.println("Current status: " + (customer.isBlocked() ? "BLOCKED" : "ACTIVE"));
        
        if (!customer.isBlocked()) {
            System.out.println("Customer account is already active. No action needed.");
            return;
        }
        
        System.out.print("Confirm unblock customer " + customerId + "? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            bank.unblockCustomer(customerId);
            System.out.println("✓ Customer " + customerId + " (" + customer.getName() + 
                             ") has been unblocked successfully.");
            System.out.println("All associated accounts have been reactivated.");
        } else {
            System.out.println("Unblock operation cancelled.");
        }
    }
    
    /**
     * Get integer input with error handling
     */
    private int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Get double input with error handling
     */
    private double getDoubleInput() {
        try {
            String input = scanner.nextLine().trim();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}