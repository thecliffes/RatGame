package RatGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for writing the rats in a game to a file.
 * @deprecated
 */
public class RatWriter {
    /**
     * Static method to write the rats to a file.
     * @param rats the arraylist of rats to write out.
     * @param ratFile the path to the file.
     */
    public static void writeRats(ArrayList<Rat> rats, File ratFile){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ratFile));
            String line = "";
            for(Rat rat: rats) {
                Rat.Gender gender = rat.getGender();
                String x = String.valueOf(rat.getGridX());
                String y = String.valueOf(rat.getGridY());
                line = gender + x + y;
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
