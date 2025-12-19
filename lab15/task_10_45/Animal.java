import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * Now includes breed method.
 */
public abstract class Animal
{
    private boolean alive;
    private Field field;
    private Location location;
    private int age;
    private Random rand = Randomizer.getRandom();

    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }

    abstract public void act(List<Animal> newAnimals);

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * Return the breeding probability of this animal.
     * @return The breeding probability of this animal.
     */
    abstract protected double getBreedingProbability();

    /**
     * Return the maximum litter size of this animal.
     * @return The maximum litter size of this animal.
     */
    abstract protected int getMaxLitterSize();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * Return the maximum age of this animal.
     * @return The maximum age of this animal.
     */
    abstract protected int getMaxAge();

    /**
     * An animal can breed if it has reached the breeding age.
     * @return true if the animal can breed
     */
    protected boolean canBreed()
    {
        return age >= getBreedingAge();
    }

    /**
     * Return the breeding age of this animal.
     * @return The breeding age of this animal.
     */
    abstract protected int getBreedingAge();

    protected boolean isAlive()
    {
        return alive;
    }

    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    protected Location getLocation()
    {
        return location;
    }
    
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected Field getField()
    {
        return field;
    }

    protected int getAge()
    {
        return age;
    }

    protected void setAge(int age)
    {
        this.age = age;
    }
}
