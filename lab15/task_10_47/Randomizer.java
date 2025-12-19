import java.util.Random;

/**
 * Provide control over the randomization of the simulation.
 */
public class Randomizer
{
    private static final int SEED = 1111;
    private static final Random rand = new Random(SEED);
    private static final boolean useShared = true;

    public static Random getRandom()
    {
        if(useShared) {
            return rand;
        }
        else {
            return new Random();
        }
    }
    
    public static void reset()
    {
        if(useShared) {
            rand.setSeed(SEED);
        }
    }
}
