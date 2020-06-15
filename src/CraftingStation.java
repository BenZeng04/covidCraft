import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class CraftingStation extends StorageUnit {

    public CraftingStation(int layer, int xStart, int yStart, int xEnd, int yEnd, int id)
    {
        super(layer, xStart, yStart, xEnd, yEnd,1,4, id);

    }
    @Override
    public void whenInteractedWith()
    {
        if(!getGame().isAnvilUsed())
        {
            getGame().setAnvilUsed(true);
            // Tutorial dialogue box pops up if user never opened their inventory
            addComponent(new DialogueGUI("This is an anvil! This will be the main device that you will be using to craft!", "Simply put the ingredients in the top column, and click the craft button!")
            {
                @Override
                public void whenExited()
                {
                    createGUI();
                }
            });
        }
        else createGUI();
    }
    @Override
    public void createGUI()
    {
        super.createGUI();
        // 540, 360 (Centre of the screen)
        Button craftButton = new Button("",340,330,400,60,5, 1005)
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
                Item[] storage = getStorage();
                Game game = getGame();
                Level currentLevel = new Level(game.getLevel());
                Recipe[] recipes = currentLevel.getRecipes();
                ArrayList<Integer> itemIDs = new ArrayList<>();
                for(Item i: storage) if(i != null) itemIDs.add(i.ID);
                for(Recipe recipe: recipes)
                {
                    if(recipe.canCraft(itemIDs))
                    {
                        Item item = Item.IDtoItem.get(recipe.getID());
                        ArrayList<Integer> IDs = recipe.getIngredientIDs(); // removing from storage

                        for(int i = 0; i < storage.length; i++)
                        {
                            if(storage[i] != null && IDs.contains(storage[i].ID))
                            {
                                IDs.remove(storage[i].ID);
                                storage[i] = null;
                            }
                            if(storage[i] == null)
                            {
                                storage[i] = item;
                                item = null; // Sets the first empty slot to the item. (There should guaranteed be at least 1)
                            }
                        }
                        return;
                    }
                }
            }
        };
        craftButton.setText("Craft",500,370,30,Color.white);
        craftButton.setColor(Color.GRAY, Color.BLACK, Color.WHITE);
        addComponent(craftButton);
    }
}

