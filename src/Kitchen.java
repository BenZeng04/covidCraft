import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * Jun 16 2020: Added static final IDs ~Ben Zeng, 2 mins
 * Jun 11 2020: Added IDs to all storage components for the save systemNathan Lu, 5 mins
 * Jun 10 2020: Created ~Nathan Lu, 20 mins
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 1
 */

public class Kitchen extends GameplayRoom
{
    public static final int FRIDGE = 10, TOP_CABINET = 11, SINK = 12, BOTTOM_CABINET = 13;
    public Kitchen()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 330)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new StorageUnit(0, 20, 170, 110, 390, 2, 4, FRIDGE)); // Fridge
        addHitBox(new StorageUnit(0, 110, 80, 230, 230, 3, 2, TOP_CABINET)); // Cabinet
        addHitBox(new StorageUnit(0, 120, 290, 220, 390, 2, 2, SINK)); // Sink Cabinet
        addHitBox(new HitBox(0, 230, 220, 650, 390)); // Other furniture
        addHitBox(new StorageUnit(0, 750, 500, 960, 665, 3, 5, BOTTOM_CABINET)); // Cabinets at bottom of screen
        addHitBox(new HitBox(0, 960, 410, 1060, 660)); // Cabinets at bottom
        addHitBox(new HitBox(0, 960, 250, 1020, 340)); // Garbage can

        addHitBox(new Door(0, 740, 80, 860, 330, "Dining Room")); //door on top
    }

    @Override
    public int getStartX()
    {
        return 844;
    }

    @Override
    public int getStartY()
    {
        return 324;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Kitchen.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
