/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */

import java.io.*;
import java.util.*;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Ben Zeng. Time Spent: 5m
 * Quick Juicy Edits ~Oscar 30 seconds
 * Class representing the entirety of the gameplay element, which will host the several screens that are present in this application.
 * @version 1
 */

public class Game extends MultiPanel
{
    /**
     * Constant for game size
     */
    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 720;
    /**
     * Inventory of the player throughout the game
     */
    private Item[] inventory;
    /**
     * Level constant list with information on recipes
     */
    private Level[] levels = new Level[] {new Level(0), new Level(1), new Level(2)};
    /**
     * Current level
     */
    private int currentLevel;
    /**
     * Booleans for whether or not specific interactables have been used yet. Used to create tutorials.
     */
    private boolean inventoryUsed, storageDeviceUsed, computerUsed, anvilUsed, doorUsed;

    public Game(boolean loadSavedGame)
    {
        boolean createTutorial;
        if(loadSavedGame)
        {
            // Setting basic variables up.
            inventoryUsed = storageDeviceUsed = computerUsed = anvilUsed = doorUsed = true; // Assume the player has already used these.
            createTutorial = false;
        }
        else
            createTutorial = true;
        inventory = new Item[4];
        add(new LivingRoom(), "Living Room");
        add(new DiningRoom(), "Dining Room");
        add(new Kitchen(), "Kitchen");
        add(new Hallway(createTutorial), "Hallway");
        add(new CarrieRoom(), "Carrie's Room");
        add(new AliceRoom(createTutorial), "Alice's Room");
        add(new AvaRoom(), "Ava's Room");
        add(new FirstBathroom(), "Bathroom 1");
        add(new SecondBathroom(), "Bathroom 2");
        displayPanel("Alice's Room");
        if(loadSavedGame)
            loadGame();
        else
        {
            // Adding items to storage devices manually rather than checking through file
            for(int entry: Item.IDtoItem.keySet())
            {
                Item item = Item.IDtoItem.get(entry);
                if (!item.NAME.equals(Item.UTILITYKNIFE) && !item.NAME.equals(Item.SCISSORS))
                item.addToStorageDevices();
            }
        }
        try
        {
            new PrintWriter(new FileWriter("saveFile.txt")); // Resets the saved file to a blank file.
        }
        catch(IOException e) {}
    }

    /**
     * Helper method to load the game from a file.
     */
    private void loadGame()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
            for(int i = 0; i < inventory.length; i++) // Inventory items
            {
                String entry = br.readLine();
                if(!entry.equals("NULL"))
                {
                    String[] tokens = entry.split(" ");
                    int ID = Integer.parseInt(tokens[0]);
                    int quality = Integer.parseInt(tokens[1]);
                    inventory[i] = Item.IDtoItem.get(ID);
                    inventory[i] = inventory[i].changeQuality(quality);
                }
            }
            // Storage component items
            int storageComponentCount = Integer.parseInt(br.readLine());
            for(int x = 0; x < storageComponentCount; x++)
            {
                String component = br.readLine();
                String[] token = component.split(" ");
                int storageID = Integer.parseInt(token[0]);
                Item[] storage = StorageUnit.IDToStorageUnit.get(storageID).getStorage();
                for(int i = 0; i < storage.length; i++)
                {
                    String entry = br.readLine();
                    if(!entry.equals("NULL"))
                    {
                        String[] tokens = entry.split(" ");
                        int ID = Integer.parseInt(tokens[0]);
                        int quality = Integer.parseInt(tokens[1]);
                        storage[i] = Item.IDtoItem.get(ID);
                        storage[i] = storage[i].changeQuality(quality);
                    }
                }
            }
            currentLevel = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
        }
    }

    /**
     * Getter for level objects via the level number
     * @param level the current level number
     * @return the level object
     */
    public Level getLevel(int level)
    {
        return levels[level];
    }

    /**
     * Getter for inventory
     * @return the player inventory
     */
    public Item[] getInventory()
    {
        return inventory;
    }

    /**
     * Setter for inventory
     * @param inventory the player inventory
     */
    public void setInventory(Item[] inventory)
    {
        this.inventory = inventory;
    }

    /**
     * Getter for the current level number
     * @return the current level number
     */
    public int getCurrentLevel()
    {
        return currentLevel;
    }

    /**
     * Increments the level by 1.
     */
    public void incrementLevel()
    {
        currentLevel++;
    }

    /**
     * Checks whether or not inventory has been used yet (And a dialogue needs to show)
     * @return whether or not inventory has been used yet
     */
    public boolean isInventoryUsed()
    {
        return inventoryUsed;
    }

    /**
     * Setter for inventoryUsed
     * @param inventoryUsed whether inventory has been used yet
     */
    public void setInventoryUsed(boolean inventoryUsed)
    {
        this.inventoryUsed = inventoryUsed;
    }

    /**
     * Checks whether or not a storage device has been used yet (And a dialogue needs to show)
     * @return whether or not a storage device has been used yet
     */
    public boolean isStorageDeviceUsed()
    {
        return storageDeviceUsed;
    }

    /**
     * Setter for storageDeviceUsed
     * @param storageDeviceUsed whether or not storage device has been used yet
     */
    public void setStorageDeviceUsed(boolean storageDeviceUsed)
    {
        this.storageDeviceUsed = storageDeviceUsed;
    }

    /**
     * Checks whether or not computer has been used yet (And a dialogue needs to show)
     * @return whether or not computer has been used yet
     */
    public boolean isComputerUsed()
    {
        return computerUsed;
    }

    /**
     * Setter for computerUsed
     * @param computerUsed whether or not computer has been used yet
     */
    public void setComputerUsed(boolean computerUsed)
    {
        this.computerUsed = computerUsed;
    }

    /**
     * Checks whether or not anvil has been used yet (And a dialogue needs to show)
     * @return whether or not anvil has been used yet
     */
    public boolean isAnvilUsed()
    {
        return anvilUsed;
    }

    /**
     * Setter for anvilUsed
     * @param anvilUsed Whether or not anvil has been used yet
     */
    public void setAnvilUsed(boolean anvilUsed)
    {
        this.anvilUsed = anvilUsed;
    }

    /**
     * Checks whether or not door has been used yet (And a dialogue needs to show)
     * @return whether or not door has been used yet
     */
    public boolean isDoorUsed()
    {
        return doorUsed;
    }

    /**
     * Setter for doorUsed
     * @param doorUsed whether or not door has been used yet
     */
    public void setDoorUsed(boolean doorUsed)
    {
        this.doorUsed = doorUsed;
    }
}
