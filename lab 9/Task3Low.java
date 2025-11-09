/**
 * TASK 3: LOW COHESION VERSION (Original Code)
 * 
 * ANALYSIS: NO, this code has LOW COHESION
 * 
 * REASONS FOR LOW COHESION:
 * 1. Each class (Name, Age, Number) has only ONE attribute and ONE method
 * 2. These classes are too granular and don't represent a cohesive concept
 * 3. They should be part of a single Person class
 * 4. The methods don't add value - they just set and return the same value
 * 5. Display class needs to create 3 separate objects for related data
 * 6. Data that belongs together is scattered across multiple classes
 * 7. Violates Single Responsibility at a higher level (Person concept is split)
 * 
 * HIGH COHESION means: Related data and methods should be together in one class
 * This code does the opposite - it splits related person data into separate classes
 */

class Name {
    String name;
    
    public String getName(String name) {
        this.name = name;
        return name;
    }
}

class Age {
    int age;
    
    public int getAge(int age) {
        this.age = age;
        return age;
    }
}

class Number {
    int mobileno;
    
    public int getNumber(int mobileno) {
        this.mobileno = mobileno;
        return mobileno;
    }
}

class Display {
    public static void show(String name, int age, int mobileno) {
        Name n = new Name();
        System.out.println("Name: " + n.getName(name));
        
        Age a = new Age();
        System.out.println("Age: " + a.getAge(age));
        
        Number no = new Number();
        System.out.println("Mobile: " + no.getNumber(mobileno));
    }
}

public class Task3Low {
    public static void demonstrate(String name, int age, int mobileno) {
        System.out.println("LOW COHESION ANALYSIS:");
        System.out.println("X Person data split across 3 separate classes");
        System.out.println("X Each class has only one attribute (too granular)");
        System.out.println("X Related data is not grouped together");
        System.out.println("X Need to create 3 objects for one person's data");
        System.out.println("X Methods don't provide meaningful functionality");
        System.out.println("X Poor representation of real-world Person concept");
        System.out.println();
        
        Display.show(name, age, mobileno);
    }
}
