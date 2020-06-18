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
 * @version 3
 */
public class AvaRoom extends GameplayRoom
{
    public static final int AVA = 17;
    public AvaRoom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 180, 170, 380, 520)); //bed
        Roommate Ava = new Roommate(0, 0, 160, 250, 450, AVA, Item.loadImage("Sprites_Humans/Ava.png"), Item.SCISSORS);
        addHitBox(Ava); //Roommate
        addHitBox(new HitBox(0, 20, 140, 180, 350)); //furniture at left
        addHitBox(new HitBox(0, 380, 230, 580, 370)); //desk
        addHitBox(new HitBox(0, 390, 260, 490, 410)); //chair
        addHitBox(new HitBox(0, 570, 230, 740, 390)); //bookshelf
        addHitBox(new HitBox(0, 820, 190, 910, 370)); //guitar
        addHitBox(new HitBox(0, 940, 220, 1040, 390)); //chair

        addHitBox(new Door(0, 515, 650, 670, 700, "Hallway")); //Door at the bottom
        addHitBox(new Door(0, 1050, 425, 1080, 590, "Bathroom 2")); //Door at the right
    }

    @Override
    public int getStartX()
    {
        return 600;
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
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bed_Ava.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
