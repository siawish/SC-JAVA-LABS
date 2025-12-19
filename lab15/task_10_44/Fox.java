import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Now uses getMaxAge() method from Animal.
 */
public class Fox extends Animal
{
    private static final int BREEDING_AGE = 15;
    private static final int MAX_AGE = 150;
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 2;
    private static final int RABBIT_FOOD_VALUE = 9;
    
    private int foodLevel;
    private Random rand = Randomizer.getRandom();

    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }

    /**
     * Return the breeding age of a fox.
     * @return The breeding age of a fox.
     */
    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * Return the maximum age of a fox.
     * @return The maximum age of a fox.
     */
    protected int getMaxAge()
    {
        return MAX_AGE;
    }

    public void act(List<Animal> newFoxes)
    {
        incrementAge();  // Now uses Animal's incrementAge
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);
            Location newLocation = findFood();
            if(newLocation == null) { 
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
    }
    
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    
    private void giveBirth(List<Animal> newFoxes)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, field, loc);
            newFoxes.add(young);
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
}
