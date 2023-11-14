package RatGame;

import java.util.ArrayList;

/**
 * Level object containing all the data stored in a level.
 */
public class Level {
    private final String levelFileName;
    private final String filePath;
    private char[][] levelGrid;
    private int sizeX;
    private int sizeY;
    private ArrayList<RatSpawn> ratSpawns = new ArrayList<>();
    private ArrayList<Rat> rats = new ArrayList<>();


    /**
     * Constructs a new level if you only have file name and path.
     * @param levelFileName name of the Level
     * @param filePath path to the level
     */
    public Level(String levelFileName, String filePath){
        this.levelFileName = levelFileName;
        this.filePath = filePath;
    }

    /**
     * Constructs a new Level object from parameters.
     * @param levelFileName name of the level
     * @param filePath path to the level
     * @param levelGrid the grid of tiles that make up the level's map
     * @param x x size of the map
     * @param y y size of the map
     */
    public Level(String levelFileName, String filePath, char[][] levelGrid, int x, int y){
        this.levelFileName = levelFileName;
        this.filePath = filePath;
        this.levelGrid = levelGrid;
        this.sizeX = x;
        this.sizeY = y;
    }

    /**
     * Get the rats from the arraylist
     * @return The rats
     */
    public ArrayList<Rat> getRats() {
        return rats;
    }

    /**
     * Adds rats to the Arraylist
     * @param rat
     */
    public void addRat(Rat rat){
        this.rats.add(rat);
    }

    /**
     * Get the grid of tiles that make up the level's map
     * @return The grid of tiles that make up the level's map
     */
    public char[][] getLevelGrid() {
        return levelGrid;
    }

    /**
     * Sets the grid of tiles that make up the level's map
     * @param levelGrid
     */
    public void setLevelGrid(char[][] levelGrid) {
        this.levelGrid = levelGrid;
    }
    
    /**
     * Get the name of the level
     * @return name of the level
     */
    public String getName() {
        return this.levelFileName;
    }

    /**
     * Get the path to the level
     * @return path to the level
     */
    public String getPath() {
        return this.filePath + this.levelFileName;
    }

    /**
     * Get the x size of the map
     * @return x size of the map
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Get the y size of the map
     * @return y size of the map
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Sets x size of the map
     * @param sizeX
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Sets y size of the map
     * @param sizeY
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public ArrayList<RatSpawn> getRatSpawns() {
        return ratSpawns;
    }

    public void setRatSpawns(ArrayList<RatSpawn> ratSpawns) {
        this.ratSpawns = ratSpawns;
    }

    public void addRatSpawn(RatSpawn ratSpawn) {
        this.ratSpawns.add(ratSpawn);
    }

}
