import java.util.List;

/**
 * An interface representing actors in the simulation.
 * Actors can be animals or other participants.
 */
public interface Actor
{
    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActors A list to receive newly born actors.
     */
    void act(List<Actor> newActors);

    /**
     * Check whether the actor is alive or not.
     * @return true if the actor is still alive.
     */
    boolean isAlive();
}
