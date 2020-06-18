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
    private String [] specialItemResponses;
    private String [] inventoryNotEmptyResponses;
    private String [] genericResponses;
    private Item importantItem;
    private Image sprite;
    public Roommate(int layer, int xStart, int yStart, int xEnd, int yEnd, int id, Image icon, Item importantItem)
    {
        this(layer, xStart, yStart, xEnd, yEnd, 1, 4, id, icon, importantItem);
    }
    public Roommate(int layer, int xStart, int yStart, int xEnd, int yEnd, int height, int width, int id, Image icon, Item importantItem)
    {
        super(layer, xStart, yStart, xEnd, yEnd, height, width, id);
        this.importantItem = importantItem;
        addItem(importantItem);
        sprite = icon;
        specialItemResponses = new String [] {
            "Be careful with this...",
            "You're making homemade protective equipment? You may need this. Be careful with them, though.",
            "Do you need this? It's dangerous, so use it carefully.",
            "It's nice that you're making protective equipment for yourself. Don't get hurt with this, though!"
        };
        inventoryNotEmptyResponses = new String [] {
                "You left this with me.",
                "Did you need this?"
        };
        genericResponses = new String [] {
            "Making protective equipment yourself is a really important skill. I should do that sometime too.",
            "Are you looking for something? Make sure you check every nook and cranny!",
            "Can't find something? I remember there being some useful materials in some hard to reach places...",
            "How did you get that anvil? Aren't these things really expensive? And heavy?"
        };
    }


    public void whenInteractedWith()
    {
        // choosing a line to say
        String line="";
        if (inventoryContainsImportantItem(getStorage())) {
            int random = (int)(Math.random()*specialItemResponses.length);
            line = specialItemResponses[random];
        }
        else if (inventoryNotEmpty(getStorage())) {
            int random = (int)(Math.random()*inventoryNotEmptyResponses.length);
            line = inventoryNotEmptyResponses[random];
        }
        else {
            int random = (int)(Math.random()*genericResponses.length);
            line = genericResponses[random];
        }
        addComponent(new DialogueGUI(line)
        {
            @Override
            public void whenExited()
            {
                createGUI();
            }
        });
    }
    public boolean inventoryContainsImportantItem(Item [] inventory) {
        for (int x=0; x<inventory.length; x++) {
            if (inventory[x]!= null && importantItem.equals(inventory[x])) {
                return true;
            }
        }
        return false;
    }
    public boolean inventoryNotEmpty(Item [] inventory) {
        for (int x=0; x<inventory.length; x++) {
            if (inventory[x] != null) {
                return true;
            }
        }
        return false;
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
        super.draw(g);
        g.drawImage(sprite, this.getXStart(), this.getYStart(), this.getXEnd()-this.getXStart(), this.getYEnd()-this.getYStart(), null);
    }
}


