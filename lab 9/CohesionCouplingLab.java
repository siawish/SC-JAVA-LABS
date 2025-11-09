import java.util.Scanner;

/**
 * Main Lab Menu - Cohesion and Coupling Demonstration
 */
public class CohesionCouplingLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n========================================");
            System.out.println("COHESION & COUPLING LAB - MAIN MENU");
            System.out.println("========================================");
            System.out.println("1. Task 1: E-Commerce Order Processing (Cohesion & Coupling)");
            System.out.println("2. Task 2: Box Volume - Tight vs Loose Coupling");
            System.out.println("3. Task 3: Person Data - Cohesion Analysis");
            System.out.println("4. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    runTask1(scanner);
                    break;
                case 2:
                    runTask2(scanner);
                    break;
                case 3:
                    runTask3(scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void runTask1(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("TASK 1: E-COMMERCE ORDER PROCESSING");
        System.out.println("========================================");
        System.out.println("Scenario: Online Shopping Order System");
        System.out.println("Demonstrating HIGH COHESION & LOOSE COUPLING\n");
        
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter payment method (CREDIT_CARD/PAYPAL/BANK_TRANSFER): ");
        String paymentMethod = scanner.nextLine();
        
        Task1.processOrder(productName, price, quantity, email, paymentMethod);
    }
    
    private static void runTask2(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("TASK 2: BOX VOLUME CALCULATION");
        System.out.println("========================================");
        
        System.out.print("Enter box length: ");
        int length = scanner.nextInt();
        
        System.out.print("Enter box width: ");
        int width = scanner.nextInt();
        
        System.out.print("Enter box height: ");
        int height = scanner.nextInt();
        
        System.out.println("\n--- TIGHTLY COUPLED VERSION ---");
        Task2Tight.demonstrate(length, width, height);
        
        System.out.println("\n--- LOOSELY COUPLED VERSION ---");
        Task2Loose.demonstrate(length, width, height);
    }
    
    private static void runTask3(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("TASK 3: PERSON DATA - COHESION ANALYSIS");
        System.out.println("========================================");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        
        System.out.print("Enter mobile number: ");
        int mobileNo = scanner.nextInt();
        
        System.out.println("\n--- LOW COHESION VERSION (Original) ---");
        Task3Low.demonstrate(name, age, mobileNo);
        
        System.out.println("\n--- HIGH COHESION VERSION (Improved) ---");
        Task3High.demonstrate(name, age, mobileNo);
    }
}
