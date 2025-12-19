/**
 * Demonstrates the use of shared random generator for repeatable simulations.
 * 
 * @author Your Name
 * @version 2024
 */
public class Simulation
{
    /**
     * Run the simulation.
     */
    public void run()
    {
        System.out.println("=== First Simulation Run ===");
        Randomizer.reset();
        Animal dog = new Animal("Dog");
        Animal cat = new Animal("Cat");
        
        for(int i = 0; i < 3; i++) {
            dog.grow();
            cat.grow();
        }
        
        System.out.println("\n=== Second Simulation Run (should be identical) ===");
        Randomizer.reset();
        Animal dog2 = new Animal("Dog");
        Animal cat2 = new Animal("Cat");
        
        for(int i = 0; i < 3; i++) {
            dog2.grow();
            cat2.grow();
        }
    }
    
    public static void main(String[] args)
    {
        Simulation sim = new Simulation();
        sim.run();
    }
}
