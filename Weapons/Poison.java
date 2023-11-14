package RatGame.Weapons;

import RatGame.Controller;
import RatGame.MapView;
import RatGame.Rat;
import javafx.scene.image.Image;

/**
 * This class is used to add poison to the map that kills the first rat to come in contact with it.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

public class Poison extends Weapons {
    private Image img = new Image("file:Images/PoisonGas.png", 50, 50, true, false);
    private final MapView mapView;

 /**
 * The constructor takes the tickcount from the controller class, the relative x and y location where the poison item has been added and the mapView
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the item has been placed on the map
 * @param y the y axis location where the item has been placed on the map
 * @param mapView the current view of the map
 */
    public Poison(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        update();
    }

 /**
 * This method is used to update the poison. It calls the poisons method to kill a rat and update the image 
 * the rat and then draw the updated rat to the map
 */
    @Override
    public void update() {
        mapView.getGraphicsContext2D().drawImage(img, x * 50, y * 50);
        poisons();
    }

 /**
 * This method is used to kill a rat using the poison. It checks if there is any rat whose x and y location is 
 * the same as the poisons location and if true the rat is killed
 */
    public void poisons(){
        for(Rat rats : Controller.getRatArray()) {
            if (this.x == rats.getGridX() && this.y == rats.getGridY()){
                rats.kill();
                this.isDone = true;

            }
        }
    }
    
}
