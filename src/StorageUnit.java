import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 5, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing the storage components
 * @version 1
 */
public class StorageUnit extends Interactable
{
    public static HashMap<Integer, StorageUnit> IDToStorageUnit = new HashMap<>();
    private final int INVENTORY_HEIGHT, INVENTORY_WIDTH;
    private final int INVENTORY_SIZE;
    private Item[] storage;
    private int id;
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
        else createGUI();
    }
    public void createGUI()
    {
        addComponent(new StorageUnitInventory(storage, getPlayerInventory()));
    }
    public Item[] getStorage()
    {
        return storage;
    }
    public int getID() {
        return id;
    }
    public boolean hasSpaceLeft()
    {
        for(Item item: storage)
            if(item == null)
                return true;
        return false;
    }
    public boolean addItem(Item toAdd)
    {
        ArrayList<Integer> availibleSlots = new ArrayList<>();
        for(int i = 0; i < storage.length; i++)
        {
            if(storage[i] == null)
                availibleSlots.add(i);
        }
        if(availibleSlots.isEmpty()) return false;
        int randomIndex = (int) (Math.random() * availibleSlots.size());
        storage[availibleSlots.get(randomIndex)] = toAdd;
        return true;
    }
    private class StorageUnitInventory extends InventoryGUI
    {
        private Item[] storageUnitInventory, playerInventory;
        public StorageUnitInventory(Item[] storageUnitInventory, Item[] playerInventory)
        {
            this.storageUnitInventory = storageUnitInventory; // It is important that the reference gets saved!
            this.playerInventory = playerInventory;
        }
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
