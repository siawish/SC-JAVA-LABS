/**
 * A base class providing common functionality for actors.
 * This class provides the implementation that was in the abstract Actor class.
 */
public abstract class ActorBase implements Actor
{
    private boolean alive;
    private Field field;
    private Location location;

    public ActorBase(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
    }

    public boolean isAlive()
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
}
