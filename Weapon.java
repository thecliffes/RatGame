package RatGame;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class handles all the weapons available in the game.
 * @author Vuyelwa Mazingi
 * @version 1
 * */
public class Weapon{
	private Image bombImage = new Image("file:Bomb.png");
	private Image deathRatImage;
	private Image femaleSexChangeImage = new Image("file:FemaleSexChange.png");
	private Image maleSexChangeImage;
	private Image gasImage;
	private Image noEntrySignImage;
	private Image poisonImage;
	private Image sterilisationImage;
	private Image currentImage;
	private Canvas canvas;
	private static final int CANVAS_WIDTH = 600;
	private static final int CANVAS_HEIGHT = 400;

	/**
	 * React when an object is dragged onto the canvas. 
	 * @param event The drag event itself which contains data about the drag that occurred.
	 */
	public void canvasDragDroppedOccured(DragEvent event) {
    	double x = event.getX();
        double y = event.getY();
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.drawImage(currentImage, x - currentImage.getWidth() / 2.0, y - currentImage.getHeight() / 2.0);
	}
	
	/**
	 * Create the GUI.
	 * @return The panel that contains the created GUI.
	 */
	private Pane buildGUI() {
		BorderPane root = new BorderPane();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		HBox toolbar = new HBox();
		toolbar.setSpacing(10);
		toolbar.setPadding(new Insets(10, 10, 10, 10)); 
		root.setTop(toolbar);

        ImageView dragBomb = new ImageView();
        dragBomb.setImage(bombImage);
        toolbar.getChildren().add(dragBomb);
        
        ImageView dragDeathRat = new ImageView();
        dragDeathRat.setImage(deathRatImage);
        toolbar.getChildren().add(dragDeathRat);
        
        ImageView dragFSexChange = new ImageView();
        dragFSexChange.setImage(femaleSexChangeImage);
        toolbar.getChildren().add(dragFSexChange);
        
        ImageView dragMSexChange = new ImageView();
        dragMSexChange.setImage(maleSexChangeImage);
        toolbar.getChildren().add(dragMSexChange);
        
        ImageView dragGas = new ImageView();
        dragGas.setImage(gasImage);
        toolbar.getChildren().add(dragGas);
        
        ImageView dragNoEntry = new ImageView();
        dragNoEntry.setImage(noEntrySignImage);
        toolbar.getChildren().add(dragNoEntry);
        
        ImageView dragPoison = new ImageView();
        dragPoison.setImage(poisonImage);
        toolbar.getChildren().add(dragPoison);
        
        ImageView dragSterilisation = new ImageView();
        dragSterilisation.setImage(sterilisationImage);
        toolbar.getChildren().add(dragSterilisation);
        
        dragBomb.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragBomb.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(bombImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
		
        dragDeathRat.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragDeathRat.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(deathRatImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragFSexChange.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragFSexChange.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(femaleSexChangeImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragMSexChange.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragMSexChange.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(maleSexChangeImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragGas.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragGas.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(gasImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragNoEntry.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragNoEntry.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(noEntrySignImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragPoison.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragPoison.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(poisonImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        dragSterilisation.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	Dragboard db = dragSterilisation.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(sterilisationImage);
                db.setContent(content);
                currentImage = db.getImage();
           		event.consume();
		    }
		});
        
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
            	if (event.getGestureSource() == dragBomb || event.getGestureSource() == dragDeathRat || 
            			event.getGestureSource() == dragFSexChange || event.getGestureSource() == dragMSexChange || 
            			event.getGestureSource() == dragGas || event.getGestureSource() == dragNoEntry || event.getGestureSource() == dragPoison || 
            			event.getGestureSource() == dragSterilisation) {
            		event.acceptTransferModes(TransferMode.ANY);
            		event.consume();
            	}
            }
        });
        
        canvas.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {                
            	canvasDragDroppedOccured(event);
            	event.consume();
             }
        });
         return root;
	}
	        
}
