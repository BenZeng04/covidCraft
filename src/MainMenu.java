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

/**
 * @author Oscar Han
 * Revision History:
 * - May 29, 2020: Created ~Oscar Han.
 * @version 1
 */
public class MainMenu extends ScreenPanel
{
    private Image bg;

    /**
     * Default constructor
     */
    public MainMenu()
    {
        boolean fileEmpty = isSaveFileEmpty();
        try
        {
            bg = ImageIO.read(new File("Backgrounds/Menu_Bg.png"));
        }
        catch(IOException e)
        {
        }
        // Creates buttons.
        Button[] fromMenu = new Button[4];
        fromMenu[0] = new ScreenChangeButton("Gameplay", 340, 250, 400, 60, 5)
        {
            @Override
            public void buttonPressed(MouseEvent event)
            {
                getHostPanel().add(new Game(false), "Gameplay"); // resetting gameplay
                super.buttonPressed(event);
            }
        };

        // Displays a grayed out "Continue" button if there is no file
        if(fileEmpty)
            fromMenu[1] = new Button(340, 330, 400, 60, 5);
        else
        {
            fromMenu[1] = new ScreenChangeButton("Gameplay", 340, 330, 400, 60, 5)
            {
                @Override
                public void buttonPressed(MouseEvent event)
                {
                    getHostPanel().add(new Game(true), "Gameplay"); // resetting gameplay, but saving
                    super.buttonPressed(event);
                }
            };
        }
        fromMenu[2] = new ScreenChangeButton("InstructionsMenu", 340, 410, 400, 60, 5);
        fromMenu[3] = new ScreenChangeButton("ExitMenu", 340, 490, 400, 60, 5);
        fromMenu[0].setText("New Game", 470, 290, 30, Color.white);
        fromMenu[1].setText("Continue Game", 425, 370, 30, Color.white);
        fromMenu[2].setText("Instructions", 430, 450, 30, Color.white);
        // fromMenu[3].setText("Highscores",460,530,30,Color.white);
        fromMenu[3].setText("Exit Game", 460, 530, 30, Color.white);
        for(int i = 0; i < 4; i++)
        {
            if(i == 1 && fileEmpty)
            {
                fromMenu[i].setColor(Color.BLACK, Color.BLACK, Color.WHITE);
            }
            else
            {
                fromMenu[i].setColor(Color.GRAY, Color.BLACK, Color.WHITE);
            }
            addComponent(fromMenu[i]);
        }
    }

    /**
     * Checks for whether or not the save file is empty
     *
     * @return result
     */
    private boolean isSaveFileEmpty()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
            return br.readLine() == null;
        }
        catch(IOException e)
        {
            return true;
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(bg, 0, 0, null);
    }
}
