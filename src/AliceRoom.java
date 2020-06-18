import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AliceRoom extends GameplayRoom
{
    public static final int DRAWERS = 14, LAUNDRY_BASKET = 15; // Unused Storage Device.
    public AliceRoom(boolean createTutorial)
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 210, 140, 410, 490)); //bed
        addHitBox(new StorageUnit(0, 30, 280, 210, 390, 2, 5, DRAWERS)); //drawers
        addHitBox(new StorageUnit(0, 30, 170, 160, 280, 1, 2, LAUNDRY_BASKET)); //drawers
        addHitBox(new HitBox(0, 410, 230, 650, 400)); //computer desk
        addHitBox(new CraftingStation(0, 650, 240, 830, 380, 99)); //anvil (ID set to an unused ID, as no items will initially appear here.
        addHitBox(new HitBox(0, 810, 120, 1050, 380)); //bulletin board
        addHitBox(new Computer(0,497,155,595,315)); // computer
        addHitBox(new Door(0, 560, 650, 720, 670, "Hallway")); //Door at the bottom

        if(createTutorial)
            TutorialDialogue.startTutorial(this);
    }
    @Override
    public int getStartX()
    {
        return 850;
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
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bed_Alice.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
