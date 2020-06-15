import java.util.ArrayList;
import java.util.HashMap;

// Oscar Han
public class Level {
    // Level should contain all the recipes
    // Game g = (Game) getParentScreen().getParent();
    // Implement some way to detect final item

    private Recipe[] recipes;

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
            recipes= new Recipe[]{new Recipe("FaceMask", 16, faceMask)};
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
            recipes= new Recipe[]{new Recipe("Sanitizer Gel", 20, sanitizerGel), new Recipe("Hand Sanitizer", 21, handSanitizer)};
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
            ArrayList<Integer> plasticGuard= new ArrayList<Integer>();
            plasticGuard.add(3);
            plasticGuard.add(11);
        }
    }

    public Recipe[] getRecipes(){
        return recipes;
    }
}
