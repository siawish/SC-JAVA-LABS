/**
 * TASK 3: HIGH COHESION VERSION (Improved)
 * 
 * IMPROVEMENTS:
 * 1. All person-related data is in one Person class
 * 2. Person class has multiple related attributes and methods
 * 3. Methods operate on the class's own data (high cohesion)
 * 4. Represents a real-world concept properly
 * 5. Easy to maintain and extend
 * 6. Better encapsulation with validation
 * 7. Single object represents complete person information
 * 
 * HIGH COHESION: All related data and operations are grouped together
 */

// High Cohesion - All person-related data and methods in one class
class Person {
    private String name;
    private int age;
    private long mobileNumber;
    
    // Constructor
    public Person(String name, int age, long mobileNumber) {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public long getMobileNumber() {
        return mobileNumber;
    }
    
    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
    
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        }
    }
    
    public void setMobileNumber(long mobileNumber) {
        if (String.valueOf(mobileNumber).length() == 10) {
            this.mobileNumber = mobileNumber;
        }
    }
    
    // Business methods that operate on person's data
    public boolean isAdult() {
        return age >= 18;
    }
    
    public String getAgeCategory() {
        if (age < 13) return "Child";
        else if (age < 20) return "Teenager";
        else if (age < 60) return "Adult";
        else return "Senior";
    }
    
    public String getFormattedMobile() {
        String mobile = String.valueOf(mobileNumber);
        if (mobile.length() == 10) {
            return String.format("(%s) %s-%s", 
                mobile.substring(0, 3),
                mobile.substring(3, 6),
                mobile.substring(6));
        }
        return mobile;
    }
    
    // Display method - operates on own data
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " (" + getAgeCategory() + ")");
        System.out.println("Mobile: " + getFormattedMobile());
        System.out.println("Adult Status: " + (isAdult() ? "Yes" : "No"));
    }
    
    // toString for easy printing
    @Override
    public String toString() {
        return String.format("Person[name=%s, age=%d, mobile=%d]", name, age, mobileNumber);
    }
}

// PersonManager - manages collection of persons (also high cohesion)
class PersonManager {
    public void displayPerson(Person person) {
        System.out.println("=== Person Information ===");
        person.displayInfo();
    }
    
    public void comparePeople(Person p1, Person p2) {
        System.out.println("\n=== Comparing Two People ===");
        System.out.println(p1.getName() + " is " + p1.getAge() + " years old");
        System.out.println(p2.getName() + " is " + p2.getAge() + " years old");
        
        if (p1.getAge() > p2.getAge()) {
            System.out.println(p1.getName() + " is older");
        } else if (p2.getAge() > p1.getAge()) {
            System.out.println(p2.getName() + " is older");
        } else {
            System.out.println("Both are the same age");
        }
    }
}

public class Task3High {
    public static void demonstrate(String name, int age, int mobileno) {
        System.out.println("HIGH COHESION IMPROVEMENTS:");
        System.out.println("+ All person data in one cohesive Person class");
        System.out.println("+ Related attributes grouped together");
        System.out.println("+ Methods operate on class's own data");
        System.out.println("+ Better encapsulation with validation");
        System.out.println("+ Represents real-world concept properly");
        System.out.println("+ Single object for complete person information");
        System.out.println();
        
        // Create person object
        Person person = new Person(name, age, mobileno);
        
        // Display using PersonManager
        PersonManager manager = new PersonManager();
        manager.displayPerson(person);
        
        // Demonstrate additional functionality
        System.out.println("\n=== Additional Cohesive Methods ===");
        System.out.println("Is Adult: " + person.isAdult());
        System.out.println("Age Category: " + person.getAgeCategory());
        System.out.println("Formatted Mobile: " + person.getFormattedMobile());
        
        // Create another person for comparison
        Person person2 = new Person("John Doe", 25, 9876543210L);
        manager.comparePeople(person, person2);
        
        System.out.println("\n=== KEY BENEFITS ===");
        System.out.println("1. All person-related data is in one place");
        System.out.println("2. Methods work with the class's own data (high cohesion)");
        System.out.println("3. Easy to understand and maintain");
        System.out.println("4. Can add more person-related methods without affecting other classes");
        System.out.println("5. Better represents real-world Person concept");
    }
}
