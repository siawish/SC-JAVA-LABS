/**
 * The CD class represents a CD in the DoME database.
 * Inherits from Item.
 */
public class CD extends Item {
    private String artist;
    private int numberOfTracks;

    /**
     * Constructor for CD
     * @param title The title of the CD
     * @param artist The artist name
     * @param numberOfTracks Number of tracks on the CD
     * @param playingTime Total playing time in minutes
     */
    public CD(String title, String artist, int numberOfTracks, int playingTime) {
        super(title, playingTime);
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
    }

    public String getArtist() {
        return artist;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    /**
     * Print details about this CD
     */
    @Override
    public void print() {
        System.out.println("CD:");
        super.print();
        System.out.println("    Artist: " + artist);
        System.out.println("    Tracks: " + numberOfTracks);
    }
}
