package RatGame;

/**
 * This class outlines a position in the game where a rat should be spawned when
 * the game is started.
 * @author James Watts
 */
public class RatSpawn {
    private final int xPos;
    private final int yPos;

    /**
     * Constructs a new RatSpawn object with an x and y coordinate.
     * @param xPos x coordinate of rat spawn.
     * @param yPos y coordinate of rat spawn.
     */
    public RatSpawn(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * @return the x coordinate of the RatSpawn object.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * @return the y coordinate of the RatSpawn object.
     */
    public int getyPos() {
        return yPos;
    }
}
