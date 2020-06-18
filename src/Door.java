import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 5, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing the storage components
 * @version 1
 */
public class Door extends Interactable
{
    private String toScreen;
    public Door(int layer, int xStart, int yStart, int xEnd, int yEnd, String toScreen)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
        this.toScreen = toScreen;
    }
    public void whenInteractedWith()
    {
        addComponent(new TransitionEvent(30, "Gameplay", toScreen));
    }
    @Override
    public void whenInRange(Graphics2D g)
    {
        g.setColor(new Color(0xB45FFF));
        g.setStroke(new BasicStroke(5));
        g.drawRect(getXStart(), getYStart(), getXEnd()-getXStart(), getYEnd()-getYStart());
        g.setColor(new Color(255, 255, 255, 75));
        g.fillRect(getXStart(), getYStart(), getXEnd()-getXStart(), getYEnd()-getYStart());
        Game game = getGame();
        if(!game.isDoorUsed()) // Create a tutorial dialogue showing the player the door.
        {
            getGame().setDoorUsed(true);
            // Tutorial dialogue box pops up if user never opened their inventory
            TutorialDialogue dialogue = new TutorialDialogue(true,
                "This purple outline is a door! Click on it to be sent to a new room! Each room has a unique set of objects and items you can find for crafting! When you're done, simply navigate back to this room."
            );
            dialogue.setHighlight(new Position((getXStart() + getXEnd()) / 2, (getYStart() + getYEnd()) / 2));
            addComponent(dialogue);
        }
    }
}
