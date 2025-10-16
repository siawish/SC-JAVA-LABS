import java.util.Scanner;
import java.util.ArrayList;

/**
 * ATM class with transfer and admin functionality
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
                    adminLogin();
                    break;
                case 3:
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
        System.out.println("2. Administrator Login");
        System.out.println("3. Exit");
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
                    interAccountTransfer();
                    break;
                case 3:
                    crossCustomerTransfer();
                    break;
                case 4:
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
        System.out.println("2. Transfer Between My Accounts");
        System.out.println("3. Transfer to Another Customer");
        System.out.println("4. Logout");
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
        System.out.println("Account: " + account.getAccountNumber() + " (" + account.getAccountType() + ")");
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. View Transaction History");
        System.out.println("5. Back to Customer Menu");
        System.out.print("Enter your choice: ");
    }
    
    private void interAccountTransfer() {
        ArrayList<BankAccount> accounts = currentCustomer.getAccounts();
        
        if (accounts.size() < 2) {
            System.out.println("You need at least 2 accounts to transfer between them.");
            return;
        }
        
        System.out.println("\n=== Transfer Between Your Accounts ===");
        System.out.println("Select source account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
        
        System.out.print("Source account (1-" + accounts.size() + "): ");
        int sourceChoice = getIntInput();
        
        if (sourceChoice < 1 || sourceChoice > accounts.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        System.out.println("Select destination account:");
        for (int i = 0; i < accounts.size(); i++) {
            if (i != sourceChoice - 1) {
                System.out.println((i + 1) + ". " + accounts.get(i));
            }
        }
        
        System.out.print("Destination account (1-" + accounts.size() + "): ");
        int destChoice = getIntInput();
        
        if (destChoice < 1 || destChoice > accounts.size() || destChoice == sourceChoice) {
            System.out.println("Invalid selection.");
            return;
        }
        
        System.out.print("Enter transfer amount: $");
        double amount = getDoubleInput();
        
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        
        BankAccount sourceAccount = accounts.get(sourceChoice - 1);
        BankAccount destAccount = accounts.get(destChoice - 1);
        
        if (bank.transferFunds(sourceAccount.getAccountNumber(), destAccount.getAccountNumber(), amount)) {
            System.out.println("Transfer successful!");
            System.out.println("From: " + sourceAccount.getAccountNumber() + " - New Balance: $" + 
                             String.format("%.2f", sourceAccount.getBalance()));
            System.out.println("To: " + destAccount.getAccountNumber() + " - New Balance: $" + 
                             String.format("%.2f", destAccount.getBalance()));
            printTransferReceipt(sourceAccount.getAccountNumber(), destAccount.getAccountNumber(), amount);
        } else {
            System.out.println("Transfer failed. Please check account balances and limits.");
        }
    }
    
    private void crossCustomerTransfer() {
        ArrayList<BankAccount> myAccounts = currentCustomer.getAccounts();
        
        if (myAccounts.isEmpty()) {
            System.out.println("No accounts available for transfer.");
            return;
        }
        
        System.out.println("\n=== Transfer to Another Customer ===");
        System.out.println("Select your source account:");
        for (int i = 0; i < myAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + myAccounts.get(i));
        }
        
        System.out.print("Source account (1-" + myAccounts.size() + "): ");
        int sourceChoice = getIntInput();
        
        if (sourceChoice < 1 || sourceChoice > myAccounts.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        System.out.print("Enter destination account number: ");
        String destAccountNumber = scanner.nextLine().trim();
        
        BankAccount destAccount = bank.getAccount(destAccountNumber);
        if (destAccount == null) {
            System.out.println("Destination account not found.");
            return;
        }
        
        if (!destAccount.isActive()) {
            System.out.println("Destination account is not active.");
            return;
        }
        
        if (destAccount.getCustomerId().equals(currentCustomer.getCustomerId())) {
            System.out.println("Cannot transfer to your own account. Use inter-account transfer instead.");
            return;
        }
        
        System.out.print("Enter transfer amount: $");
        double amount = getDoubleInput();
        
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        
        BankAccount sourceAccount = myAccounts.get(sourceChoice - 1);
        
        if (bank.transferFunds(sourceAccount.getAccountNumber(), destAccountNumber, amount)) {
            System.out.println("Transfer successful!");
            System.out.println("From: " + sourceAccount.getAccountNumber() + " - New Balance: $" + 
                             String.format("%.2f", sourceAccount.getBalance()));
            System.out.println("To: " + destAccountNumber);
            printTransferReceipt(sourceAccount.getAccountNumber(), destAccountNumber, amount);
        } else {
            System.out.println("Transfer failed. Please check account balances and limits.");
        }
    }
    
    // Admin functionality
    private void adminLogin() {
        System.out.print("Enter administrator username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter administrator password: ");
        String password = scanner.nextLine().trim();
        
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Administrator login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid administrator credentials.");
        }
    }
    
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
                    System.out.println("Administrator logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void showAdminMenu() {
        System.out.println("\n=== Administrator Menu ===");
        System.out.println("1. View All Customers");
        System.out.println("2. View All Accounts");
        System.out.println("3. Create New Account");
        System.out.println("4. Unblock Customer");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }
    
    private void viewAllCustomers() {
        System.out.println("\n=== All Customers ===");
        ArrayList<Customer> customers = bank.getAllCustomers();
        
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
                System.out.println("  Account Numbers: ");
                for (BankAccount account : customer.getAccounts()) {
                    System.out.println("    " + account.getAccountNumber());
                }
                System.out.println();
            }
        }
    }
    
    private void viewAllAccounts() {
        System.out.println("\n=== All Accounts ===");
        ArrayList<BankAccount> accounts = bank.getAllAccounts();
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account + " | Customer: " + account.getCustomerId());
            }
        }
    }
    
    private void createNewAccount() {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Choice (1-2): ");
        
        int typeChoice = getIntInput();
        String accountType;
        
        if (typeChoice == 1) {
            accountType = "savings";
        } else if (typeChoice == 2) {
            accountType = "checking";
        } else {
            System.out.println("Invalid account type.");
            return;
        }
        
        System.out.print("Enter initial balance: $");
        double initialBalance = getDoubleInput();
        
        if (initialBalance < 0) {
            System.out.println("Invalid initial balance.");
            return;
        }
        
        String accountNumber = bank.createNewAccount(customerId, accountType, initialBalance);
        
        if (accountNumber != null) {
            System.out.println("Account created successfully!");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Type: " + accountType);
            System.out.println("Initial Balance: $" + String.format("%.2f", initialBalance));
        } else {
            System.out.println("Failed to create account.");
        }
    }
    
    private void unblockCustomer() {
        System.out.print("Enter Customer ID to unblock: ");
        String customerId = scanner.nextLine().trim();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        if (!customer.isBlocked()) {
            System.out.println("Customer is not blocked.");
            return;
        }
        
        bank.unblockCustomer(customerId);
        System.out.println("Customer " + customerId + " has been unblocked successfully.");
    }
    
    // Helper methods from Task 1
    private void checkBalance(BankAccount account) {
        System.out.println("\n=== Balance Inquiry ===");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Type: " + account.getAccountType());
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
    
    private void printTransferReceipt(String fromAccount, String toAccount, double amount) {
        System.out.println("\n=== TRANSFER RECEIPT ===");
        System.out.println("Operation: FUND TRANSFER");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("From Account: " + fromAccount);
        System.out.println("To Account: " + toAccount);
        System.out.println("Thank you for banking with us!");
        System.out.println("========================");
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