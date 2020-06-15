/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Ben Zeng. Time Spent: 5m
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 1
 */
public abstract class GameplayRoom extends ScreenPanel
{
    private Image bg;
    private Player thisPlayer;
    private ArrayList<HitBox> hitBoxes;
    private boolean initialized;
    public GameplayRoom()
    {
        hitBoxes = new ArrayList<>();
    }
    public void addHitBox(HitBox hb)
    {
        addComponent(hb);
        hitBoxes.add(hb);
    }
    @Override
    public void draw(Graphics g)
    {
        if(!initialized)
        {
            thisPlayer = new Player(getStartX(), getStartY());
            addComponent(thisPlayer);
            bg = getRoomBackground();
            initialized = true;
        }
        g.drawImage(bg, 0, 0, 1080, 720, null);
    }
    public ArrayList<HitBox> getHitBoxes()
    {
        return hitBoxes;
    }
    public Player getThisPlayer()
    {
        return thisPlayer;
    }
    public abstract int getStartX();
    public abstract int getStartY();
    public abstract Image getRoomBackground();
}
