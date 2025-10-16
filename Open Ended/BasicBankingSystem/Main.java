/**
 * Main class for Task 1 - Banking System
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Basic Banking System ===");
        System.out.println("Core ATM & Account Management System");
        System.out.println("\nSample Customer Credentials:");
        System.out.println("Customer ID: C001, PIN: 1234 (Malik Muhammad Sisaiwsh)");
        System.out.println("Customer ID: C002, PIN: 5678 (Malik Muhammad Sisaiwsh)");
        System.out.println("Customer ID: C003, PIN: 9999 (Malik Muhammad Sisaiwsh)");
        System.out.println("=======================================");
        
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        atm.start();
    }
}