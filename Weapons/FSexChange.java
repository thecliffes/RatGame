package RatGame.Weapons;

import RatGame.Controller;
import RatGame.MapView;
import RatGame.Rat;
import javafx.scene.image.Image;

/**
 * This class is used to change the sex of a rat to female.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

public class FSexChange extends Weapons {
    private Image img = new Image("file:Images/femaleSexChange.png", 50, 50, true, false);
    private final MapView mapView;

 /**
 * The constructor takes the tickcount from the controller class, the relative x and y location where the fsexchange item has been added and the mapView
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the item has been placed on the map
 * @param y the y axis location where the item has been placed on the map
 * @param mapView the current view of the map
 */
    public FSexChange(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        isDone = false;
        update();
    }

 /**
 * This method is used to update the rat. It calls the FemaleChange method to change the sex of 
 * the rat and then draw the updated rat to the map
 */
    @Override
    public void update() {
        FemaleChange();
        mapView.getGraphicsContext2D().drawImage(img, x*50, y*50);
    }

 /**
 * This method changes the sex of a rat to female. If the rat is already a female rat there is no effect.
 * This is done by getting the grid reference of the rat and checking if its gender is female.
 */
    public void FemaleChange(){
        for(Rat rats : Controller.getRatArray()) {
            if (this.x == rats.getGridX() && this.y == rats.getGridY() && rats.getGender() == Rat.Gender.FEMALE){
                rats.setGender(Rat.Gender.MALE);
                this.isDone = true;

            }
        }
    }
}
