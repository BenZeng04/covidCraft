import java.awt.*;

/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 5, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing the storage components
 * @version 1
 */

public class Roommate extends StorageUnit
{
    private String [] possibleResponses;
    public Roommate(int layer, int xStart, int yStart, int xEnd, int yEnd, int id)
    {
        this(layer, xStart, yStart, xEnd, yEnd, 2, 5, id);
    }
    public Roommate(int layer, int xStart, int yStart, int xEnd, int yEnd, int height, int width, int id)
    {
        super(layer, xStart, yStart, xEnd, yEnd, height, width, id);
        possibleResponses = new String [] {
            ""
        };
    }
    public void whenInteractedWith()
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
        createGUI();
    }
    public void createGUI()
    {
        super.createGUI();
    }
    public Item[] getStorage()
    {
        return super.getStorage();
    }
    public int getID() {
        return super.getID();
    }
    public void draw(Graphics g) {

    }
}


