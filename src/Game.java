/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

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
    private BufferedReader br;
    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 720;
    private Item[] inventory;
    private Level[] levels = new Level[]{new Level(0),new Level(1),new Level(2)};
    private int currentLevel;
    private boolean inventoryUsed, storageDeviceUsed, computerUsed, anvilUsed, doorUsed;
    private ArrayList<GameplayRoom> rooms = new ArrayList<GameplayRoom>();
    public Game(boolean loadSavedGame)
    {
        boolean createTutorial;
        if(loadSavedGame)
        {
            // Setting basic variables up.
            inventoryUsed = storageDeviceUsed = computerUsed = anvilUsed = doorUsed = true; // Assume the player has already used these.
            createTutorial = false;
        }
        else createTutorial = true;
        currentLevel= 0; // I tinkered with this a little bit; TODO
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
        {
            loadGame(this);
        }
        else
        {
            for(int entry: Item.IDtoItem.keySet())
            {
                Item item = Item.IDtoItem.get(entry);
                item.setQuality(-1);
                item.addToStorageDevices();
            }
        }
    }
    public void loadGame(Game g) {
        try {
            br = new BufferedReader(new FileReader("saveFile.txt"));
        }
        catch(Exception e){}

        //getting player inventory
        String playerInventory = "";
        try {
            playerInventory = br.readLine();
        }
        catch (Exception e) {}

        //setting player inventory
        String [] individualIDs = playerInventory.split(" ");
        Item [] inventory = g.getInventory();
        for (int x=0; x<individualIDs.length; x++) {
            String curID = individualIDs[x];
            int integerVersionOfID = Integer.parseInt(curID);
            inventory[x] = Item.IDtoItem.get(integerVersionOfID);
        }
        //getting locations of items
        HashMap<String, JPanel> IDToRoom = g.IDtoJPanel();
        String cur = null;
        try {
            cur = br.readLine();
        }
        catch (Exception e) {}
        while (cur!=null) {
            GameplayRoom curRoom = (GameplayRoom) IDToRoom.get(cur);
            try {
                cur = br.readLine();
            }
            catch (Exception e) {}
            while (!cur.equals("exit")) {
                StorageUnit curStorage = findStorageUnit(curRoom, Integer.parseInt(cur));
                int index = 0;
                while (!cur.equals("exit")) {
                    Item [] curStorageArray = curStorage.getStorage();
                    int integerItemID = Integer.parseInt(cur);;
                    curStorageArray[index] = Item.IDtoItem.get(integerItemID);
                    try {
                        cur = br.readLine();
                    }
                    catch (Exception e) {}
                }
                try {
                    cur = br.readLine();
                }
                catch (Exception e) {}
            }
            try {
                cur = br.readLine();
            }
            catch (Exception e) {}
        }
    }
    private StorageUnit findStorageUnit(GameplayRoom gpr, int desiredID) {
        ArrayList<HitBox> furniture = gpr.getHitBoxes();
        for (HitBox hb : furniture) {
            if (hb instanceof StorageUnit) { //if the HitBox is a StorageUnit
                if (((StorageUnit) hb).getID()==desiredID) {
                    return (StorageUnit) hb;
                }
            }
        }
        return null;
    }
    public void add(GameplayRoom panel, String panelID)
    {
        super.add(panel, panelID);
        rooms.add(panel);
    }
    public Level getLevel(int level)
    {
        return levels[level];
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

    public int getCurrentLevel(){
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

    public boolean isDoorUsed()
    {
        return doorUsed;
    }

    public void setDoorUsed(boolean doorUsed)
    {
        this.doorUsed = doorUsed;
    }
}
