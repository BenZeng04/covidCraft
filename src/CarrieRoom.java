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
public class CarrieRoom extends GameplayRoom
{
    public static final int CARRIE = 16;
    public CarrieRoom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 750, 80, 1050, 340)); //furniture to right of bed
        addHitBox(new HitBox(0, 580, 140, 770, 490)); //bed
        addHitBox(new HitBox(0, 370, 200, 580, 340)); //desk
        addHitBox(new HitBox(0, 420, 220, 530, 390)); //chair
        addHitBox(new HitBox(0, 20, 140, 340, 370)); //furniture at left
        addHitBox(new HitBox(0, 930, 440, 1050, 650)); //telescope
        addHitBox(new Roommate(0, 600, 140, 750, 360, CARRIE, Item.loadImage("Sprites_Humans/Carrie.png"), Item.UTILITYKNIFE)); //Roommate
        addHitBox(new Door(0, 0, 445, 30, 610, "Hallway")); //Door at the left
    }

    @Override
    public int getStartX()
    {
        return 100;
    }

    @Override
    public int getStartY()
    {
        return 530;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bed_Carrie.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
