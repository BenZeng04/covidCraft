import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Ben Zeng
 * Revision History:
 * - Jun 11, 2020: Created ~Ben Zeng. Time Spent: 30m
 * The class representing the Crafting Station / Anvil in Alice's room. Utilizes currently existing StorageUnit class as it's base.
 * @version 1
 */
public class CraftingStation extends StorageUnit
{
    /**
     * Default constructor, creates a hitbox from (xStart, yStart) to (xEnd, yEnd)
     * @param layer The layer
     * @param xStart The x-start for the hitbox
     * @param yStart The y-start for the hitbox
     * @param xEnd The x-end for the hitbox
     * @param yEnd The y-end for the hitbox
     */
    public CraftingStation(int layer, int xStart, int yStart, int xEnd, int yEnd, int id)
    {
        super(layer, xStart, yStart, xEnd, yEnd, 1, 4, id);
    }

    @Override
    public void whenInteractedWith()
    {
        if(!getGame().isAnvilUsed())
        {
            getGame().setAnvilUsed(true);
            // Tutorial dialogue box pops up if user never opened the anvil
            addComponent(new DialogueGUI("This is an anvil! This will be the main device that you will be using to craft! Simply put the ingredients in the top column, and click the craft button!")
            {
                @Override
                public void whenExited()
                {
                    createGUI(); // Creates the crafting station GUI upon exiting dialogue
                }
            });
        }
        else
            createGUI();
    }

    @Override
    public void createGUI()
    {
        super.createGUI();
        // 540, 360 (Centre of the screen)
        Button craftButton = new Button(340, 330, 400, 60, 5, 1005)
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE || ke.getKeyChar() == 'e' || ke.getKeyChar() == 'E')
                    removeComponent(this); // Close this button off!
            }

            @Override
            public void buttonPressed(MouseEvent event)
            {
                Item[] storage = getStorage(); // inventory of crafting station
                Game game = getGame();
                Level currentLevel = game.getLevel(game.getCurrentLevel());
                Recipe[] recipes = currentLevel.getRecipes();
                ArrayList<Integer> itemIDs = new ArrayList<>();
                for(Item i: storage)
                    if(i != null)
                        itemIDs.add(i.ID);
                // Checks for all recipes
                for(Recipe recipe: recipes)
                {
                    if(recipe.canCraft(itemIDs))
                    {
                        Item item = Item.IDtoItem.get(recipe.getID());
                        ScreenPanel minigame = null;
                        // Special case for the three special items that summon a minigame
                        if(item == Item.VISORHANDLE)
                            minigame = new AccuracyMinigame(item, CraftingStation.this);
                        else if(item == Item.FACEMASK)
                            minigame = new ScissorsMinigame(item, CraftingStation.this);
                        else if(item == Item.SANITIZERGEL)
                            minigame = new PouringMinigame(item, CraftingStation.this);

                        ArrayList<Integer> IDs = (ArrayList<Integer>) recipe.getIngredientIDs().clone(); // removing from storage
                        for(int i = 0; i < storage.length; i++)
                        {
                            if(storage[i] != null && !storage[i].IS_TOOL && IDs.contains(storage[i].ID))
                            {
                                IDs.remove((Integer) storage[i].ID);
                                storage[i] = null;
                            }
                        }
                        if(minigame != null)
                        {
                            // Creating a screen dedicated to the mini-game.
                            getHostPanel().add(minigame, "Minigame");
                            getHostPanel().displayPanel("Minigame");
                        }
                        else CraftingStation.this.addItem(item);
                        return;
                    }
                }
            }
        };
        craftButton.setText("Craft", 500, 370, 30, Color.white);
        craftButton.setColor(Color.GRAY, Color.BLACK, Color.WHITE);
        addComponent(craftButton);
    }
}

