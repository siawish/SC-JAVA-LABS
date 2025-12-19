import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represent a rectangular grid of field positions.
 */
public class Field
{
    private int depth, width;
    private Object[][] field;

    public Field(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }
    
    public void clear()
    {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    public void clear(Location location)
    {
        field[location.getRow()][location.getCol()] = null;
    }
    
    public void place(Object animal, Location location)
    {
        field[location.getRow()][location.getCol()] = animal;
    }
    
    public Object getObjectAt(Location location)
    {
        return getObjectAt(location.getRow(), location.getCol());
    }
    
    public Object getObjectAt(int row, int col)
    {
        return field[row][col];
    }
    
    public Location freeAdjacentLocation(Location location)
    {
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.size() > 0) {
            return free.get(0);
        }
        return null;
    }
    
    public List<Location> getFreeAdjacentLocations(Location location)
    {
        List<Location> free = new ArrayList<>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location next : adjacent) {
            if(getObjectAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }
    
    public List<Location> adjacentLocations(Location location)
    {
        List<Location> locations = new ArrayList<>();
        int row = location.getRow();
        int col = location.getCol();
        for(int roffset = -1; roffset <= 1; roffset++) {
            int nextRow = row + roffset;
            if(nextRow >= 0 && nextRow < depth) {
                for(int coffset = -1; coffset <= 1; coffset++) {
                    int nextCol = col + coffset;
                    if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }
        Collections.shuffle(locations);
        return locations;
    }

    public int getDepth()
    {
        return depth;
    }
    
    public int getWidth()
    {
        return width;
    }
}
