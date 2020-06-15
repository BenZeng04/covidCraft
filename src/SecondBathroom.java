import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SecondBathroom extends GameplayRoom
{
    public SecondBathroom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 130, 660)); // Left Border (with bathtub)
        addHitBox(new HitBox(0, 0, 610, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 940, 0, 2600, 600)); // Right Border

        addHitBox(new HitBox(0, 130, 130, 245, 340)); // Shelves at the left
        addHitBox(new StorageUnit(0, 240, 180, 375, 340, 0)); // Cabinet at the left
        addHitBox(new HitBox(0, 390, 250, 460, 330)); // Plant
        addHitBox(new HitBox(0, 800, 80, 950, 460)); // Bathtub
        addHitBox(new HitBox(0, 820, 470, 950, 550)); // Toilet
        addHitBox(new HitBox(0, 920, 530, 950, 590)); // Toilet Paper

        addHitBox(new Door(0, 635, 90, 750, 330, "Ava's Room")); // door at top
    }

    @Override
    public int getStartX()
    {
        return 680;
    }

    @Override
    public int getStartY()
    {
        return 350;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bath2.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
