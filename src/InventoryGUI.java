/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Gameplay Elements
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 16, 2020: Added item descriptions ~ Nathan Lu. Time Spent: 30 mins
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
     * Constant for the spacing between items on the screen.
     */
    public final static int ITEM_SPACING = ITEM_SIZE * 6 / 5;

    public InventoryGUI()
    {
        super(1000);
        selectedItemIndex = -1;
    }

    /**
     * Private method to draw an item border
     *
     * @param position the position
     * @param g        graphics context
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
     *
     * @param item     the item
     * @param position the position
     * @param g        graphics context
     */
    private static void drawItemIcon(Item item, Position position, Position mouseLocation, Graphics g)
    {
        if(item != null)
        {
            final int CONSTANT_OFFSET = ITEM_SIZE * 4 / 10;
            g.drawImage(item.ICON, position.getX() - CONSTANT_OFFSET, position.getY() - CONSTANT_OFFSET, CONSTANT_OFFSET * 2, CONSTANT_OFFSET * 2, null);
            if(HitBox.pointRectCollision(position.getX() - CONSTANT_OFFSET, position.getY() - CONSTANT_OFFSET, CONSTANT_OFFSET * 2, CONSTANT_OFFSET * 2, mouseLocation
                .getX(), mouseLocation.getY()))
            {
                // Item description HERE
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 20));
                g.drawString(item.NAME, 800, position.getY());
                g.setFont(new Font("monospaced", Font.PLAIN, 15));
                String currentLine = "";
                int lineCount = 0;
                if(item.QUALITY != -1)
                {
                    g.drawString("Item Quality: " + item.QUALITY + "%", 800, position.getY() + 22);
                    lineCount++;
                }
                String[] words = item.DESCRIPTION.split(" ");
                for(int i = 0; i < words.length; i++)
                {
                    if(currentLine.length() + words[i].length() >= 25)
                    {
                        g.drawString(currentLine, 800, position.getY() + 30 + lineCount * 15);
                        currentLine = "";
                        lineCount++;
                    }
                    currentLine += words[i] + " ";
                }
                if(currentLine.length() > 0)
                {
                    g.drawString(currentLine, 800, position.getY() + 30 + lineCount * 15);
                }
            }
        }
    }

    /**
     * The current selected item when using the inventory GUI.
     */
    private int selectedItemIndex;

    @Override
    public void draw(Graphics g)
    {
        g.setColor(TRANSPARENT_GRAY);
        g.fillRect(0, 0, 1080, 720); // Background
        Position[] positions = getItemLocations();
        Position mouseLocation = new Position(getParentScreen().getMouseX(), getParentScreen().getMouseY());
        int count = 0;
        // Drawing the items
        for(int i = 0; i < size(); i++)
        {
            Item item = get(i);
            drawItemBorder(positions[count], g);
            if(selectedItemIndex == -1 || i != selectedItemIndex)
                drawItemIcon(item, positions[count], mouseLocation, g);
            count++;
        }

        // Drawing the selected item if there is one
        if(selectedItemIndex != -1)
            drawItemIcon(get(selectedItemIndex), mouseLocation, mouseLocation, g);
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
            if(HitBox.pointRectCollision(position.getX() - ITEM_SIZE / 2, position.getY() - ITEM_SIZE / 2, ITEM_SIZE, ITEM_SIZE, me
                .getX(), me.getY()))
            {
                if(selectedItemIndex != -1)
                {
                    boolean emptySpot = get(i) == null;
                    // Swap the items.
                    Item temp = get(i);
                    set(i, get(selectedItemIndex));
                    set(selectedItemIndex, temp);
                    if(emptySpot || selectedItemIndex == i)
                        selectedItemIndex = -1;
                }
                else if(get(i) != null)
                    selectedItemIndex = i;
            }
        }
        denyComponents();
    }

    /**
     * Meant to be overridden. Get method for where the item slots should be displayed.
     *
     * @return An array of co-ordinates the size of the inventory
     */
    public abstract Position[] getItemLocations();

    /**
     * Getter for items in the InventoryGUI. Should correspond to the VISIBLE inventory of this GUI (Including both storage and normal player inventory).
     *
     * @param index the index
     * @return the item
     */
    public abstract Item get(int index);

    /**
     * Setter for items in the InventoryGUI. Should correspond to the VISIBLE inventory of this GUI (Including both storage and normal player inventory).
     *
     * @param index the index
     * @param item  the item
     */
    public abstract void set(int index, Item item);

    /**
     * Returns the number of item slots in this GUI
     *
     * @return item slots
     */
    public abstract int size();
}


