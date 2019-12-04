import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
//Your life is the sum of a remainder of an unbalanced equation inherent to the programming
//of the matrix

public class TheArchitect extends JFrame {

    public void setExit(int x, int y)//records the location of the exit so we can show it when its time
    {
        WallXCord = x;
        WallYCord = y;
    }

    public void showWall()//used when its time to show the exit.
    {
        updatedMatrix[WallXCord][WallYCord] = BLOCK_EXIT;
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix, int totalDimonds) {
        int[] coordsPlayer = findPlayer(currentMatrix);
        int x = coordsPlayer[0];
        int y = coordsPlayer[1];
        globalTotalDimonds = totalDimonds; //use this later for the gui dimond count
        nextLevel(false); //dont go to the next level yet.
        if (collected == totalDimonds)//if we have all the dimonds give the player the exit
        {
            showWall();
        }
        updatedMatrix = updateMatrix(xScale, yScale, currentMatrix, x, y); //we will return updatedMatrix for the gui
    }//end method

    private int[] findPlayer(String[][] currentMatrix) {
        int coordX = 0, coordY = 0;
        for (int i = 0; i < currentMatrix.length; i++) { //for loop will find were the player is now
            for (int j = 0; j < currentMatrix[i].length; j++) {
                if (isPlayer(currentMatrix[i][j])) {
                    coordX = i;
                    coordY = j;
                    break;
                }
            }
        }//end both for loops
        return new int[]{coordX, coordY};
    }

    private boolean isPlayer(String block) {
        return block.equals(BLOCK_PLAYER);
    }

    private String[][] updateMatrix(int xScale, int yScale, String[][] currentMatrix, int x, int y) {
        String nextBlock = currentMatrix[x + xScale][y + yScale];
        switch (nextBlock) {
            case BLOCK_HIDDEN:
            case BLOCK_DIAMOND:
                collected++;
                break;
            case BLOCK_EXIT:
                nextLevel(true);
                break;
            case BLOCK_MOVEABLE:
                if (currentMatrix[x + (xScale * 2)][y + (yScale * 2)].equals(BLOCK_NORMAL)) {
                    currentMatrix[x + (xScale * 2)][y + (yScale * 2)] = BLOCK_MOVEABLE;
                } else {
                    throw new OutOfLimits("Ass Hole hit wall!");
                }
                break;
            default:
                if (!nextBlock.equals(BLOCK_NORMAL)) {
                    throw new OutOfLimits("Ass Hole hit wall!");
                }
        }
        currentMatrix[x][y] = BLOCK_NORMAL;
        currentMatrix[x + xScale][y + yScale] = BLOCK_PLAYER;
        return currentMatrix;
    }
    //true we go to next level, false we update current level's gui

    public void nextLevel(boolean tOrF) {
        level = tOrF;
    }

    //returs level true or false
    public boolean getLevel() {
        return level;
    }

    //for GUI JLabel, show how many dimonds are left to be collected
    public int getDimondsLeft() {
        return globalTotalDimonds - collected;
    }

    //returns the updated matrix for the gui to display
    public String[][] getUpdatedMatrix() {
        return updatedMatrix;
    }

    private class OutOfLimits extends RuntimeException {

        public OutOfLimits(String event) {
            JFrame frame = new JFrame("Warning");
            JOptionPane.showMessageDialog(frame, "You Stupid Ass, Ran in to something did you?");
        }
    }//end inner class

    int foundPlayer = 0;
    String[][] updatedMatrix;
    int WallXCord;
    int WallYCord;
    int collected = 0;
    boolean level;
    int globalTotalDimonds = 0;
    private static final String BLOCK_HIDDEN = "H";
    private static final String BLOCK_DIAMOND = "D";
    private static final String BLOCK_MOVEABLE = "M";
    private static final String BLOCK_NORMAL = "N";
    private static final String BLOCK_EXIT = "E";
    private static final String BLOCK_PLAYER = "P";
}//end class
