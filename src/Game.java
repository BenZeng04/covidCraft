/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */
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
    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 720;
    private Item[] inventory;
    // private Level[] levels = new Level[]{new Level(0),new Level(1),new Level(2)};
    private int currentLevel;
    private boolean inventoryUsed, storageDeviceUsed, computerUsed, anvilUsed;
    private ArrayList<GameplayRoom> rooms = new ArrayList<GameplayRoom>();
    public Game(boolean loadSavedGame)
    {
        if(loadSavedGame)
        {
            inventoryUsed = storageDeviceUsed = computerUsed = anvilUsed = true; // Assume the player has already used these.
        }
        boolean createTutorial = true; // could also be false
        currentLevel= 0;
        inventory = new Item[4];
        // inventory[0] = new Item(Item.loadImage(""))
        add(new LivingRoom(), "Living Room");
        add(new DiningRoom(), "Dining Room");
        add(new Kitchen(), "Kitchen");
        add(new Hallway(), "Hallway");
        add(new CarrieRoom(), "Carrie's Room");
        add(new AliceRoom(createTutorial), "Alice's Room");
        add(new AvaRoom(), "Ava's Room");
        add(new FirstBathroom(), "Bathroom 1");
        add(new SecondBathroom(), "Bathroom 2");
        displayPanel("Alice's Room"); // Player starts out in the living room.
    }
    public void add(GameplayRoom panel, String panelID)
    {
        super.add(panel, panelID);
        rooms.add(panel);
    }
    public ArrayList<GameplayRoom> getRooms() {
        return rooms;
    }
    public Item[] getInventory()
    {
        return inventory;
    }

    public void setInventory(Item[] inventory)
    {
        this.inventory = inventory;
    }

    public int getLevel(){
        return currentLevel;
    }
    public void incrementLevel(){
        currentLevel++;
    }

    public boolean isInventoryUsed()
    {
        return inventoryUsed;
    }

    public void setInventoryUsed(boolean inventoryUsed)
    {
        this.inventoryUsed = inventoryUsed;
    }

    public boolean isStorageDeviceUsed()
    {
        return storageDeviceUsed;
    }

    public void setStorageDeviceUsed(boolean storageDeviceUsed)
    {
        this.storageDeviceUsed = storageDeviceUsed;
    }

    public boolean isComputerUsed()
    {
        return computerUsed;
    }

    public void setComputerUsed(boolean computerUsed)
    {
        this.computerUsed = computerUsed;
    }

    public boolean isAnvilUsed()
    {
        return anvilUsed;
    }

    public void setAnvilUsed(boolean anvilUsed)
    {
        this.anvilUsed = anvilUsed;
    }
}
