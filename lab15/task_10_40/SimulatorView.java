import java.awt.Color;
import java.util.HashMap;

/**
 * A simple view of the simulation (stub implementation).
 */
public class SimulatorView
{
    private HashMap<Class, Color> colors;
    private int width, height;

    public SimulatorView(int height, int width)
    {
        this.height = height;
        this.width = width;
        colors = new HashMap<>();
    }

    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    public void showStatus(int step, Field field)
    {
        int foxCount = 0;
        int rabbitCount = 0;
        
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal instanceof Fox) {
                    foxCount++;
                } else if(animal instanceof Rabbit) {
                    rabbitCount++;
                }
            }
        }
        
        System.out.println("Step: " + step + " | Foxes: " + foxCount + " | Rabbits: " + rabbitCount + " | Total: " + (foxCount + rabbitCount));
    }

    public boolean isViable(Field field)
    {
        return true;
    }
}
