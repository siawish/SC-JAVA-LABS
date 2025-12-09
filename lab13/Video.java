/**
 * The Video class represents a video in the DoME database.
 * Inherits from Item.
 */
public class Video extends Item {
    private String director;

    /**
     * Constructor for Video
     * @param title The title of the video
     * @param director The director's name
     * @param playingTime Total playing time in minutes
     */
    public Video(String title, String director, int playingTime) {
        super(title, playingTime);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    /**
     * Print details about this video
     */
    @Override
    public void print() {
        System.out.println("Video:");
        super.print();
        System.out.println("    Director: " + director);
    }
}
