package RatGame.Weapons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

/**
 * This class is used to display the weapon images for each weapon and allow them to be dragged and dropped
 * Each weapon can only be used 4 times
 * @author James Butler
 * @version 1
 */


public class WeaponsToolbar {

    private Image bombImage = new Image("file:Images/Bomb.png");
    private Image deathRatImage = new Image("file:Images/deathRatRight.png");
    private Image femaleSexChangeImage = new Image("file:Images/femaleSexChange.png");
    private Image maleSexChangeImage = new Image("file:Images/maleSexChange.png");
    private Image gasImage = new Image("file:Images/Gas.png");
    private Image noEntrySignImage = new Image("file:Images/NoEntrySign.png");
    private Image poisonImage = new Image("file:Images/PoisonGas.png", 70, 70, true, false);
    private Image sterilisationImage = new Image("file:Images/Sterilisation.png");
    private VBox bar;
    private int bombsUsed = 0;
    private int deathRatUsed = 0;
    private int fSexUsed = 0;
    private int mSexUsed = 0;
    private int gasUsed = 0;
    private int noEntryUsed = 0;
    private int poisonUsed = 0;
    private int sterilisationUsed = 0;


    public WeaponsToolbar(VBox bar) {
        this.bar = bar;
        buildGUI();
    }
    
   /**
    * This method builds the GUI and enables the drag drop functionality
    */
    private void  buildGUI() {
        ImageView dragBomb = new ImageView();
        dragBomb.setImage(bombImage);
        this.bar.getChildren().add(dragBomb);

        dragBomb.setOnDragDetected(event -> {
            Dragboard db = dragBomb.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(bombImage);
            content.putString("B");
            db.setContent(content);
            event.consume();
        });

        ImageView dragDeathRat = new ImageView();
        dragDeathRat.setImage(deathRatImage);
        this.bar.getChildren().add(dragDeathRat);

        dragDeathRat.setOnDragDetected(event -> {
            Dragboard db = dragDeathRat.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(deathRatImage);
            content.putString("DR");
            db.setContent(content);
               event.consume();
        });

        ImageView dragFSexChange = new ImageView();
        dragFSexChange.setImage(femaleSexChangeImage);
        this.bar.getChildren().add(dragFSexChange);

        dragFSexChange.setOnDragDetected(event -> {
            Dragboard db = dragFSexChange.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(femaleSexChangeImage);
            content.putString("FSC");
            db.setContent(content);
               event.consume();
        });

        ImageView dragMSexChange = new ImageView();
        dragMSexChange.setImage(maleSexChangeImage);
        this.bar.getChildren().add(dragMSexChange);

        dragMSexChange.setOnDragDetected(event -> {
            Dragboard db = dragMSexChange.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(maleSexChangeImage);
            content.putString("MSC");
            db.setContent(content);
               event.consume();
        });

        ImageView dragGas = new ImageView();
        dragGas.setImage(gasImage);
        this.bar.getChildren().add(dragGas);

        dragGas.setOnDragDetected(event -> {
            Dragboard db = dragGas.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(gasImage);
            content.putString("G");
            db.setContent(content);
               event.consume();
        });

        ImageView dragNoEntry = new ImageView();
        dragNoEntry.setImage(noEntrySignImage);
        this.bar.getChildren().add(dragNoEntry);

        dragNoEntry.setOnDragDetected(event -> {
            Dragboard db = dragNoEntry.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(noEntrySignImage);
            content.putString("NE");
            db.setContent(content);
               event.consume();
        });

        ImageView dragPoison = new ImageView();
        dragPoison.setImage(poisonImage);
        this.bar.getChildren().add(dragPoison);

        dragPoison.setOnDragDetected(event -> {
            Dragboard db = dragPoison.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(poisonImage);
            content.putString("P");
            db.setContent(content);
               event.consume();
        });

        ImageView dragSterilisation = new ImageView();
        dragSterilisation.setImage(sterilisationImage);
        this.bar.getChildren().add(dragSterilisation);

        dragSterilisation.setOnDragDetected(event -> {
            Dragboard db = dragSterilisation.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(sterilisationImage);
            content.putString("S");
            db.setContent(content);
               event.consume();
        });
    }

    /**
    * This method checks the usage count of the bomb weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void bombUsed() {
        bombsUsed += 1;
        if (bombsUsed >= 4) {
            this.bar.getChildren().remove(4);
        }
    }

   /**
    * This method checks the usage count of the death rat weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void deathRatUsed() {
        deathRatUsed += 1;
        if (deathRatUsed >= 4) {
            this.bar.getChildren().remove(5);
        }
    }

   /**
    * This method checks the usage count of the female sex change weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void fSexUsed() {
        fSexUsed += 1;
        if (fSexUsed >= 4) {
            this.bar.getChildren().remove(6);
        }
    }

   /**
    * This method checks the usage count of the male sex change weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void mSexUsed() {
        mSexUsed += 1;
        if (mSexUsed >= 4) {
            this.bar.getChildren().remove(7);
        }
    }

   /**
    * This method checks the usage count of the gas weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void gasUsed() {
        gasUsed += 1;
        if (gasUsed >= 4) {
            this.bar.getChildren().remove(8);
        }
    }

   /**
    * This method checks the usage count of the no entry sign weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void noEntryUsed() {
        noEntryUsed += 1;
        if (noEntryUsed >= 4) {
            this.bar.getChildren().remove(9);
        }
    }

   /**
    * This method checks the usage count of the poison weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void poisonUsed() {
        poisonUsed += 1;
        if (poisonUsed >= 4) {
            this.bar.getChildren().remove(10);
        }
    }

   /**
    * This method checks the usage count of the sterilisation weapon. If it has been used 4 times it is removed from
    * the weapon toolbar
    */
    public void sterilisationUsed() {
        sterilisationUsed += 1;
        if (sterilisationUsed >= 4) {
            this.bar.getChildren().remove(11);
        }
    }

    
}
