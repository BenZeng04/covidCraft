/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */

import java.awt.*;
import java.awt.event.*;

/**
 * @author Ben Zeng
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 1h
 * - June 9, 2020: Updated ~Ben Zeng. Time Spent: 5m (Added Key and Mouse release listeners)
 * Class representing a single component on the screen, that has its own unique set of listeners and graphics.
 * @version 1
 */
public class ScreenComponent implements Comparable<ScreenComponent>
{

    private boolean mouseHeld;
    private boolean keyHeld;
    /**
     * The screen which the given component is inside of.
     */
    private ScreenPanel parentScreen;

    /**
     * An identifier for the layer of the component. Necessary when handling multi-layered components, with specific components on a higher level than others.
     */
    private final int layer;

    /**
     * Default Constructor for PComponent.
     */
    public ScreenComponent()
    {
        this(1);
    }

    /**
     * Constructor for PComponent.
     *
     * @param layer the layer of the component.
     */
    public ScreenComponent(int layer)
    {
        this.layer = layer;
    }

    @Override
    public final int compareTo(ScreenComponent other)
    {
        return Integer.compare(getLayer(), other.getLayer());
    }

    /**
     * Getter for the layer parameter.
     *
     * @return the layer
     */
    public final int getLayer()
    {
        return layer;
    }

    /**
     * Effectively the draw function for an individual component.
     */
    public void draw(Graphics g)
    {

    }

    /**
     * Effectively the mousePressed function for an individual component. Not necessary to override.
     */
    public void mousePressed(MouseEvent event)
    {
    }

    /**
     * Effectively the keyPressed function for an individual component. Not necessary to override.
     */
    public void keyPressed(KeyEvent event)
    {
    }

    /**
     * Effectively the mouseReleased function for an individual component. Not necessary to override.
     *
     */
    public void mouseReleased(MouseEvent event)
    {

    }

    /**
     * Effectively the keyReleased function for an individual component. Not necessary to override.
     *
     */
    public void keyReleased(KeyEvent event)
    {

    }

    /**
     * Returns the parent screen.
     *
     * @return The parent screen
     */
    public ScreenPanel getParentScreen()
    {
        return parentScreen;
    }

    /**
     * Sets the parent screen.
     *
     * @param parentScreen the parent screen
     */
    public void setParentScreen(ScreenPanel parentScreen)
    {
        this.parentScreen = parentScreen;
    }

    public boolean isKeyHeld()
    {
        return keyHeld;
    }

    public boolean isMouseHeld()
    {
        return mouseHeld;
    }

    public void denyComponents()
    {
        getParentScreen().denyComponents();
    }

    public void addComponent(ScreenComponent sc)
    {
        getParentScreen().addComponent(sc);
    }

    public void removeComponent(ScreenComponent sc)
    {
        getParentScreen().removeComponent(sc);
    }

    public HostApplication getHostPanel()
    {
        return HostApplication.getHost(getParentScreen());
    }

    public void setMouseHeld(boolean mouseHeld)
    {
        this.mouseHeld = mouseHeld;
    }

    public void setKeyHeld(boolean keyHeld)
    {
        this.keyHeld = keyHeld;
    }
}