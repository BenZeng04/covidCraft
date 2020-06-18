import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DiningRoom extends GameplayRoom
{
    public static final int DRAWER = 4, BOOKSHELF = 5, DISPLAY_CASE = 6;
    public DiningRoom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 30, 180, 110, 380)); //lamp at the very left
        addHitBox(new StorageUnit(0, 150, 230, 240, 350, 1, 2, DRAWER)); //drawer beside bookshelf
        addHitBox(new StorageUnit(0, 240, 110, 420, 350, 3, 2, BOOKSHELF)); //bookshelf
        addHitBox(new StorageUnit(0, 420, 80, 670, 350, 3, 3, DISPLAY_CASE)); //display case
        addHitBox(new HitBox(0, 670, 220, 740, 340)); //flower pot
        addHitBox(new HitBox(0, 960, 170, 1039, 380)); //lamp at the very right

        addHitBox(new HitBox(0, 260, 400, 820, 590)); //dining table and chairs

        addHitBox(new Door(0, 800, 80, 910, 330, "Living Room")); //door on top
        addHitBox(new Door(0, 1060, 420, 1080, 585, "Hallway")); //door on right
        addHitBox(new Door(0, 830, 650, 995, 670, "Kitchen")); //door at bottom
    }

    @Override
    public int getStartX()
    {
        return 1000;
    }

    @Override
    public int getStartY()
    {
        return 500;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Dining.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
