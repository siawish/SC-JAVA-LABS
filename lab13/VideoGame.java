/**
 * The VideoGame class represents a video game in the DoME database.
 * Inherits from Item. (Task #4)
 */
public class VideoGame extends Item {
    private String platform;
    private int numberOfPlayers;

    /**
     * Constructor for VideoGame
     * @param title The title of the game
     * @param platform The gaming platform (e.g., "PS5", "Xbox", "PC")
     * @param numberOfPlayers Maximum number of players
     * @param playingTime Estimated playing time in minutes
     */
    public VideoGame(String title, String platform, int numberOfPlayers, int playingTime) {
        super(title, playingTime);
        this.platform = platform;
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getPlatform() {
        return platform;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Print details about this video game
     */
    @Override
    public void print() {
        System.out.println("Video Game:");
        super.print();
        System.out.println("    Platform: " + platform);
        System.out.println("    Max Players: " + numberOfPlayers);
    }
}
