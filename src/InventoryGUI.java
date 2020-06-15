/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Gameplay Elements
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 9, 2020: Created ~Ben Zeng. Time Spent: 1h
 * An abstract GUI for all inventory variants that can show up, such as the player's inventory, or opening storage utilities.
 * @version 1
 */
public abstract class InventoryGUI extends ScreenComponent
{
    /**
     * Transparent gray constant colour.
     */
    public final static Color TRANSPARENT_GRAY = new Color(0, 0, 0, 114);
    /**
     * Transparent white constant colour.
     */
    public final static Color TRANSPARENT_WHITE = new Color(255, 255, 255, 114);
    /**
     * Constant for the pixel width of an item on the screen.
     */
    public final static int ITEM_SIZE = 60;
    /**
     *  Constant for the spacing between items on the screen.
     */
    public final static int ITEM_SPACING = ITEM_SIZE * 6 / 5;

    /**
     * Private method to draw an item border
     * @param position the position
     * @param g graphics context
     */
    private static void drawItemBorder(Position position, Graphics g)
    {
        g.setColor(TRANSPARENT_WHITE);
        g.fillRect(position.getX() - ITEM_SIZE / 2, position.getY() - ITEM_SIZE / 2, ITEM_SIZE, ITEM_SIZE);
        final int BORDER = ITEM_SIZE * 9 / 20;
        g.setColor(TRANSPARENT_GRAY);
        g.fillRect(position.getX() - BORDER, position.getY() - BORDER, BORDER * 2, BORDER * 2);
    }
    /**
     * Private method to draw an item icon
     * @param item the item
     * @param position the position
     * @param g graphics context
     */
    private static void drawItemIcon(Item item, Position position, Position mouseLocation, Graphics g)
    {
        if(item != null)
        {
            final int CONSTANT_OFFSET = ITEM_SIZE * 4 / 10;
            g.drawImage(item.ICON, position.getX() - CONSTANT_OFFSET, position.getY() - CONSTANT_OFFSET, CONSTANT_OFFSET * 2, CONSTANT_OFFSET * 2, null);
            if(HitBox.pointRectCollision(mouseLocation.getX(), mouseLocation.getY(), position.getX() - CONSTANT_OFFSET, position.getY() - CONSTANT_OFFSET, CONSTANT_OFFSET * 2, CONSTANT_OFFSET * 2))
            {
                // Draw the item description
            }
        }
    }

    /**
     * The current selected item when using the inventory GUI.
     */
    private int selectedItemIndex;
    /**
     * The items inside of the inventory used by the GUI.
     */
    private Item[] inventory;

    /**
     * Default constructor.
     */
    public InventoryGUI()
    {
        this(null);
    }

    /**
     * Constructor that initializes inventory.
     * @param inventory the inventory
     */
    public InventoryGUI(Item[] inventory)
    {
        super(1000); // Super high layer
        this.setInventory(inventory);
        selectedItemIndex = -1;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(TRANSPARENT_GRAY);
        g.fillRect(0, 0, 1080, 720); // Background
        Position[] positions = getItemLocations();
        Position mouseLocation = new Position(getParentScreen().getMouseX(), getParentScreen().getMouseY());
        int count = 0;
        // Drawing the items
        for(Item item: inventory)
        {
            drawItemBorder(positions[count], g);
            if(selectedItemIndex == -1 || item != inventory[selectedItemIndex])
                drawItemIcon(item, positions[count], mouseLocation, g);
            count++;
        }

        // Drawing the selected item if there is one
        if(selectedItemIndex != -1)
            drawItemIcon(inventory[selectedItemIndex], mouseLocation, mouseLocation, g);
    }
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE || ke.getKeyChar() == 'e' || ke.getKeyChar() == 'E')
            removeComponent(this); // Close this screen off!
        denyComponents();
    }
    @Override
    public void mousePressed(MouseEvent me)
    {
        Position[] positions = getItemLocations();
        for(int i = 0; i < positions.length; i++)
        {
            Position position = positions[i];
            if(HitBox.pointRectCollision(position.getX() - ITEM_SIZE / 2, position.getY() - ITEM_SIZE / 2, ITEM_SIZE, ITEM_SIZE, me.getX(), me.getY()))
            {
                if(selectedItemIndex != -1)
                {
                    boolean emptySpot = inventory[i] == null;
                    // Swap the items.
                    Item temp = inventory[i];
                    inventory[i] = inventory[selectedItemIndex];
                    inventory[selectedItemIndex] = temp;
                    inventoryUpdateEvent(getInventory());
                    if(emptySpot) selectedItemIndex = -1;
                }
                else if(inventory[i] != null) selectedItemIndex = i;
            }
        }
        denyComponents();
    }

    /**
     * Meant to be overridden. Get method for where the item slots should be displayed.
     * @return An array of co-ordinates the size of the inventory
     */
    public abstract Position[] getItemLocations();

    /**
     * Gets called whenever the inventory gets updated. The subclass should utilize the GUI's inventory and update the original inventory accordingly.
     * @param newInventory the new inventory
     */
    public abstract void inventoryUpdateEvent(Item[] newInventory);

    /**
     * Getter for inventory.
     * @return the inventory
     */
    public Item[] getInventory()
    {
        return inventory;
    }

    /**
     * Setter for inventory.
     * @param inventory the inventory
     */
    public void setInventory(Item[] inventory)
    {
        this.inventory = inventory;
    }
}


