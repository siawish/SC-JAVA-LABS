import java.util.Scanner;

/**
 * DoMEApp - Interactive console application for DoME.
 * Takes user input to manage multimedia items.
 */
public class DoMEApp {
    private Database db;
    private Scanner scanner;

    public DoMEApp() {
        db = new Database();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to DoME - Database of Multimedia Entertainment");
        
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: addCD(); break;
                case 2: addVideo(); break;
                case 3: addVideoGame(); break;
                case 4: db.list(); break;
                case 5: searchItems(); break;
                case 6: running = false; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
        
        System.out.println("Thank you for using DoME. Goodbye!");
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Add CD");
        System.out.println("2. Add Video");
        System.out.println("3. Add Video Game");
        System.out.println("4. List all items");
        System.out.println("5. Search by title");
        System.out.println("6. Exit");
        System.out.println("================");
    }

    private void addCD() {
        System.out.println("\n--- Add New CD ---");
        String title = getStringInput("Title: ");
        String artist = getStringInput("Artist: ");
        int tracks = getIntInput("Number of tracks: ");
        int playingTime = getIntInput("Playing time (minutes): ");
        
        CD cd = new CD(title, artist, tracks, playingTime);
        
        String gotIt = getStringInput("Do you own it? (y/n): ");
        cd.setGotIt(gotIt.equalsIgnoreCase("y"));
        
        String comment = getStringInput("Comment (press Enter to skip): ");
        if (!comment.isEmpty()) {
            cd.setComment(comment);
        }
        
        db.addItem(cd);
        System.out.println("CD added successfully!");
    }

    private void addVideo() {
        System.out.println("\n--- Add New Video ---");
        String title = getStringInput("Title: ");
        String director = getStringInput("Director: ");
        int playingTime = getIntInput("Playing time (minutes): ");
        
        Video video = new Video(title, director, playingTime);
        
        String gotIt = getStringInput("Do you own it? (y/n): ");
        video.setGotIt(gotIt.equalsIgnoreCase("y"));
        
        String comment = getStringInput("Comment (press Enter to skip): ");
        if (!comment.isEmpty()) {
            video.setComment(comment);
        }
        
        db.addItem(video);
        System.out.println("Video added successfully!");
    }

    private void addVideoGame() {
        System.out.println("\n--- Add New Video Game ---");
        String title = getStringInput("Title: ");
        String platform = getStringInput("Platform (e.g., PS5, Xbox, PC): ");
        int players = getIntInput("Max number of players: ");
        int playingTime = getIntInput("Estimated playing time (minutes): ");
        
        VideoGame game = new VideoGame(title, platform, players, playingTime);
        
        String gotIt = getStringInput("Do you own it? (y/n): ");
        game.setGotIt(gotIt.equalsIgnoreCase("y"));
        
        String comment = getStringInput("Comment (press Enter to skip): ");
        if (!comment.isEmpty()) {
            game.setComment(comment);
        }
        
        db.addItem(game);
        System.out.println("Video Game added successfully!");
    }

    private void searchItems() {
        String searchTerm = getStringInput("Enter search term: ");
        db.searchByTitle(searchTerm);
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        DoMEApp app = new DoMEApp();
        app.run();
    }
}
