import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 8, 2020: Added Inventory GUI Ben Zeng. Time Spent: 10m
 * - Jun 5, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing the storage components
 * @version 1
 */
public class StorageUnit extends Interactable
{
    /**
     * The Hash Map that converts IDs to StorageUnits
     */
    public static HashMap<Integer, StorageUnit> IDToStorageUnit = new HashMap<>();
    /**
     * The variables that determine the length and width of the inventory of this StorageUnit
     */
    private final int INVENTORY_HEIGHT, INVENTORY_WIDTH;
    /**
     * The variable denoting the size of the array
     */
    private final int INVENTORY_SIZE;
    /**
     * The array of items in the inventory
     */
    private Item[] storage;
    /**
     * The ID of the StorageUnit, used to save and load games
     */
    private int id;

    /**
     * Default constructor, creates a hitbox from (xStart, yStart) to (xEnd, yEnd)
     * @param layer The layer
     * @param xStart The x-start for the hitbox
     * @param yStart The y-start for the hitbox
     * @param xEnd The x-end for the hitbox
     * @param yEnd The y-end for the hitbox
     */
    public StorageUnit(int layer, int xStart, int yStart, int xEnd, int yEnd, int height, int width, int id)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
        INVENTORY_HEIGHT = height;
        INVENTORY_WIDTH = width;
        INVENTORY_SIZE = Player.INVENTORY_SIZE + INVENTORY_HEIGHT * INVENTORY_WIDTH;
        storage = new Item[INVENTORY_HEIGHT * INVENTORY_WIDTH];
        this.id = id;
        IDToStorageUnit.put(id, this);
    }

    @Override
    public void whenInteractedWith()
    {
        if(!getGame().isStorageDeviceUsed())
        {
            getGame().setStorageDeviceUsed(true);
            // Tutorial dialogue box pops up if user never opened their inventory
            addComponent(new DialogueGUI("This is a storage device! Storage devices can either be used to store items, and are where you can find items to use for crafting! Simply drag an item from the device down into your inventory.")
            {
                @Override
                public void whenExited()
                {
                    createGUI();
                }
            });
        }
        else
            createGUI();
    }

    /**
     * Creates the GUI menu that allows the player to access the contents of the StorageUnit
     */
    public void createGUI()
    {
        addComponent(new StorageUnitInventory(storage, getPlayerInventory()));
    }

    //Accessors
    public Item[] getStorage()
    {
        return storage;
    }

    public int getID()
    {
        return id;
    }

    /**
     * Checks if there's still an empty space in the array
     */
    public boolean hasSpaceLeft()
    {
        for(Item item: storage)
            if(item == null)
                return true;
        return false;
    }

    /**
     * Adds the item toAdd to this storage component
     * @param toAdd the item to add
     * @return successful or not
     */
    public boolean addItem(Item toAdd)
    {
        ArrayList<Integer> availibleSlots = new ArrayList<>();
        for(int i = 0; i < storage.length; i++)
        {
            if(storage[i] == null)
                availibleSlots.add(i);
        }
        if(availibleSlots.isEmpty())
            return false;
        int randomIndex = (int) (Math.random() * availibleSlots.size());
        storage[availibleSlots.get(randomIndex)] = toAdd;
        return true;
    }

    /**
     * Helper class used for the GUI
     */
    private class StorageUnitInventory extends InventoryGUI
    {
        private Item[] storageUnitInventory, playerInventory;

        public StorageUnitInventory(Item[] storageUnitInventory, Item[] playerInventory)
        {
            this.storageUnitInventory = storageUnitInventory; // It is important that the reference gets saved!
            this.playerInventory = playerInventory;
        }

        /**
         * Helper method to find the start location when displaying item slots
         * @param middleLocation the middle location
         * @param spacing spacing distance of items
         * @param count item count
         * @return the position
         */
        private int findStart(int middleLocation, int spacing, int count)
        {
            return middleLocation - (spacing * (count - 1) / 2);
        }

        @Override
        public Position[] getItemLocations()
        {
            Position[] ret = new Position[INVENTORY_SIZE];

            for(int i = 0; i < Player.INVENTORY_SIZE; i++)
                ret[i] = new Position(findStart(Game.GAME_WIDTH / 2, ITEM_SPACING, Player.INVENTORY_SIZE) + ITEM_SPACING * i, Game.GAME_HEIGHT * 3 / 4);

            for(int i = 0; i < INVENTORY_HEIGHT; i++)
            {
                for(int j = 0; j < INVENTORY_WIDTH; j++)
                {
                    int index = i * INVENTORY_WIDTH + j + Player.INVENTORY_SIZE;
                    ret[index] = new Position(findStart(Game.GAME_WIDTH / 2, ITEM_SPACING, INVENTORY_WIDTH) + ITEM_SPACING * j, findStart(Game.GAME_HEIGHT / 4, ITEM_SPACING, INVENTORY_HEIGHT) + ITEM_SPACING * i);
                }
            }
            return ret;
        }

        @Override
        public Item get(int index)
        {
            if(index < playerInventory.length)
                return playerInventory[index];
            else
                return storageUnitInventory[index - playerInventory.length];
        }

        @Override
        public void set(int index, Item item)
        {
            if(index < playerInventory.length)
                playerInventory[index] = item;
            else
                storageUnitInventory[index - playerInventory.length] = item;
        }

        @Override
        public int size()
        {
            return playerInventory.length + storageUnitInventory.length;
        }
    }
}
