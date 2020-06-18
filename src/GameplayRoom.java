/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of the first few basic screens in the program.
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Ben Zeng. Time Spent: 5m
 * Some edits here and there- Oscar lel top right image
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 1
 */
public abstract class GameplayRoom extends ScreenPanel
{
    private Image bg, objective;
    private int transitionLevel; // TODO- kinda sketch monkey

    private Player thisPlayer;
    private ArrayList<HitBox> hitBoxes;
    private boolean initialized;
    private Game gm;

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
            transitionLevel= 0;
            thisPlayer = new Player(getStartX(), getStartY());
            addComponent(thisPlayer);
            bg = getRoomBackground();
            initialized = true;
            gm= (Game) getParent();
            objective= gm.getLevel(0).getObjective().ICON;
        }
        Level level = gm.getLevel(gm.getCurrentLevel());
        if(transitionLevel != gm.getCurrentLevel()) { // TODO
            objective = level.getObjective().ICON; // game current level
            transitionLevel = gm.getCurrentLevel();
        }

        g.drawImage(bg, 0, 0, 1080, 720, null);

        g.setColor(new Color(255,255,255,125));
        g.fillOval(945,60,110,110);

        g.setColor(new Color(255,0,0,125));
        for(Item userItems: gm.getInventory()){ // TODO
           if(userItems != null && userItems.ID == level.getObjective().ID){
                g.setColor(new Color(0,128,0,125));
                break;
           }
        }
        // g.fillRect(950,65,100,100); // TODO- test
        g.fillOval(950,65,100,100);
        g.drawImage(objective,960,75,80,80, null);
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
