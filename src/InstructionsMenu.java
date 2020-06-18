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

public class InstructionsMenu extends ScreenPanel
{
    /**
     * Background
     */
    private Image bg;

    public InstructionsMenu()
    {
        try {
            bg= ImageIO.read(new File("Backgrounds/Instructions_Bg.png"));
        }
        catch(IOException e) {
        }
        /**
         * Button to return back to MainMenu
         */
        ScreenChangeButton toMain = new ScreenChangeButton("MainMenu", 340, 630, 400, 60, 5);
        toMain.setText("Done",500,670,30,Color.white);
        toMain.setColor(Color.GRAY,Color.BLACK,Color.WHITE);
        addComponent(toMain);
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(bg,0, 0, 1080, 720, null);
    }
}