/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * @author Oscar Han
 * Revision History:
 * - May 29, 2020: Created ~Oscar Han.
 * @version 1
 */
public class MainMenu extends ScreenPanel
{
    private Image bg;
    private Button[] fromMenu= new Button[5];
    /**
     * Default constructor
     *
     */
    public MainMenu()
    {

        try {
            bg= ImageIO.read(new File("Backgrounds/Menu_Bg.png"));
        }
        catch(IOException e) {
        }
        // change directories
        fromMenu[0]= new Button("Gameplay",340,250,400,60,5);
        fromMenu[1]= new Button("Gameplay",340,330,400,60,5);
        fromMenu[2]= new Button("InstructionsMenu",340,410,400,60,5);
        fromMenu[3]= new Button("HighScoresMenu",340,490,400,60,5);
        fromMenu[4]= new Button("ExitMenu",340,570,400,60,5);
        fromMenu[0].setText("New Game",470,290,30,Color.white);
        fromMenu[1].setText("Continue Game",425,370,30,Color.white);
        fromMenu[2].setText("Instructions",430,450,30,Color.white);
        fromMenu[3].setText("Highscores",460,530,30,Color.white);
        fromMenu[4].setText("Exit Game",465,610,30,Color.white);
        for(int i=0; i <= 4; i++) {
            fromMenu[i].setColor(Color.GRAY, Color.BLACK, Color.WHITE);
            addComponent(fromMenu[i]);
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(bg,0,0,null);;
    }
}
