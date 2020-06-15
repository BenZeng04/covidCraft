import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AvaRoom extends GameplayRoom
{
    public AvaRoom()
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 180, 170, 380, 520)); //bed
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
        return 400;
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
