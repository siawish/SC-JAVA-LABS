/**
 * Main class for Task 2 - Advanced Banking System with Transfers and Administration
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Advanced Banking System ===");
        System.out.println("Complete Banking Solution with Transfers & Administration");
        System.out.println("\nSample Customer Credentials:");
        System.out.println("Customer ID: C001, PIN: 1234 (Malik Muhammad Sisaiwsh)");
        System.out.println("Customer ID: C002, PIN: 5678 (Malik Muhammad Sisaiwsh)");
        System.out.println("Customer ID: C003, PIN: 9999 (Malik Muhammad Sisaiwsh)");
        System.out.println("\nAdministrator Credentials:");
        System.out.println("Username: admin, Password: password");
        System.out.println("================================================");
        
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        atm.start();
    }
}