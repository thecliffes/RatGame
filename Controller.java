package RatGame;

import RatGame.Rat.Gender;
import RatGame.Weapons.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class handles the main aspects of the beginning of a game.
 * @author James Butler, Ben Cliffe, Mathew Philip Peedikayil, Tom Muoneke
 * @version 1
 */
public class Controller{

	private String name;
	@FXML private MapView mapView;
	@FXML private Label levelLabel;
	@FXML private Label scoreLabel;
    @FXML private VBox weaponsToolbar;
    @FXML private Button playPause;
    @FXML private ProgressBar ratProgress;
    @FXML Button back;
    private static final ArrayList<Rat> ratArray = new ArrayList<>();
    private static ArrayList<RatSpawn> ratSpawn = new ArrayList<>();
    private final ArrayList<Weapons> weaponsArray = new ArrayList<>();
    public static int tickCount;
    Score score = new Score();
    TimerTask tick;
    private boolean paused;
    private WeaponsToolbar bar;

    private static Level selectedLevel;

    public static void setLevel(Level level) {
        selectedLevel = level;
    }

    public static Level getLevel() {
        return selectedLevel;
    }

    /**
     * This method initialises the primary elements of the game. It loads the current level,
     * allows the canvas to interact with objects within bounds and creates the level's first
     * rats.
     * @throws FileNotFoundException Will throw an exception when a file with the
     * specified pathname doesn't exist.
     */
    public void initialize() throws FileNotFoundException {
            mapView.readMap(selectedLevel);
            mapView.setOnDragDropped(event -> {
                // We call this method which is where the bulk of the behaviour takes place.
                canvasDragDroppedOccurred(event);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            });
            mapView.setOnDragOver(event -> {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into our application)
                // Mark the drag event as acceptable by the canvas.
                event.acceptTransferModes(TransferMode.ANY);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            });

            /* gets the rat spawn positions from the level's ratSpawn arraylist */
            ratSpawn = selectedLevel.getRatSpawns();
            for (RatSpawn spawn : ratSpawn) {
                ratArray.add(new Rat(spawn.getxPos(), spawn.getyPos(), mapView));
            }

            bar = new WeaponsToolbar(weaponsToolbar);

            //this.levelLabel.setText("Level:" + selectedLevel.getName());
            this.scoreLabel.setText(String.format("Score: %d", score.getTotalPoints()));
            startTimer();
            tickCount = 0;

    }

    /**
     * This method checks the map coordinates where an item has been dropped, and
     * adds that item to an arraylist to say that the item is currently in use on the map.
     * @param event A drag event containing data regarding the drag.
     */
    public void canvasDragDroppedOccurred(DragEvent event){
        double x = event.getX();
        double y = event.getY();
        Dragboard db = event.getDragboard();

        if (db.hasString()) {
            System.out.println(db.getString() + x  + y);
            switch(db.getString()){
                case "B":
                    System.out.println("its a bomb");
                    Bomb bomb = new Bomb(tickCount, x, y, mapView);
                    weaponsArray.add(bomb);
                    bar.bombUsed();
                    break;
                case "DR":
                    System.out.println("Death Rat");
                    DeathRat deathRat = new DeathRat(tickCount, x, y, mapView);
                    weaponsArray.add(deathRat);
                    bar.deathRatUsed();
                    break;
                case "FSC":
                    System.out.println("FemaleSexChange");
                    FSexChange fSexChange = new FSexChange(tickCount, x, y, mapView);
                    weaponsArray.add(fSexChange);
                    bar.fSexUsed();
                    break;
                case "MSC":
                    System.out.println("MaleSexChange");
                    MSexChange mSexChange = new MSexChange(tickCount, x, y, mapView);
                    weaponsArray.add(mSexChange);
                    bar.mSexUsed();
                    break;
                case "G":
                    System.out.println("Gas");
                    Gas gas = new Gas(tickCount, x, y, mapView);
                    weaponsArray.add(gas);
                    bar.gasUsed();
                    break;
                case "NE":
                    System.out.println("NoEntry");
                    NoEntry noEntry = new NoEntry(tickCount, x, y, mapView);
                    weaponsArray.add(noEntry);
                    bar.noEntryUsed();
                    break;
                case "P":
                    System.out.println("Poison");
                    Poison poison = new Poison(tickCount, x, y, mapView);
                    weaponsArray.add(poison);
                    bar.poisonUsed();
                    break;
                case "S":
                    System.out.println("Sterilisation");
                    Sterilisation sterilisation = new Sterilisation(tickCount, x, y, mapView);
                    weaponsArray.add(sterilisation);
                    bar.sterilisationUsed();
                    break;

                default:
                    System.out.println("No Weapon.");
            }
        } else {
            System.out.println("");
        }
    }

    /**
     * This method is the main timer for length of ticks.
     */
    private void startTimer() {
        tick = new TimerTask() {
            public void run() {
                tickCount += 1;
                mapView.mapUpdate();
                ratUpdate();
                weaponUpdate();
                gameStatus();
                Platform.runLater(() -> scoreLabel.setText(String.format("Score: %d", score.getTotalPoints())));
                getRatSize();

            }
        };

        Timer timer = new Timer("tick");
        timer.schedule(tick, 250, 250);
    }

    /**
     * This method computes if a game is running, and if so it starts the timer.
     * If the game is paused, the timer stops.
     */
    public void playPause() {
        if(paused) {
            playPause.setText("Pause");
            paused = false;
            startTimer();
        }
        else {
            playPause.setText("Play");
            this.tick.cancel();
            paused = true;
        }
    }

    /**
     * This method removes a weapon from the map once that item has been used.
     */
    public void weaponUpdate() {
        for (Weapons weapon : weaponsArray) {
            weapon.update();
        }
        weaponsArray.removeIf(i -> i.getIsDone());
    }

    /**
     * This method deals with the current rats on the map. It updates the current rats array in case of
     * any rats deaths, or births. If when 2 rats meet, none of them is a death rat, the method
     * will call the breeding function if the rats have different genders if the female rat isn't already pregnant.
     * If a rat meets a death rat, death rat kills that rat and the array of rats will be updated.
     */
    public void ratUpdate() {
        ratArray.removeIf(i -> i.getIsDead());
        ArrayList<Rat> bornRats = new ArrayList<>();
        for (Rat rat : ratArray) {
            rat.update(tickCount);
            if( Gender.BABY != rat.getGender() && !rat.getIsPregnant()) {
                for (Rat rat2 : ratArray) {
                    if (rat.getGridX() == rat2.getGridX() && rat.getGridY() == rat2.getGridY() && rat2.getGender() != Gender.BABY && !rat2.getIsPregnant() && rat2.getGender() != rat.getGender()) {
                        rat.breed(rat2);
                    }
                    if (rat.getGridX() == rat2.getGridX() && rat.getGridY() == rat2.getGridY() && (rat.getGender() == Gender.EVIL || rat2.getGender() == Gender.EVIL)) {
                        if(rat.getGender() != Gender.EVIL){
                            rat.kill();
                        }
                        if(rat2.getGender() != Gender.EVIL){
                            rat2.kill();
                        }
                    }
                }
            } else if (rat.birth()) {
                bornRats.add(new Rat(rat.getGridX(), rat.getGridY(), mapView));
            }
        }
        ratArray.addAll(bornRats);
    }


    /**
     * This method returns an array that keeps track of the current rats on
     * the map.
     * @return An array of rats.
     */
    public static List<Rat> getRatArray() {
        return ratArray;
    }

    /**
     * This method computes whether the player won or lost the game.
     */
    public void gameStatus(){
        if (ratArray.size() == 0){
            System.out.println("Game won!!!");
            System.exit(1);
        } else if (ratArray.size() >= 50) {
            System.out.println("Game over!!!");
            System.exit(1);
        }

    }

    /**
     * This method gets the size of the rat array and displays it on the progress bar.
     */
    public void getRatSize(){
        ratProgress.setProgress((double) ratArray.size() / 50);
    }

    /**
     * Returns to the previous javaFX window.
     * @param actionEvent
     */
    public void runReturn(javafx.event.ActionEvent actionEvent) {
      Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            stage.setTitle("Rats");
            loader.getController();
            loader.getController();
            stage.getIcons().add(new Image("file:Images/logo.png"));
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            root.requestFocus();
        } catch (IOException e) {
            System.out.println("test");
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }
}
