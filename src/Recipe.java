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
    /**
     * Ingredients needed to craft
     */
    private ArrayList<Integer> ingredientIDs;
    /**
     * ID of the item crafted
     */
    private int id;

    /**
     * Constructor of Recipe class
     * @param id ID of craftable for a recipe.
     * @param ingredients ArrayList of IDs required to make craftable.
     */
    public Recipe(int id, ArrayList<Integer> ingredients)
    {
        this.id = id;
        this.ingredientIDs = ingredients;
    }
    /**
     * items is compared with ingredientIDs to determine whether the craftable is craftable with the items in possession.
     * @param items ArrayList of IDs of items in user inventory.
     */
    public boolean canCraft(ArrayList<Integer> items)
    {
        for(int x = 0; x < items.size(); x++)
            if(!ingredientIDs.contains(items.get(x)))
                return false;
        for(int x = 0; x < ingredientIDs.size(); x++)
            if(!items.contains(ingredientIDs.get(x)))
                return false;
        return true;
    }

    /**
     * Getter method for the ID of the recipe
     * @return id
     */
    public int getID()
    {
        return id;
    } // getter method- returns ID of recipe
    /**
     * Getter method for the ingredient list IDs of the recipe
     * @return ArrayList of ingredients
     */
    public ArrayList<Integer> getIngredientIDs()
    {
        return ingredientIDs;
    } // getter method- returns IDs required to make recipe
}
