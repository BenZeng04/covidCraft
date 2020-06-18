/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Ben Zeng. Time Spent: 5m
 * - June 1, 2020: Updated ~Ben Zeng. Time Spent: 25m
 * - June 15, 2020: Updated ~Oscar Han. Time Spent: 10m
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 1
 */
public abstract class GameplayRoom extends ScreenPanel
{
    /**
     * Background of the room
     */
    private Image bg, objective;

    /**
     * The player inside of this specific room
     */
    private Player thisPlayer;
    /**
     * List of hitboxes / collisions in this room
     */
    private ArrayList<HitBox> hitBoxes;
    /**
     * Whether or not player has been initialized
     */
    private boolean initialized;

    /**
     * Default constructor.
     */
    public GameplayRoom()
    {
        hitBoxes = new ArrayList<>();
    }

    /**
     * Adds a hitbox as both a component, and to the list of hitboxes.
     * @param hb The hitbox
     */
    public void addHitBox(HitBox hb)
    {
        addComponent(hb);
        hitBoxes.add(hb);
    }

    @Override
    public void draw(Graphics g)
    {
        Game gm = (Game) getParent();
        if(!initialized)
        {
            thisPlayer = new Player(getStartX(), getStartY());
            addComponent(thisPlayer);
            bg = getRoomBackground();
            initialized = true;
            objective = gm.getLevel(0).getObjective().ICON;
        }
        Level level = gm.getLevel(gm.getCurrentLevel());
        // Displaying the objective.
        objective = level.getObjective().ICON; // game current level
        g.drawImage(bg, 0, 0, 1080, 720, null);
        g.setColor(new Color(255, 255, 255, 125));
        g.fillOval(945, 60, 110, 110);
        g.setColor(new Color(255, 0, 0, 125));
        for(Item userItems: gm.getInventory())
        {
            if(userItems != null && userItems.ID == level.getObjective().ID)
            {
                g.setColor(new Color(0, 128, 0, 125));
                break;
            }
        }
        g.fillOval(950, 65, 100, 100);
        g.drawImage(objective, 960, 75, 80, 80, null);
    }

    /**
     * Getter for hitboxes
     * @return hitboxes
     */
    public ArrayList<HitBox> getHitBoxes()
    {
        return hitBoxes;
    }

    /**
     * Getter for the player
     * @return the player
     */
    public Player getThisPlayer()
    {
        return thisPlayer;
    }

    /**
     * Getter for the starting position of the player
     * @return starting x position
     */
    public abstract int getStartX();

    /**
     * Getter for the starting position of the player
     * @return starting y position
     */
    public abstract int getStartY();

    /**
     * Background image for the room.
     * @return the background
     */
    public abstract Image getRoomBackground();
}
