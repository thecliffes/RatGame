package RatGame.Weapons;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle.Control;

import RatGame.Controller;
import RatGame.MapView;
import javafx.scene.image.Image;
import RatGame.Rat;

/**
 * This class creates a death rat weapon which kills the first 5 rats it comes in contact with.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

public class DeathRat extends Weapons {
    private Image img = new Image("file:Images/DeathRatUp.png", 50, 50, false, false);
    private final MapView mapView;
    Random generator = new Random();
    private Direction direction;
    private int killed = 0;

    enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

 /**
 * This constructor of the death rat uses the tick count from the controller, the map view and grid location of the deathRat.
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the death rat has been placed
 * @param y the y axis location where the death rat has been placed
 * @param mapView the map where the death rat has been placed
 */
    public DeathRat(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        isDone = false;
        update();
    }

 /**
 * This method is used to update the death rat.
 * Depending on the state of the tickcount it will call the moveRat method to move the rat in the map, or
 * it will call the kills method to kill a rat and then draw the new state of the death rat
 */
    @Override
    public void update() {
        if(Controller.tickCount > tickCreated + 5) {
            moveRat();
        }
        kills();
        drawRat();
    }

 /**
 * This method is used to move the death rat in the Map by indicating the direction of the rats movement.
 * It uses the tick count from the controller class to simulate continuous movement
 */
    public void moveRat(){
        if(Controller.tickCount % 2 == 0) {
            return;
        }
        ArrayList<Direction> validDirections = new ArrayList<>();
        if (MapView.getSquare(this.x, this.y - 1) != 'G') {
            validDirections.add(Direction.UP);
        }
        if (MapView.getSquare(this.x, this.y + 1) != 'G') {
            validDirections.add(Direction.DOWN);
        }
        if (MapView.getSquare(this.x + 1, this.y) != 'G') {
            validDirections.add(Direction.LEFT);
        }
        if (MapView.getSquare(this.x - 1, this.y) != 'G') {
            validDirections.add(Direction.RIGHT);
        }

        if (validDirections.size() > 1) {
            try {
                validDirections.remove(Direction.values()[((this.direction.ordinal() + 2) % 4)]);
            } catch (NullPointerException e) {
                System.out.print("");
            }
        }

        int randomNum = generator.nextInt(validDirections.size());
        this.direction = validDirections.get(randomNum);
        if (this.direction == Direction.LEFT) {
            x += 1;
        } else if (this.direction == Direction.RIGHT) {
            x -= 1;
        } else if (this.direction == Direction.DOWN) {
            y += 1;
        } else if (this.direction == Direction.UP) {
            y -= 1;
        }
    }

 /**
 * This method is used to draw the death rat in the correct orientation.
 * The image used to represent the death rat changes according to the direction it will be facing
 */
    public void drawRat(){
        if (this.direction == Direction.UP) {
            img = new Image("file:Images/DeathRatUp.png", 50, 50, false, false);
        } else if (this.direction == Direction.DOWN) {
            img = new Image("file:Images/DeathRatDown.png", 50, 50, false, false);
        } else if (this.direction == Direction.LEFT) {
            img = new Image("file:Images/DeathRatRight.png", 50, 50, false, false);
        } else if (this.direction == Direction.RIGHT) {
            img = new Image("file:Images/DeathRatLeft.png", 50, 50, false, false);
        }
        mapView.getGraphicsContext2D().drawImage(img, x * 50, y * 50);
    }

 /**
 * This method is used to kill rats the death rat comes into contact with.
 * The method uses the rat array from the controller class and if the x and y reference of the rat is the same
 * as that of the death rat then it is killed
 */
    public void kills(){
        for(Rat rats : Controller.getRatArray()) {
            if (this.x == rats.getGridX() && this.y == rats.getGridY()){
                rats.kill();
                killed += 1;
            }
            if(killed >= 5) {
                isDone = true;
                break;
            }
        }
    }
}
