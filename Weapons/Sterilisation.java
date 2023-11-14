package RatGame.Weapons;

import RatGame.MapView;
import javafx.scene.image.Image;

/**
 * This class is used to make a rat sterile.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

//didnt not get round to completing 
public class Sterilisation extends Weapons {
    private Image img = new Image("file:Images/Sterilisation.png", 50, 50, true, false);
    private final MapView mapView;

 /**
 * The constructor takes the tickcount from the controller class, the relative x and y location where the sterilisation item has been added and the mapView
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the item has been placed on the map
 * @param y the y axis location where the item has been placed on the map
 * @param mapView the current view of the map
 */
    public Sterilisation(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        isDone = false;
        update();
    }

    @Override
    public void update() {
        mapView.getGraphicsContext2D().drawImage(img, x * 50, y * 50);
    }

}
