// Oscar Han

/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Oscar Han
 * Revision History:
 * - May 29, 2020: Created ~Oscar Han.
 * @version 1
 */
public class ExitMenu extends ScreenPanel
{
    // https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
    private Image bg;
    private int framesElapsed;

    /**
     * Constructor: loads background image
     */
    public ExitMenu() {
        try {
            bg= ImageIO.read(new File("Backgrounds/Exit_Bg.png"));
        }
        catch(IOException e) {
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(bg,0,0, 1080, 720, null);
        framesElapsed++;
        if(framesElapsed == 60 * 5)
            System.exit(0);
    }
}