/**
 * The Item class represents a general multimedia item in the DoME database.
 * This is the superclass for CD, Video, and VideoGame.
 */
public class Item {
    private String title;
    private int playingTime;
    private boolean gotIt;
    private String comment;

    /**
     * Constructor for Item
     * @param title The title of the item
     * @param playingTime The playing time in minutes
     */
    public Item(String title, int playingTime) {
        this.title = title;
        this.playingTime = playingTime;
        this.gotIt = false;
        this.comment = "";
    }

    public String getTitle() {
        return title;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public boolean getGotIt() {
        return gotIt;
    }

    public void setGotIt(boolean gotIt) {
        this.gotIt = gotIt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Print details about this item to the terminal
     */
    public void print() {
        System.out.println("    Title: " + title);
        System.out.println("    Playing time: " + playingTime + " mins");
        System.out.println("    Got it: " + gotIt);
        if (!comment.isEmpty()) {
            System.out.println("    Comment: " + comment);
        }
    }
}
