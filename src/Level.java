import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Oscar Han
public class Level {
    // Level should contain all the recipes
    // Game g = (Game) getParentScreen().getParent();
    // Implement some way to detect final item

    private Recipe[] recipes;
    private Item objective;

    public Level()
    {
        this(0);
    }

    public Level(int level)
    {
        if(level== 0)
        {
            ArrayList<Integer> faceMask= new ArrayList<Integer>();
            faceMask.add(1);
            faceMask.add(2);
            faceMask.add(7);
            recipes= new Recipe[]{new Recipe("Face Mask", 16, faceMask)};
            objective= new Item("Face Mask", 16, "Monkey", loadImage("Items/FaceMask.png"));
        }
        if(level== 1)
        {
            ArrayList<Integer> handSanitizer= new ArrayList<Integer>();
            handSanitizer.add(20);
            handSanitizer.add(10);
            ArrayList<Integer> sanitizerGel= new ArrayList<Integer>();
            sanitizerGel.add(17);
            sanitizerGel.add(18);
            sanitizerGel.add(19);
            recipes= new Recipe[]{new Recipe("Hand Sanitizer", 21, handSanitizer), new Recipe("Sanitizer Gel", 20, sanitizerGel)};
            objective= new Item("Hand Sanitizer", 21, "Monkey", loadImage("Items/HandSanitizer.png"));
        }
        if(level== 2)
        {
            ArrayList<Integer> faceShield= new ArrayList<Integer>();
            faceShield.add(7);
            faceShield.add(8);
            faceShield.add(2);
            faceShield.add(14);
            ArrayList<Integer> shieldCover= new ArrayList<Integer>();
            shieldCover.add(5);
            shieldCover.add(13);
            shieldCover.add(12);
            ArrayList<Integer> visorHandle= new ArrayList<Integer>();
            visorHandle.add(4);
            visorHandle.add(9);
            visorHandle.add(6);
            visorHandle.add(3);
            ArrayList<Integer> plasticGuard= new ArrayList<Integer>();
            plasticGuard.add(3);
            plasticGuard.add(11);
            recipes= new Recipe[]{new Recipe("Face Shield", 15, faceShield), new Recipe("Shield Cover", 14, shieldCover), new Recipe("Visor Handle", 13, visorHandle), new Recipe("Plastic Guard", 12, plasticGuard)};
            objective= new Item("Face Shield", 15, "Monkey", loadImage("Items/FaceShield.png"));
        }
    }

    public Recipe[] getRecipes(){
        return recipes;
    }

    public Item getObjective() {
        return objective;
    }

    public static Image loadImage(String filePath)
    {
        try
        {
            return ImageIO.read(new File(filePath));
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
