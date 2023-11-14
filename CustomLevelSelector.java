package RatGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * JavaFX menu to select a custom level.
 * @author James Watts
 */
public class CustomLevelSelector {
    @FXML Button btnLevel1;
    @FXML Button btnLevel2;
    @FXML Button btnBack;
    @FXML ComboBox<String> comLevels;

    Level selectedLevel;

    final String LEVELS_PATH = "CustomLevels/";

    /**
     * Initializes the combo-box and fills with all files in the custom-levels directory
     */
    public void initialize() {
        try {
            String[] files = new File(LEVELS_PATH).list();
            comLevels.getItems().setAll(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Passes selected custom level to the controller class which handles the game.
     */
    public void runCustomLevel(ActionEvent event){
        Stage stage = (Stage) btnLevel1.getScene().getWindow();
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
            Controller.setLevel(LevelReader.getLevel("CustomLevels/", (String) comLevels.getValue()));
            root.requestFocus();
        } catch (IOException e) {
            System.out.println("test");
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Passes level 1 to the controller class which handles the game.
     */
    public void runLevel1(ActionEvent event){
        Stage stage = (Stage) btnLevel1.getScene().getWindow();
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
            Controller.setLevel(LevelReader.getLevel("levels/", "lvl1.txt"));
            root.requestFocus();
        } catch (IOException e) {
            System.out.println("test");
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Passes level 2 to the controller class which handles the game.
     * @param event
     */
    public void runLevel2(ActionEvent event){
        Stage stage = (Stage) btnLevel2.getScene().getWindow();
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
            Controller.setLevel(LevelReader.getLevel("levels/", "lvl2.txt"));
            root.requestFocus();
        } catch (IOException e) {
            System.out.println("test");
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns to the previous javaFX window.
     * @param actionEvent
     */
    public void runReturn(ActionEvent actionEvent) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
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

    /**
     * <p> Calls the method in LevelWriter to delete the custom level selected
     * in the combo-box </p>
     * @throws FileNotFoundException if the file in question doesn't exist.
     */
    public void deleteCustomLevel(ActionEvent actionEvent) throws FileNotFoundException {
        LevelWriter.deleteFile("CustomLevels/"
                + (String) comLevels.getValue());
        String[] files = new File(LEVELS_PATH).list();
        comLevels.getItems().setAll(files);
        comLevels.setValue(comLevels.getItems().get(0));
    }
}
