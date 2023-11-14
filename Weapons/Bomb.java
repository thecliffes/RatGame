package RatGame.Weapons;

import RatGame.Controller;
import RatGame.MapView;
import RatGame.Rat;
import javafx.scene.image.Image;

// TODO: Destroy other items in path;

/**
 * This class creates a bomb weapon which counts down and explodes after 5 seconds.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

public class Bomb extends Weapons {
    private Image bomb5 = new Image("file:Images/BombImages/Bomb5.png", 50, 50, true, false);
    private Image bomb4 = new Image("file:Images/BombImages/Bomb4.png", 50, 50, true, false);
    private Image bomb3 = new Image("file:Images/BombImages/Bomb3.png", 50, 50, true, false);
    private Image bomb2 = new Image("file:Images/BombImages/Bomb2.png", 50, 50, true, false);
    private Image bomb1 = new Image("file:Images/BombImages/Bomb1.png", 50, 50, true, false);
    private Image explosion = new Image("file:Images/BombImages/Explosion.png", 50, 50, true, false);
    private final MapView mapView;

 /**
 * The constructor takes in the tickCount from the controller, the relative grid location of the bomb and the map
 * @param tickCount the tickcount from the controller used to make the bomb countdown
 * @param x the x axis reference of where the bomb has been placed
 * @param y the y axis reference of where the bomb has been placed
 * @param mapView the mapView of where the bomb has been placed
 */ 
    public Bomb(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        update();
    }

 /**
 * This method is used to make the bomb count down from 5 to 1 based on the tickCount from the Controller
 */
    @Override
    public void update() {
        if (Controller.tickCount < tickCreated + 4){
            mapView.getGraphicsContext2D().drawImage(bomb5, x * 50, y * 50);
        } else if (Controller.tickCount < tickCreated + 8){
            mapView.getGraphicsContext2D().drawImage(bomb4, x * 50, y * 50);
        } else if (Controller.tickCount < tickCreated + 10){
            mapView.getGraphicsContext2D().drawImage(bomb3, x * 50, y * 50);
        } else if (Controller.tickCount < tickCreated + 12){
            mapView.getGraphicsContext2D().drawImage(bomb2, x * 50, y * 50);
        } else if (Controller.tickCount < tickCreated + 16){
            mapView.getGraphicsContext2D().drawImage(bomb1, x * 50, y * 50);
        } else {
            mapView.getGraphicsContext2D().drawImage(bomb1, x * 50, y * 50);
            explode();
        }
    }

 /**
 *This method is used to make the bomb weapon explode. It makes the bomb explode within a given radius and kills
 *all rats within this radius using the ratArray in the Controller.
 */
    public void explode() {
        int maxY = (int) y;
        int minY = (int) y;
        int maxX = (int) x;
        int minX = (int) x;
        if(mapView.getMap()[(int) x][(int) y] == 'P') {

            for(int i = 0; i <= mapView.getMap().length - x; i++) {
                if ('G' == mapView.getMap()[(int) x + i][(int) y]) {
                    maxX = (int) x + i - 1;
                    break;
                }
            }
            for(int i = 0; i <= x; i++) {
                if ('G' == mapView.getMap()[(int) x - i][(int) y]) {
                    minX = (int) x - i +1 ;
                    break;
                }
            }
            for(int i = 0; i <= mapView.getMap()[0].length - y; i++) {
                if ('G' == mapView.getMap()[(int) x][(int) y + i]) {
                    maxY = (int) y + i -1 ;
                    break;
                }
            }
            for(int i = 0; i <= y; i++) {
                if ('G' == mapView.getMap()[(int) x][(int) y - i]) {
                    minY = (int) y - i + 1;
                    System.out.println("minY: " + minY);
                    break;
                }
            }
            System.out.println(maxY + "," + minY);
            
            System.out.println(maxX + "," + minX);

            
            for(Rat rats : Controller.getRatArray()) {
                boolean rowCheck = (rats.getGridX() == x && rats.getGridY() <= maxY && rats.getGridY() <= minY);
                boolean colCheck = (rats.getGridY() == y && rats.getGridX() <= maxX && minX <= rats.getGridX());
                if(rowCheck || colCheck ) {
                    mapView.getGraphicsContext2D().drawImage(explosion, rats.getGridX()*50, rats.getGridY()*50);
                    rats.kill();
                }
            }

        } else {
            System.out.println("Didn't place it on the a path.");
        }
        this.isDone = true;
    }
}
