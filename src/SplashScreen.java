import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Oscar Han
 * Revision History:
 * - June 8, 2020: Created ~Oscar Han. Time Spent: 5m
 * The splash screen that plays at the beginning.
 * @version 1
 */
public class SplashScreen extends ScreenPanel {
    /**
     * The logo
     */
    private Image logo;
    /**
     * Timer for fade
     */
    private int fade;

    /**
     * Default Constructor
     */
    public SplashScreen() {
        try {
            logo= ImageIO.read(new File("Backgrounds/Company_Logo.png"));
        }
        catch(IOException e) {
        }
    }

    @Override
    public void draw(Graphics g)
    {
        if(fade == 255)
        {
            addComponent(new TransitionEvent("MainMenu")); // change this
            return;
        }
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1080,720);
        g.drawImage(logo,300,10,null);

        g.setColor(new Color(0, 0, 0, 255 - fade));
        g.fillRect(0, 0, 1080, 720);
        fade++;
    }
}
