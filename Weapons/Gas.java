package RatGame.Weapons;

import java.util.ArrayList;

import RatGame.Controller;
import RatGame.MapView;
import RatGame.Rat;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * This class is used to kill rats using the gas weapon.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

//didnt not get round to completing 
public class Gas extends Weapons {
    private Image img = new Image("file:Images/Gas.png", 50, 50, true, false);
    private final MapView mapView;
    private ArrayList<Point2D> infectedGrid = new ArrayList<>();

/**
 * The constructor takes the tickcount from the controller class, the relative x and y location where the gas item has been added and the mapView
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the item has been placed on the map
 * @param y the y axis location where the item has been placed on the map
 * @param mapView the current view of the map
 */
    
    public Gas(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        infectedGrid.add(new Point2D(this.x, this.y));
        tickCreated = Controller.tickCount;
        update();
    }

    @Override
    public void update() {
        for (Point2D p : infectedGrid) {
            mapView.getGraphicsContext2D().drawImage(img, p.getX()*50, p.getY()*50);
            
        }
        
    }

}
