import java.util.Random;
import java.util.List;
import java.awt.Color;

/**
 * Responsible for generating the initial population.
 */
public class PopulationGenerator
{
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    
    private Random rand = Randomizer.getRandom();
    private SimulatorView view;

    public PopulationGenerator(SimulatorView view)
    {
        this.view = view;
        defineColors();
    }

    private void defineColors()
    {
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
    }

    public void populate(Field field, List<Actor> actors)
    {
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    actors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    actors.add(rabbit);
                }
            }
        }
    }
}
