package RatGame.Weapons;

import RatGame.Controller;
import RatGame.MapView;
import RatGame.Rat;
import javafx.scene.image.Image;

/**
 * This class is used to add a no entry sign to the map that blocks a rats path.
 * The class inherits from the Weapons class
 * @author James Butler
 * @version 1
 */

public class NoEntry extends Weapons {
    private Image signImage = new Image("file:Images/NoEntrySign.png", 50, 50, true, false);
    private Image sign4 = new Image("file:Images/StopSignImages/sign4.png", 50, 50, true, false);
    private Image sign3 = new Image("file:Images/StopSignImages/sign3.png", 50, 50, true, false);
    private Image sign2 = new Image("file:Images/StopSignImages/sign2.png", 50, 50, true, false);
    private Image sign1 = new Image("file:Images/StopSignImages/sign1.png", 50, 50, true, false);
    private final MapView mapView;
    private int signHealth = 5;

 /**
 * The constructor takes the tickcount from the controller class, the relative x and y location where the noEntry item has been added and the mapView
 * @param tickCount the tick count from the controller class
 * @param x the x axis location where the item has been placed on the map
 * @param y the y axis location where the item has been placed on the map
 * @param mapView the current view of the map
 * if the no entry sign is placed on a path, the path will now act like grass and block access to rats
 */
    public NoEntry(int tickCount, double x, double y, MapView mapView) {
        this.tickCreated = tickCount;
        this.x = roundToGrid(x);
        this.y = roundToGrid(y);
        this.mapView = mapView;
        System.out.println(this.x + "," + this.y);
        if (mapView.getMap()[(int) this.x][(int) this.y] == 'P') {
            MapView.setMapSquare(this.x, this.y, 'G');
        } else {
            isDone = true;
        }
        update();
    }

 /**
 * This method is used to update the no entry sign. The sign health indicates how many times the sign has been
 * hit by a rat. After each hit the sign health decrements by one and more damage is shown on the sign image.
 * After 5 hits the sign breaks and disappears and rats are now able to pass through the path.
 */
    @Override
    public void update() {
        mapView.getGraphicsContext2D().drawImage(new Image("file:Images/Path.png", 50, 50, false, false), 50*x, 50*y);
        mapView.getGraphicsContext2D().drawImage(signImage, x * 50, y * 50);
        for (Rat rats : Controller.getRatArray()) {
            if (this.x == rats.getGridX()+1 && this.y == rats.getGridY()){
                signHealth = signHealth -1;
            } else if (this.x == rats.getGridX()-1 && this.y == rats.getGridY()){
                signHealth = signHealth -1; 
            } else if (this.x == rats.getGridX() && this.y == rats.getGridY()+1){
                    signHealth = signHealth -1;
            } else if (this.x == rats.getGridX() && this.y == rats.getGridY()-1){
                signHealth = signHealth -1;
            }
            
            if (signHealth == 4) {
                mapView.getGraphicsContext2D().drawImage(sign4, x * 50, y * 50);

            } else if (signHealth == 3) {
                mapView.getGraphicsContext2D().drawImage(sign3, x * 50, y * 50);

            } else if (signHealth == 2) {
                mapView.getGraphicsContext2D().drawImage(sign2, x * 50, y * 50);
                
            } else if (signHealth == 1) {
                mapView.getGraphicsContext2D().drawImage(sign1, x * 50, y * 50);
                
            }else if (signHealth <= 0) {
                isDone = true;
                MapView.setMapSquare(this.x, this.y, 'P');
            }
        }
    }

}
