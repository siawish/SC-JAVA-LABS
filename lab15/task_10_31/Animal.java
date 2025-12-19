import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * Uses the shared random generator from Randomizer.
 * 
 * @author Your Name
 * @version 2024
 */
public class Animal
{
    private String name;
    private int age;
    private Random rand;

    /**
     * Create an animal with a name and initial age of 0.
     * @param name The animal's name.
     */
    public Animal(String name)
    {
        this.name = name;
        this.age = 0;
        this.rand = Randomizer.getRandom();
    }

    /**
     * Get the animal's name.
     * @return The animal's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the animal's age.
     * @return The animal's age.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Simulate the animal growing.
     * Uses the shared random generator for repeatable behavior.
     */
    public void grow()
    {
        int growth = rand.nextInt(5) + 1;
        age += growth;
        System.out.println(name + " grew by " + growth + " units. Age is now: " + age);
    }
}
