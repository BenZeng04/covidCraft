import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FirstBathroom extends GameplayRoom
{
    public static final int LEFT_CABINET = 7, RIGHT_CABINET = 8, LAUNDRY_BASKET = 9;
    public FirstBathroom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 260, 660)); // Left Border (with bathtub)
        addHitBox(new HitBox(0, 0, 600, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 970, 0, 2600, 600)); // Right Border

        addHitBox(new StorageUnit(0, 630, 220, 750, 380, 1, 3, LEFT_CABINET)); // black cabinet at the left
        addHitBox(new StorageUnit(0, 750, 220, 860, 380, 1, 3, RIGHT_CABINET)); // black cabinet at the right
        addHitBox(new HitBox(0, 860, 230, 970, 400)); // Washing Machine
        addHitBox(new StorageUnit(0, 875, 200, 960, 280, 1, 2, LAUNDRY_BASKET)); // laundry basket
        addHitBox(new HitBox(0, 330, 190, 410, 380)); // Toilet
        addHitBox(new HitBox(0, 290, 280, 329, 350)); // Plunger


        addHitBox(new Door(0, 600, 600, 760, 620, "Living Room")); // door at bottom
    }

    @Override
    public int getStartX()
    {
        return 670;
    }

    @Override
    public int getStartY()
    {
        return 480;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bath1.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
