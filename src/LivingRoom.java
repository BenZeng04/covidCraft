import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
/**
 * @author Ben Zeng, Nathan Lu
 * Revision History:
 * - June 9, 2020: Added collisions ~Nathan Lu. Time Spent: 20m
 * - June 2, 2020: Created ~Ben Zeng. Time Spent: 20m
 * An extension of the custom MultiPanel which allows for animations and key listeners between multiple panels. Any given JPanel can be set as the current screen and animated. Meant to be the main "host" for the application.
 * @version 1
 */
public class LivingRoom extends GameplayRoom
{
    // Constants for IDs of storage units
    public static final int DRESSER = 0, DESK = 1, TV_DRAWER = 2;
    public LivingRoom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new StorageUnit(0, 20, 260, 170, 390, 2, 5, DRESSER)); // Dresser at the left side
        addHitBox(new StorageUnit(0, 175, 260, 360, 390, 1, 3, DESK)); // Desk at the left side
        addHitBox(new HitBox(0, 360, 190, 420, 340)); // Tall potted plant near the TV
        addHitBox(new StorageUnit(0, 440, 250, 640, 390, 2, 3, TV_DRAWER)); // TV table
        addHitBox(new HitBox(0, 650, 150, 730, 360)); // Lamp beside TV
        addHitBox(new HitBox(0, 930, 231, 1050, 350)); // Bookcase
        addHitBox(new HitBox(0, 410, 460, 670, 600)); // Couch

        addHitBox(new Door(0, 780, 90, 900, 330, "Bathroom 1")); // Door at top
        addHitBox(new Door(0, 770, 650, 920, 670, "Dining Room")); // Door at bottom
    }

    @Override
    public int getStartX()
    {
        return 844;
    }

    @Override
    public int getStartY()
    {
        return 520;
    }

    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Living.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
