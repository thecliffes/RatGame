package RatGame;

import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class reads a level file and builds the actual map where game actions are
 * going to happen.
 * @author James Butler
 * @version 1
 * */
public class MapView extends Canvas{
    private Image PATH;
    private Image GRASS;
    private Image TUNNEL;
    private Image GRASS_FLOWER;

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 900;
    private static final int GRID_CELL_WIDTH = 50;
	private static final int GRID_CELL_HEIGHT = 50;

    static char[][] map;
    static int sizeX;
    static int sizeY;

    /***
     * Creates a new map view object, setting its height and width.
     * */
    public MapView() {
        this.setHeight(CANVAS_HEIGHT);
        this.setWidth(CANVAS_WIDTH);
        PATH = new Image("file:Images/Path.png", 50, 50, false, false);
        GRASS = new Image("file:Images/Grass.png", 50, 50, false, false);
        TUNNEL = new Image("file:Images/Tunnel.png", 50, 50, false, false);
        GRASS_FLOWER = new Image("file:Images/GrassFlower.png", 50, 50, false, false);
    }


    /**
     * Gets a specific square from the map grid.
     * @param gridX The X coordinate.
     * @param gridY The Y coordinate.
     * @return A specific square.
     * */
    public static char getSquare(double gridX, double gridY){
        if ((gridX >= 0) && (gridY >= 0) && (gridX <= MapView.sizeY) && (gridY <= MapView.sizeX)) {
            return (MapView.map[(int) (gridX)][(int) (gridY)]);
        } else
            return 'G';
    }
    

    /**
     * This method adds a path to the map.
     * @param image A path image.
     * @param x1 The X coordinate where the path starts.
     * @param y1 The Y coordinate where the path starts.
     * @param Direction A boolean value.
     * @param length The lenght of the path.
     * */
    public void addPath(Image image, int x1, int y1, boolean Direction, int length){
        GraphicsContext gc = this.getGraphicsContext2D();
       
        if (Direction){
            for (int x = 0; x < length;  x++) {
                gc.drawImage(image, (x1 + x) * GRID_CELL_WIDTH, y1 * GRID_CELL_HEIGHT);
            }
        }
        else {
            for (int y = 0; y < length; y++) {
                gc.drawImage(image,  x1 * GRID_CELL_WIDTH, (y1 + y) * GRID_CELL_HEIGHT);
            }
        }
    }

    /**
     * Gets the map grid.
     * @return A 2d array consisting of the map grid.
     * */
    public char[][] getMap() {
        return map;
    }

    /**
     * This method updates the map's appearance adding different kinds of surfaces.
     * */
    public void mapUpdate() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(new Color(0.2,0.2,0.2, 1));
        gc.fillRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 'G')
                {
                    gc.drawImage(GRASS, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
                else if (map[x][y] == 'P')
                {
                    gc.drawImage(PATH, (x) * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
                else if (map[x][y] == 'T')
                {
                    gc.drawImage(TUNNEL, (x) * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
                else if (map[x][y] == 'F')
                {
                    gc.drawImage(GRASS_FLOWER, (x) * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
            }
        }
    }

    /**
     * This method reads a level file and uses its information to customise the map.
     * @param level a level object
     */
    public void readMap(Level level) {
        GraphicsContext gc = this.getGraphicsContext2D();
        map = level.getLevelGrid();
        sizeY = map.length;
        sizeX = map[1].length;

        for (int i = 0; i <= map.length - 1; i++) {
            for (int j = 0; j <= map[i].length - 1; j++) {
                if (map[i][j] == 'G')
                {
                    gc.drawImage(GRASS, (i) * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
                }
                else if (map[i][j] == 'P')
                {
                    gc.drawImage(PATH, (i) * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
                }
                else if (map[i][j] == 'T')
                {
                    gc.drawImage(TUNNEL, (i) * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
                }
                else if (map[i][j] == 'F')
                {
                    gc.drawImage(GRASS_FLOWER, (i) * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
                }
            }
        }

    }

    /**
     * Sets map squares.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param c A character that defines a specific map attribute.*/
    public static void setMapSquare(double x, double y, char c) {
        map[(int) x][(int) y] = c;
    }
}
