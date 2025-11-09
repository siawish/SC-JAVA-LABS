/**
 * TASK 2: LOOSELY COUPLED VERSION
 * 
 * IMPROVEMENTS:
 * 1. Shape interface provides abstraction
 * 2. VolumeCalculator depends on interface, not concrete classes
 * 3. Easy to add new shapes (Cylinder, Sphere) without modifying VolumeCalculator
 * 4. Better encapsulation with private fields and getters
 * 5. Follows Open/Closed Principle (open for extension, closed for modification)
 * 6. Easy to test and maintain
 */

// Abstraction layer - Interface
interface Shape {
    double calculateVolume();
    String getShapeName();
}

// Concrete implementation - Box
class BoxShape implements Shape {
    private int length;
    private int width;
    private int height;
    
    public BoxShape(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateVolume() {
        return length * width * height;
    }
    
    @Override
    public String getShapeName() {
        return "Box";
    }
    
    public int getLength() { return length; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}

// Additional shape - Cylinder (demonstrates extensibility)
class Cylinder implements Shape {
    private double radius;
    private double height;
    
    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public double calculateVolume() {
        return Math.PI * radius * radius * height;
    }
    
    @Override
    public String getShapeName() {
        return "Cylinder";
    }
}

// Additional shape - Sphere (demonstrates extensibility)
class Sphere implements Shape {
    private double radius;
    
    public Sphere(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }
    
    @Override
    public String getShapeName() {
        return "Sphere";
    }
}

// Loosely coupled - depends on interface, not concrete classes
class VolumeCalculator {
    // Uses dependency injection - shape is passed as parameter
    public void displayVolume(Shape shape) {
        double volume = shape.calculateVolume();
        System.out.println(shape.getShapeName() + " Volume: " + String.format("%.2f", volume));
    }
    
    // Can handle any shape that implements Shape interface
    public void compareVolumes(Shape shape1, Shape shape2) {
        double vol1 = shape1.calculateVolume();
        double vol2 = shape2.calculateVolume();
        
        System.out.println("\n--- Volume Comparison ---");
        System.out.println(shape1.getShapeName() + ": " + String.format("%.2f", vol1));
        System.out.println(shape2.getShapeName() + ": " + String.format("%.2f", vol2));
        
        if (vol1 > vol2) {
            System.out.println(shape1.getShapeName() + " has larger volume");
        } else if (vol2 > vol1) {
            System.out.println(shape2.getShapeName() + " has larger volume");
        } else {
            System.out.println("Both shapes have equal volume");
        }
    }
}

public class Task2Loose {
    public static void demonstrate(int length, int width, int height) {
        System.out.println("LOOSELY COUPLED CODE IMPROVEMENTS:");
        System.out.println("+ VolumeCalculator depends on Shape interface");
        System.out.println("+ Can calculate volume for any shape without modification");
        System.out.println("+ Better encapsulation with private fields");
        System.out.println("+ Easy to extend with new shapes");
        System.out.println();
        
        VolumeCalculator calculator = new VolumeCalculator();
        
        // Create different shapes
        Shape box = new BoxShape(length, width, height);
        Shape cylinder = new Cylinder(3.0, height);
        Shape sphere = new Sphere(4.0);
        
        // Calculate volumes - same method works for all shapes
        calculator.displayVolume(box);
        calculator.displayVolume(cylinder);
        calculator.displayVolume(sphere);
        
        // Compare volumes
        calculator.compareVolumes(box, cylinder);
        
        System.out.println("\n=== KEY BENEFITS ===");
        System.out.println("1. VolumeCalculator doesn't know about specific shape implementations");
        System.out.println("2. Adding new shapes doesn't require changing VolumeCalculator");
        System.out.println("3. Each shape is responsible for its own volume calculation");
        System.out.println("4. Easy to test each component independently");
    }
}
