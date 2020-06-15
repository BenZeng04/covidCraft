// Oscar Han

/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author Oscar Han
 * Revision History:
 * @version 1
 */

public class Highscores extends ScreenPanel
{
    // https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
    private Image bg;

    public Highscores()
    {
        try {
            bg= ImageIO.read(new File("Backgrounds/HighScores_Bg.png"));
        }
        catch(IOException e) {
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(bg,0,0,null);
    }
}