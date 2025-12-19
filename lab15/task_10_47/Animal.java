import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * Now extends ActorBase which implements Actor interface.
 */
public abstract class Animal extends ActorBase
{
    private int age;

    public Animal(Field field, Location location)
    {
        super(field, location);
        age = 0;
    }

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
