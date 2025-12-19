import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * Now extends Actor.
 */
public abstract class Animal extends Actor
{
    private int age;

    public Animal(Field field, Location location)
    {
        super(field, location);
        age = 0;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && Randomizer.getRandom().nextDouble() <= getBreedingProbability()) {
            births = Randomizer.getRandom().nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    abstract protected double getBreedingProbability();
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

    abstract protected int getMaxAge();

    protected boolean canBreed()
    {
        return age >= getBreedingAge();
    }

    abstract protected int getBreedingAge();

    protected int getAge()
    {
        return age;
    }

    protected void setAge(int age)
    {
        this.age = age;
    }
}
