/**
 * TASK 1: E-COMMERCE ORDER PROCESSING SYSTEM
 * 
 * SCENARIO: Online Shopping Platform
 * This demonstrates HIGH COHESION and LOOSE COUPLING principles
 * 
 * HIGH COHESION: Each class has a single, well-defined responsibility
 * - Product: Manages product data
 * - Order: Manages order details and calculations
 * - PaymentProcessor: Interface for payment processing (loose coupling)
 * - NotificationService: Handles customer notifications
 * 
 * LOOSE COUPLING: Classes interact through interfaces and minimal dependencies
 * - Payment methods implement PaymentProcessor interface
 * - Order doesn't know about specific payment implementations
 */

// Product class - HIGH COHESION (only handles product-related data)
class Product {
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    
    public double calculateSubtotal() {
        return price * quantity;
    }
}

// Order class - HIGH COHESION (only handles order-related operations)
class Order {
    private Product product;
    private String orderId;
    private double totalAmount;
    
    public Order(Product product) {
        this.product = product;
        this.orderId = "ORD" + System.currentTimeMillis();
        this.totalAmount = calculateTotal();
    }
    
    private double calculateTotal() {
        double subtotal = product.calculateSubtotal();
        double tax = subtotal * 0.10; // 10% tax
        return subtotal + tax;
    }
    
    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public Product getProduct() { return product; }
}

// Payment Processor Interface - LOOSE COUPLING (abstraction)
interface PaymentProcessor {
    boolean processPayment(double amount);
    String getPaymentMethod();
}

// Concrete payment implementations
class CreditCardPayment implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + String.format("%.2f", amount));
        return true;
    }
    
    public String getPaymentMethod() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + String.format("%.2f", amount));
        return true;
    }
    
    public String getPaymentMethod() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer of $" + String.format("%.2f", amount));
        return true;
    }
    
    public String getPaymentMethod() {
        return "Bank Transfer";
    }
}

// Notification Service - HIGH COHESION (only handles notifications)
class NotificationService {
    public void sendOrderConfirmation(String email, Order order) {
        System.out.println("\n--- Email Notification ---");
        System.out.println("To: " + email);
        System.out.println("Subject: Order Confirmation - " + order.getOrderId());
        System.out.println("Your order has been confirmed!");
        System.out.println("Total Amount: $" + String.format("%.2f", order.getTotalAmount()));
    }
}

// Order Service - Orchestrates the order process with LOOSE COUPLING
class OrderService {
    private NotificationService notificationService;
    
    public OrderService() {
        this.notificationService = new NotificationService();
    }
    
    // Uses PaymentProcessor interface - doesn't depend on concrete implementations
    public void processOrder(Order order, PaymentProcessor paymentProcessor, String customerEmail) {
        System.out.println("\n=== Processing Order ===");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Product: " + order.getProduct().getName());
        System.out.println("Quantity: " + order.getProduct().getQuantity());
        System.out.println("Total Amount: $" + String.format("%.2f", order.getTotalAmount()));
        
        boolean paymentSuccess = paymentProcessor.processPayment(order.getTotalAmount());
        
        if (paymentSuccess) {
            System.out.println("Payment Method: " + paymentProcessor.getPaymentMethod());
            System.out.println("Payment Status: SUCCESS");
            notificationService.sendOrderConfirmation(customerEmail, order);
        } else {
            System.out.println("Payment Status: FAILED");
        }
    }
}

public class Task1 {
    public static void processOrder(String productName, double price, int quantity, 
                                   String email, String paymentMethod) {
        // Create product
        Product product = new Product(productName, price, quantity);
        
        // Create order
        Order order = new Order(product);
        
        // Select payment processor based on input - LOOSE COUPLING
        PaymentProcessor paymentProcessor;
        switch (paymentMethod.toUpperCase()) {
            case "CREDIT_CARD":
                paymentProcessor = new CreditCardPayment();
                break;
            case "PAYPAL":
                paymentProcessor = new PayPalPayment();
                break;
            case "BANK_TRANSFER":
                paymentProcessor = new BankTransferPayment();
                break;
            default:
                paymentProcessor = new CreditCardPayment();
        }
        
        // Process order
        OrderService orderService = new OrderService();
        orderService.processOrder(order, paymentProcessor, email);
        
        // Explanation
        System.out.println("\n=== COHESION & COUPLING ANALYSIS ===");
        System.out.println("HIGH COHESION:");
        System.out.println("- Product class: Only manages product data");
        System.out.println("- Order class: Only manages order calculations");
        System.out.println("- PaymentProcessor: Only handles payments");
        System.out.println("- NotificationService: Only sends notifications");
        System.out.println("\nLOOSE COUPLING:");
        System.out.println("- OrderService uses PaymentProcessor interface");
        System.out.println("- Can easily add new payment methods without changing OrderService");
        System.out.println("- Classes are independent and reusable");
    }
}
