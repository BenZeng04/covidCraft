/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 5, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing the storage components
 * @version 1
 */
public class StorageUnit extends Interactable
{
    private static int currentID;
    private final int INVENTORY_HEIGHT, INVENTORY_WIDTH;
    private final int INVENTORY_SIZE;
    private Item[] storage;
    private int id;
    public StorageUnit(int layer, int xStart, int yStart, int xEnd, int yEnd, int id)
    {
        this(layer, xStart, yStart, xEnd, yEnd, 2, 5, id);
        this.id = id;
    }
    public StorageUnit(int layer, int xStart, int yStart, int xEnd, int yEnd, int height, int width, int id)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
        INVENTORY_HEIGHT = height;
        INVENTORY_WIDTH = width;
        INVENTORY_SIZE = Player.INVENTORY_SIZE + INVENTORY_HEIGHT * INVENTORY_WIDTH;
        storage = new Item[INVENTORY_HEIGHT * INVENTORY_WIDTH];
        this.id = id;
    }
    public void whenInteractedWith()
    {
        if(!getGame().isStorageDeviceUsed())
        {
            getGame().setStorageDeviceUsed(true);
            // Tutorial dialogue box pops up if user never opened their inventory
            addComponent(new DialogueGUI("This is a storage device! Storage devices can either be used to store items,", "and are where you can find items to use for crafting! Simply drag an item", "from the device down into your inventory.")
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
    private class StorageUnitInventory extends InventoryGUI
    {
        private Item[] storageUnitInventory;
        public StorageUnitInventory(Item[] storageUnitInventory, Item[] playerInventory)
        {
            this.storageUnitInventory = storageUnitInventory; // It is important that the reference gets saved!
            Item[] combinedInventory = new Item[INVENTORY_SIZE];
            for(int i = 0; i < playerInventory.length; i++) // first 4 items represent player inventory
                combinedInventory[i] = playerInventory[i];
            for(int i = playerInventory.length; i < combinedInventory.length; i++)
                combinedInventory[i] = storageUnitInventory[i - playerInventory.length];
            setInventory(combinedInventory);
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
        public void inventoryUpdateEvent(Item[] newInventory)
        {
            Item[] playerInventory = getPlayerInventory();
            for(int i = 0; i < playerInventory.length; i++) // first 4 items represent player inventory
                playerInventory[i] = newInventory[i];
            for(int i = playerInventory.length; i < newInventory.length; i++)
                storageUnitInventory[i - playerInventory.length] = newInventory[i];
        }
    }
}
