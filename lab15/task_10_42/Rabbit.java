import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Now uses accessor/mutator methods for age instead of direct field access.
 */
public class Rabbit extends Animal
{
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private static final double BREEDING_PROBABILITY = 0.12;
    private static final int MAX_LITTER_SIZE = 4;
    
    private Random rand = Randomizer.getRandom();

    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        setAge(0);  // Use mutator
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));  // Use mutator
        }
    }

    public void act(List<Animal> newRabbits)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);            
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
    }

    private void incrementAge()
    {
        setAge(getAge() + 1);  // Use accessor and mutator
        if(getAge() > MAX_AGE) {  // Use accessor
            setDead();
        }
    }
    
    private void giveBirth(List<Animal> newRabbits)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
        }
    }
        
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    private boolean canBreed()
    {
        return getAge() >= BREEDING_AGE;  // Use accessor
    }
}
