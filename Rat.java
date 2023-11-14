package RatGame;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class models the behaviours of all kinds of rats in the game.
 * @author Ben Cliffe
 * @version 1
 * */
public class Rat {
    private Image image;
    private double gridX;
    private double gridY;
    private boolean isPregnant = false;
    private boolean pregnantTimer = false;
    private Direction direction;
    private final MapView mapView;
    Random generator = new Random();
    private final int id = hashCode();
    private Gender gender = Gender.BABY;
    private boolean breeding = false;
    private int breedingStartedTick;
    private int tickCreated = 0;
    private boolean isDead = false;
    private int nBabies = 0;

    /**
     * Different possible kinds of rats in the game.
     */
    public enum Gender {
        MALE, FEMALE, BABY, EVIL
    }

    /**
     * Possible directions a rat can move.
     * */
    enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

    /**
     * Creates a new rat object on the game map.
     * @param x The x coordinate where the rat is going to appear.
     * @param y The y coordinate where the rat is going to appear.
     * @param mapView The current map of the game where the rat is going to appear.
     * */
    public Rat(double x, double y, MapView mapView) {
        gridX = x;
        gridY = y;
        this.mapView = mapView;
        this.tickCreated = Controller.tickCount;
    }

    /**
     * This method is used to render rats onto the game map.
     */
    public void drawRat() {
        if (gender == Gender.MALE) {
            if (this.direction == Rat.Direction.UP) {
                image = new Image("file:Images/RatImages/MaleUp.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.DOWN) {
                image = new Image("file:Images/RatImages/MaleDown.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.LEFT) {
                image = new Image("file:Images/RatImages/MaleRight.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.RIGHT) {
                image = new Image("file:Images/RatImages/MaleLeft.png", 50, 50, false, false);
            }
        } else if (gender == Gender.FEMALE) {
            if (this.direction == Rat.Direction.UP) {
                image = new Image("file:Images/RatImages/FemaleUp.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.DOWN) {
                image = new Image("file:Images/RatImages/FemaleDown.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.LEFT) {
                image = new Image("file:Images/RatImages/FemaleRight.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.RIGHT) {
                image = new Image("file:Images/RatImages/FemaleLeft.png", 50, 50, false, false);
            }

        } else if(gender == Gender.BABY) {
            if (this.direction == Rat.Direction.UP) {
                image = new Image("file:Images/RatImages/BabyUp.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.DOWN) {
                image = new Image("file:Images/RatImages/BabyDown.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.LEFT) {
                image = new Image("file:Images/RatImages/BabyRight.png", 50, 50, false, false);
            } else if (this.direction == Rat.Direction.RIGHT) {
                image = new Image("file:Images/RatImages/BabyLeft.png", 50, 50, false, false);
            }
        } 
        mapView.getGraphicsContext2D().drawImage(image, gridX * 50, gridY * 50);
    }

    /**
     * This function is called when it is required for rats to breed.
     * @param rat A rat.
     */
    public void breed(Rat rat) {
        if(!breeding && !rat.isBreeding()) {
            this.isBreeding(true);
            rat.isBreeding(true);
            if (this.gender == Gender.FEMALE) {
                this.setIsPregnant(true);
            }  else {
                rat.setIsPregnant(true);
            }
        }
    }


    /**
     * This method is called to kill rats and updates the player's score.
     * Point increase by 10 for each rat killed, and by 20 for each pregnant rat.*/
    public void kill() {
        if (isPregnant) {
            Score.addPoints(20);
        } else {
            Score.addPoints(10);
        }
        
        this.isDead = true;
    }

    /**
     * Moves rat around the canvas.
     */
    public void ratMove() {

        if (!breeding) {
            moveRat();
            drawRat();
        } else{
            drawRat();
        }
    }

    /**
     * This method considers a rat's current tick and updates it to new stages.
     * @param currentTick Current tick
     * */
    public void update(int currentTick) {
        if(gender == Gender.BABY && currentTick >= tickCreated + 15) {
            setGender();
        }
        if(gender == Gender.BABY) {
            moveRat();
        } else if (breeding && currentTick >= breedingStartedTick + 15) {
            breeding = false;
            moveRat();
        } else if (currentTick % 2 == 0 && !breeding) {
            moveRat();
        }

        drawRat();
    }

    /**
     * This method makes rats move around random but legal positions on the map.
     * */
    public void moveRat(){
        ArrayList<Direction> validDirections = new ArrayList<>();
        if (MapView.getSquare(this.gridX, this.gridY - 1) != 'G'
                && MapView.getSquare(this.gridX, this.gridY - 1) != 'F') {
            validDirections.add(Rat.Direction.UP);
        }
        if (MapView.getSquare(this.gridX, this.gridY + 1) != 'G'
                && MapView.getSquare(this.gridX, this.gridY + 1) != 'F') {
            validDirections.add(Rat.Direction.DOWN);
        }
        if (MapView.getSquare(this.gridX + 1, this.gridY) != 'G'
                && MapView.getSquare(this.gridX + 1, this.gridY) != 'F') {
            validDirections.add(Rat.Direction.LEFT);
        }
        if (MapView.getSquare(this.gridX - 1, this.gridY) != 'G'
                && MapView.getSquare(this.gridX - 1, this.gridY) != 'F') {
            validDirections.add(Rat.Direction.RIGHT);
        }

        if (validDirections.size() > 1) {
            try {
                validDirections.remove(Rat.Direction.values()[((this.direction.ordinal() + 2) % 4)]);
            } catch (NullPointerException e) {
                System.out.print("");
            }
        }

        int randomNum = generator.nextInt(validDirections.size());
        this.direction = validDirections.get(randomNum);
        if (this.direction == Rat.Direction.LEFT) {
            this.setGridX(this.getGridX() + 1);
        } else if (this.direction == Rat.Direction.RIGHT) {
            this.setGridX(this.getGridX() - 1);
        } else if (this.direction == Rat.Direction.DOWN) {
            this.setGridY(this.getGridY() + 1);
        } else if (this.direction == Rat.Direction.UP) {
            this.setGridY(this.getGridY() - 1);
        }
    }

    /**
     * This method is called when a rat births baby rats.
     * @return true if there is a birth.
     **/
    public boolean birth() {
        if (Controller.tickCount > breedingStartedTick + 30 && Controller.tickCount % 2 == 0 && isPregnant) {
                nBabies -= 1;
                if (nBabies == 0) {
                    isPregnant = false;
                }
                return true;
            }
            return false;
    }

    /**
     * This method gets a rat's X coordinate.
     * @return The X coordinate.
     */
    public double getGridX() {
        return gridX;
    }

    /**
     * This method sets a rat's X coordinate.
     * @param d A value for the X coordinate.
     */
    public void setGridX(double d) {
        this.gridX = d;
    }

    /**
     * This method gets a rat's Y coordinate.
     * @return The Y coordinate.
     */
    public double getGridY() {
        return gridY;
    }

    /**
     * This method sets a rat's Y coordinate.
     * @param d A value for the Y coordinate.
     */
    public void setGridY(double d) {
        this.gridY = d;
    }

    /**
     * This method checks if a rat is pregnant.
     * @return A boolean value (True if a rat is pregnant).
     */
    public boolean getIsPregnant() {
        return this.isPregnant;
    }

    /**
     * If a rat is pregnant this method generates a random number of babies
     * and sets a rat to pregnant.
     * @param isPregnant A boolean value (True if a rat is pregnant).
     */
    public void setIsPregnant(boolean isPregnant) {
        nBabies = generator.nextInt(2) + 1;
        this.isPregnant = isPregnant;
    }

    /**
     * Gets rat Id.
     * @return A rats Id.
     */
    public int getId() {
        return id;
    }

    /**
     * This method checks if a timer on a pregnant rat is running.
     * @return A boolean value (True if a timer is running).
     */
    public boolean isPregnantTimer() {
        return pregnantTimer;
    }

    /**
     * This method sets a timer for pregnant rats.
     * @param pregnantTimer A boolean value (True is a timer is running).
     */
    public void setPregnantTimer(boolean pregnantTimer) {
        this.pregnantTimer = pregnantTimer;
    }

    /**
     * This method checks if a rat is breeding.
     * @return A boolean value (True if a rat is breeding).
     */
    public boolean isBreeding() {
        return breeding;
    }

    /**
     * This method checks a breeding timer to see if the rats are currently breeding.
     * @param b A boolean value (True if rats are breeding).
     * @return True if rats are breeding.
     */
    public boolean isBreeding(boolean b) {
        breedingStartedTick = Controller.tickCount;
        breeding = b;
        return b;
    }

    /**
     * This method gets a rat's gender.
     * @return A rat's gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * This method sets a random generated gender to a rat.
     */
    public void setGender() {
        this.gender = Gender.values()[generator.nextInt(2)];
    }

    /**
     * This method sets a rat's gender.
     * @param g A gender.
     */
    public void setGender(Gender g) {
        this.gender = g;
    }

    /**
     * Checks if the rat is dead.
     * @return True if the rat is dead.
     */
    public boolean getIsDead() {
        return isDead;
    }

}
