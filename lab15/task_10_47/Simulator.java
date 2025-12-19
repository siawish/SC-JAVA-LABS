import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple predator-prey simulator.
 * Now uses Actor interface.
 */
public class Simulator
{
    private List<Actor> actors;
    private Field field;
    private int step;
    private SimulatorView view;
    private PopulationGenerator populationGenerator;
    
    public Simulator()
    {
        this(120, 80);
    }
    
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = 120;
            width = 80;
        }
        
        actors = new ArrayList<>();
        field = new Field(depth, width);
        view = new SimulatorView(depth, width);
        populationGenerator = new PopulationGenerator(view);
        
        reset();
    }
    
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    public void simulateOneStep()
    {
        step++;

        List<Actor> newActors = new ArrayList<>();        
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newActors);
            if(!actor.isAlive()) {
                it.remove();
            }
        }
               
        actors.addAll(newActors);
        view.showStatus(step, field);
    }

    public void reset()
    {
        step = 0;
        actors.clear();
        populationGenerator.populate(field, actors);
        
        view.showStatus(step, field);
    }
    
    public static void main(String[] args)
    {
        Simulator sim = new Simulator();
        sim.simulate(50);
    }
}
