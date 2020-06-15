import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hallway extends GameplayRoom
{
    public Hallway()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 430)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 590, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border
        addHitBox(new HitBox(0, 480, 190, 610, 460)); // Statue

        addHitBox(new Door(0, 1060, 430, 1080, 595, "Outside")); //Door at the right
        addHitBox(new Door(0, 470, 600, 635, 620, "Carrie's Room")); //Door at the bottom
        addHitBox(new Door(0, 0, 425, 20, 590, "Dining Room")); //Door at the left
        addHitBox(new Door(0, 300, 200, 430, 430, "Ava's Room")); //Door at top left
        addHitBox(new Door(0, 665, 200, 795, 430, "Alice's Room")); //Door at top right
    }

    @Override
    public int getStartX()
    {
        return 400;
    }

    @Override
    public int getStartY()
    {
        return 400;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Hallway.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
