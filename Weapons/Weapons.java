package RatGame.Weapons;

/**
 * This class is used as the weapons superclass.
 * @author James Butler
 * @version 1
 */

public class Weapons {
    double x;
    double y;
    int tickCreated;
    boolean isDone = false;

/**
* Abstract method to be implemented by the individual weapons classes
*/
    public void update(){
      // Empty so it can be implemented by each class.
    }

    protected double roundToGrid(double x) {
        x = Math.floor(x);
        x = (x - (x%50))/50;
        return x;
    }

    /**
    * Checks if a weapon has completed its actions 
    */
    public boolean getIsDone() {
        return isDone;
    }
}
