import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * Jun 16 2020: Added static final IDs ~Ben Zeng, 2 mins
 * Jun 11 2020: Added IDs to all storage components for the save systemNathan Lu, 5 mins
 * Jun 9 2020: Created ~Nathan Lu, 20 mins
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 1
 */
public class SecondBathroom extends GameplayRoom
{
    public static int CABINET = 3;
    public SecondBathroom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 130, 660)); // Left Border (with bathtub)
        addHitBox(new HitBox(0, 0, 610, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 940, 0, 2600, 600)); // Right Border

        addHitBox(new HitBox(0, 130, 130, 245, 340)); // Shelves at the left
        addHitBox(new StorageUnit(0, 240, 180, 375, 340, 3, 3, CABINET)); // Washing machine at the left
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
