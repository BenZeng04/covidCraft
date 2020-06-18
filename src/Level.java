
import java.util.ArrayList;

/**
 * @author Oscar Han
 * Revision History:
 * - Jun 8, 2020: Created ~Oscar Han. Time Spent 30m
 * The class representing the storage components
 * @version 1
 */
public class Level
{
    /**
     * The recipe book for this level
     */
    private final Recipe[] recipes;
    /**
     * The final objective item needed to be crafted
     */
    private final Item objective;

    /**
     * Default constructor that initializes the levels and the recipes involved
     * @param level the level number
     */
    public Level(int level)
    {
        if(level == 0)
        {
            ArrayList<Integer> faceMask = new ArrayList<>();
            faceMask.add(1);
            faceMask.add(2);
            faceMask.add(7);
            recipes = new Recipe[] {new Recipe(16, faceMask)};
            objective = Item.FACEMASK;
        }
        else if(level == 1)
        {
            ArrayList<Integer> handSanitizer = new ArrayList<>();
            handSanitizer.add(20);
            handSanitizer.add(10);
            ArrayList<Integer> sanitizerGel = new ArrayList<>();
            sanitizerGel.add(17);
            sanitizerGel.add(18);
            sanitizerGel.add(19);
            recipes = new Recipe[] {new Recipe(21, handSanitizer), new Recipe(20, sanitizerGel)};
            objective = Item.HANDSANITIZER;
        }
        else
        {
            ArrayList<Integer> faceShield = new ArrayList<>();
            faceShield.add(7);
            faceShield.add(8);
            faceShield.add(2);
            faceShield.add(14);
            ArrayList<Integer> shieldCover = new ArrayList<>();
            shieldCover.add(5);
            shieldCover.add(13);
            shieldCover.add(12);
            ArrayList<Integer> visorHandle = new ArrayList<>();
            visorHandle.add(4);
            visorHandle.add(9);
            visorHandle.add(6);
            visorHandle.add(3);
            ArrayList<Integer> plasticGuard = new ArrayList<>();
            plasticGuard.add(3);
            plasticGuard.add(11);
            recipes = new Recipe[] {new Recipe(15, faceShield), new Recipe(14, shieldCover), new Recipe(13, visorHandle), new Recipe(12, plasticGuard)};
            objective = Item.FACESHIELD;
        }
    }

    /**
     * Getter for recipes
     * @return the recipes
     */
    public Recipe[] getRecipes()
    {
        return recipes;
    }

    /**
     * Getter for objective
     * @return the objective
     */
    public Item getObjective()
    {
        return objective;
    }
}
