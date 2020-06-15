/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of generic / abstract classes that will be relevant for gameplay.
 */

import java.util.*;

/**
 * @author Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Nathan Lu. Time Spent: 15m
 * The class representing recipes.
 * @version 1
 */
public class Recipe
{
    private ArrayList<Integer> ingredientIDs;
    private int id;
    private String name;
    private static HashMap<Integer, String> dictionary = new HashMap<>();
    static
    {
        dictionary.put(1, "T Shirt");
        dictionary.put(2, "Scissors");
        dictionary.put(3, "Hole Puncher");
        dictionary.put(4, "Utility Knife");
        dictionary.put(5, "Tape");
        dictionary.put(6, "Tape Measure");
        dictionary.put(7, "Thread");
        dictionary.put(8, "Elastic");
        dictionary.put(9, "Bucket");
        dictionary.put(10, "Plastic Bottle");
        dictionary.put(11, "Plastic Sheet");
        dictionary.put(12, "Plastic Guard");
        dictionary.put(13, "Visor Handle");
        dictionary.put(14, "Shield Cover");
        dictionary.put(15, "Face Shield");
        dictionary.put(16, "Face Mask");
        dictionary.put(17, "Rubbing Alcohol");
        dictionary.put(18, "Aloe Vera Gel");
        dictionary.put(19, "Essential Oil");
        dictionary.put(20, "Sanitizer Gel");
        dictionary.put(21, "Hand Sanitizer");
    }

    public Recipe(String name, int id, ArrayList<Integer> ingredients)
    {
        this.name = name;
        this.id = id;
        this.ingredientIDs = ingredients;
    }

    public boolean canCraft(ArrayList<Integer> items) // if item is craftable
    {
        int matchingItems = 0;
        for(int x = 0; x < items.size(); x++)
        {
            if(ingredientIDs.contains(items.get(x)))
            {
                matchingItems++;
            }
        }
        return matchingItems >= ingredientIDs.size();
    }

    public boolean itemsNeeded(ArrayList<Integer> items) // what items are needed
    {
        ArrayList<Integer> copyOfIngredientIDs = new ArrayList<Integer>();
        for(int x: ingredientIDs)
        {
            copyOfIngredientIDs.add(x);
        }
        for(int x = 0; x < items.size(); x++)
        {
            if(copyOfIngredientIDs.contains(items.get(x)))
            {
                copyOfIngredientIDs.remove(copyOfIngredientIDs.get(items.get(x)));
            }
        }
        if(copyOfIngredientIDs.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getID()
    {
        return id;
    }

    public ArrayList<Integer> getIngredientIDs()
    {
        return ingredientIDs;
    }

    public HashMap<Integer, String> getDictionary(){
        return dictionary;
    }
}
