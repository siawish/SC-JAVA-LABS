import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * Now includes canBreed method that uses abstract getBreedingAge.
 */
public abstract class Animal
{
    private boolean alive;
    private Field field;
    private Location location;
    private int age;

    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }

    abstract public void act(List<Animal> newAnimals);

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
