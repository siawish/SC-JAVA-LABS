/**
 * DoMEDemo - Demonstrates all lab tasks for the DoME project.
 * Database of Multimedia Entertainment
 */
public class DoMEDemo {
    
    public static void main(String[] args) {
        // ============ TASK #1 ============
        System.out.println("========== TASK #1 ==========");
        System.out.println("Creating CD and Video objects, adding to database...\n");
        
        Database db = new Database();
        
        // Create CD objects
        CD cd1 = new CD("Abbey Road", "The Beatles", 17, 47);
        CD cd2 = new CD("Thriller", "Michael Jackson", 9, 42);
        CD cd3 = new CD("Back in Black", "AC/DC", 10, 42);
        
        // Create Video objects
        Video video1 = new Video("The Matrix", "Wachowski Sisters", 136);
        Video video2 = new Video("Inception", "Christopher Nolan", 148);
        
        // Add to database
        db.addItem(cd1);
        db.addItem(cd2);
        db.addItem(cd3);
        db.addItem(video1);
        db.addItem(video2);
        
        // List database contents
        db.list();
        
        // ============ TASK #2 ============
        System.out.println("\n========== TASK #2 ==========");
        System.out.println("Testing comment behavior with object references...\n");
        
        CD testCD = new CD("Dark Side of the Moon", "Pink Floyd", 10, 43);
        db.addItem(testCD);
        
        System.out.println("Before adding comment:");
        db.list();
        
        // Add comment to the SAME object reference
        testCD.setComment("One of the greatest albums ever made!");
        
        System.out.println("\nAfter adding comment to the CD object:");
        db.list();
        
        System.out.println("EXPLANATION: The comment appears because the database stores");
        System.out.println("a REFERENCE to the CD object, not a copy. When we modify the");
        System.out.println("original object, the change is reflected in the database.\n");
        
        // ============ TASK #3 ============
        System.out.println("\n========== TASK #3 ==========");
        System.out.println("Testing inherited methods on CD object...\n");
        
        CD cd4 = new CD("Nevermind", "Nirvana", 12, 49);
        
        // Calling inherited methods from Item class
        System.out.println("Title (inherited getter): " + cd4.getTitle());
        System.out.println("Playing time (inherited): " + cd4.getPlayingTime() + " mins");
        System.out.println("Got it (inherited): " + cd4.getGotIt());
        
        cd4.setGotIt(true);  // inherited method
        cd4.setComment("Grunge classic!");  // inherited method
        
        System.out.println("\nAfter using inherited setters:");
        System.out.println("Got it: " + cd4.getGotIt());
        System.out.println("Comment: " + cd4.getComment());
        
        System.out.println("\nOBSERVATION: Inherited methods work seamlessly on subclass objects.");
        System.out.println("CD inherits setComment(), getComment(), setGotIt(), etc. from Item.\n");
        
        // ============ TASK #4 ============
        System.out.println("\n========== TASK #4 ==========");
        System.out.println("Testing VideoGame class...\n");
        
        VideoGame game1 = new VideoGame("The Legend of Zelda", "Nintendo Switch", 1, 100);
        VideoGame game2 = new VideoGame("FIFA 24", "PS5", 4, 90);
        VideoGame game3 = new VideoGame("Minecraft", "PC", 8, 999);
        
        // Test VideoGame-specific methods
        System.out.println("Game: " + game1.getTitle());
        System.out.println("Platform: " + game1.getPlatform());
        System.out.println("Max Players: " + game1.getNumberOfPlayers());
        
        // Test inherited methods
        game1.setGotIt(true);
        game1.setComment("Amazing adventure game!");
        
        // Add games to database
        db.addItem(game1);
        db.addItem(game2);
        db.addItem(game3);
        
        System.out.println("\nDatabase with Video Games added:");
        db.list();
        
        // ============ TASK #5 Preview ============
        System.out.println("\n========== TASK #5 Preview ==========");
        System.out.println("Additional features to implement (Chapter 8 exercises):");
        System.out.println("- Persistent storage (file I/O)");
        System.out.println("- Advanced search (by artist, director, platform)");
        System.out.println("- Remove items from database");
        System.out.println("- GUI interface");
        System.out.println("- Sorting and filtering\n");
        
        // Demonstrate search
        System.out.println("Search demo - searching for 'The':");
        db.searchByTitle("The");
    }
}
