import java.util.Scanner;
import java.util.ArrayList;

/**
 * ATM class providing user interface for banking operations
 */
public class ATM {
    private Bank bank;
    private Scanner scanner;
    private Customer currentCustomer;
    
    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=== Welcome to Advanced Banking System ===");
        
        while (true) {
            showMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    customerLogin();
                    break;
                case 2:
                    System.out.println("Thank you for using our banking system!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Customer Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void customerLogin() {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        if (customer.isBlocked()) {
            System.out.println("Account is blocked due to multiple failed login attempts!");
            return;
        }
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        
        if (customer.authenticate(pin)) {
            currentCustomer = customer;
            System.out.println("Login successful! Welcome, " + customer.getName());
            customerMenu();
        } else {
            if (customer.isBlocked()) {
                System.out.println("Account blocked after 3 failed attempts!");
            } else {
                System.out.println("Invalid PIN. Attempts remaining: " + (3 - customer.getFailedLoginAttempts()));
            }
        }
    }
    
    private void customerMenu() {
        while (true) {
            showCustomerMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    selectAccount();
                    break;
                case 2:
                    currentCustomer = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void showCustomerMenu() {
        System.out.println("\n=== Customer Menu ===");
        System.out.println("1. Select Account for Operations");
        System.out.println("2. Logout");
        System.out.print("Enter your choice: ");
    }
    
    private void selectAccount() {
        ArrayList<BankAccount> accounts = currentCustomer.getAccounts();
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("\n=== Your Accounts ===");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
        
        System.out.print("Select account (1-" + accounts.size() + "): ");
        int choice = getIntInput();
        
        if (choice >= 1 && choice <= accounts.size()) {
            BankAccount selectedAccount = accounts.get(choice - 1);
            accountOperations(selectedAccount);
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    private void accountOperations(BankAccount account) {
        while (true) {
            showAccountMenu(account);
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    checkBalance(account);
                    break;
                case 2:
                    depositFunds(account);
                    break;
                case 3:
                    withdrawFunds(account);
                    break;
                case 4:
                    viewTransactionHistory(account);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void showAccountMenu(BankAccount account) {
        System.out.println("\n=== Account Operations ===");
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. View Transaction History");
        System.out.println("5. Back to Account Selection");
        System.out.print("Enter your choice: ");
    }
    
    private void checkBalance(BankAccount account) {
        System.out.println("\n=== Balance Inquiry ===");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        printReceipt("BALANCE_INQUIRY", 0, account);
    }
    
    private void depositFunds(BankAccount account) {
        System.out.print("Enter deposit amount: $");
        double amount = getDoubleInput();
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }
        
        if (account.deposit(amount)) {
            System.out.println("Deposit successful!");
            System.out.println("New Balance: $" + String.format("%.2f", account.getBalance()));
            printReceipt("DEPOSIT", amount, account);
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
    }
    
    private void withdrawFunds(BankAccount account) {
        System.out.print("Enter withdrawal amount: $");
        double amount = getDoubleInput();
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }
        
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
            System.out.println("New Balance: $" + String.format("%.2f", account.getBalance()));
            printReceipt("WITHDRAWAL", amount, account);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or account restrictions.");
            if (account instanceof SavingsAccount) {
                System.out.println("Note: Savings accounts require minimum balance of $" + 
                                 ((SavingsAccount) account).getMinimumBalance());
            } else if (account instanceof CheckingAccount) {
                System.out.println("Note: Checking accounts have overdraft limit of $" + 
                                 ((CheckingAccount) account).getOverdraftLimit());
            }
        }
    }
    
    private void viewTransactionHistory(BankAccount account) {
        ArrayList<Transaction> history = account.getTransactionHistory();
        
        System.out.println("\n=== Transaction History ===");
        System.out.println("Account: " + account.getAccountNumber());
        
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : history) {
                System.out.println(transaction);
            }
        }
    }
    
    private void printReceipt(String operation, double amount, BankAccount account) {
        System.out.println("\n=== RECEIPT ===");
        System.out.println("Operation: " + operation);
        if (amount > 0) {
            System.out.println("Amount: $" + String.format("%.2f", amount));
        }
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("Thank you for banking with us!");
        System.out.println("================");
    }
    
    private int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private double getDoubleInput() {
        try {
            String input = scanner.nextLine().trim();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}