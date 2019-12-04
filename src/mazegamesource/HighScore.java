
import java.util.*;
import java.io.*;
import java.beans.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HighScore {

    public void addHighScore(String name, int min, int sec, int level) {
        if (name != null) {
            try {
                String outData = "PlayerName: " + name + " Total Time for Levels:" + min + ":" + sec + "(Minutes:Seconds)" + "Level Reached:*" + level;
                PrintWriter out = new PrintWriter(new FileOutputStream("scores.txt", true));
                out.println("");
                out.println(outData);
                out.close();
            }//prints the highscore data to scores.txt
            catch (Exception ex) {
                System.out.println(ex);
            }//end catch
        } else {
            JFrame frame = new JFrame("Warning");
            JOptionPane.showMessageDialog(frame, "Name is not set. Scores will not save while name is not set.");
        }
    }//end addHighScore   
}//end class

