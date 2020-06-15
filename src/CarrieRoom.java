import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CarrieRoom extends GameplayRoom
{
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
