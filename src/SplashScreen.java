import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Oscar
public class SplashScreen extends ScreenPanel {
    private Image logo;
    private int fade;

    public SplashScreen() {
        try {
            logo= ImageIO.read(new File("Backgrounds/Company_Logo.png"));
        }
        catch(IOException e) {
        }
    }

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
