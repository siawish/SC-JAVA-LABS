import java.util.ArrayList;

/**
 * The Database class stores all multimedia items.
 * Uses polymorphism to store different types of items in a single collection.
 */
public class Database {
    private ArrayList<Item> items;

    /**
     * Constructor - creates an empty database
     */
    public Database() {
        items = new ArrayList<>();
    }

    /**
     * Add an item to the database
     * @param item The item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the database
     * @param item The item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * List all items in the database
     */
    public void list() {
        System.out.println("=== DoME Database Contents ===");
        if (items.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            for (Item item : items) {
                item.print();
                System.out.println();
            }
        }
        System.out.println("==============================");
    }

    /**
     * Search for items by title (partial match)
     * @param searchTitle The title to search for
     */
    public void searchByTitle(String searchTitle) {
        System.out.println("Search results for: " + searchTitle);
        boolean found = false;
        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                item.print();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found.");
        }
    }

    /**
     * Get the number of items in the database
     * @return The number of items
     */
    public int getSize() {
        return items.size();
    }
}
