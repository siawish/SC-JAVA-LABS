/**
 * TASK 2: TIGHTLY COUPLED VERSION
 * 
 * ANALYSIS: YES, this code is TIGHTLY COUPLED
 * 
 * REASONS:
 * 1. Volume class directly depends on the concrete Box class
 * 2. Volume class creates Box object directly (new Box())
 * 3. If we want to calculate volume for Cylinder or Sphere, we must modify Volume class
 * 4. Volume class accesses Box's public field directly (b.volume)
 * 5. No abstraction or interface between Volume and Box
 * 6. Hard to test Volume class independently
 * 7. Changes to Box class may require changes to Volume class
 */

class Box {
    public int volume;
    
    Box(int length, int width, int height) {
        this.volume = length * width * height;
    }
}

class Volume {
    public static void calculate(int length, int width, int height) {
        // Tightly coupled - directly depends on Box class
        Box b = new Box(length, width, height);
        System.out.println("Box Volume: " + b.volume);
    }
}

public class Task2Tight {
    public static void demonstrate(int length, int width, int height) {
        System.out.println("TIGHTLY COUPLED CODE ANALYSIS:");
        System.out.println("X Volume class directly creates Box object");
        System.out.println("X Cannot calculate volume for other shapes without modifying Volume class");
        System.out.println("X Direct dependency on concrete Box class");
        System.out.println("X Accessing public field directly (poor encapsulation)");
        System.out.println();
        
        Volume.calculate(length, width, height);
    }
}
