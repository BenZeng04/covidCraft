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
}
