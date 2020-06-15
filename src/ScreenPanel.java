/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 * Resources Used as Help: https://stackoverflow.com/questions/2668718/java-mouselistener
 * https://stackoverflow.com/questions/10876491/how-to-use-keylistener
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Ben Zeng, Oscar Han
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 45m
 * - May 29, 2020: Updated ~Ben Zeng. Time Spent: 15m
 * - June 3, 2020: Updated ~Ben Zeng. Time Spent: 30m
 * - June 3, 2020: Updated ~Oscar Han. Time Spent: 30m
 * - June 9, 2020: Updated ~Ben Zeng. Time Spent: 5m (Added Key and Mouse release listeners)
 * Class representing the current screen which the application must display. Contains multiple components that are all a part of the screen.
 * @version 1
 */
public class ScreenPanel extends JPanel
{
    private boolean pendingMouseRelease;
    private boolean pendingKeyRelease;
    private boolean mouseHeld;
    private boolean keyHeld;
    private KeyEvent key;
    private MouseEvent lastMouse;
    /**
     * Helper variable used to help suppress components from being run.
     */
    private boolean overridden;
    /**
     * Stores components that need to be added
     */
    private Queue<ScreenComponent> pendingAdditions;
    /**
     * Stores components that need to be deleted
     */
    private Queue<ScreenComponent> pendingDeletions;
    /**
     * List of all components in the screen
     */
    private ArrayList<ScreenComponent> components;

    /**
     * Public constructor for PScreen
     */
    public ScreenPanel()
    {
        components = new ArrayList<>();
        pendingDeletions = new LinkedList<>();
        pendingAdditions = new LinkedList<>();
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                // Calls the overridable mousePressEvent function.
                mousePressEvent(event);
            }
            @Override
            public void mouseReleased(MouseEvent event)
            {
                // Calls the overridable mousePressEvent function.
                mouseReleaseEvent(event);
            }
        });
        // Key Events will be forwarded from the MultiPanel.
    }

    /**
     * Used to prevent other components from being triggered, to prevent a chain of events. Flags the remainder of components to not receive any keypress or mousepress events.
     */
    public void denyComponents()
    {
        overridden = true;
    }

    @Override
    public final void paint(Graphics g)
    {
        clearPending();
        draw(g);
        Collections.sort(components);
        for(ScreenComponent component: components)
            component.draw(g);
        clearPending();
    }

    /**
     * Used as an additional draw function, run prior to any of the components. Meant to be overridden, but not necessary.
     */
    public void draw(Graphics g)
    {

    }

    /**
     * Run directly from an application upon the click of the mouse. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void mousePressEvent(MouseEvent event)
    {
        overridden = false;
        clearPending();
        mousePressed(event);
        Collections.sort(components);
        Collections.reverse(components); // Reverses so that components near the top are given the trigger first.
        for(ScreenComponent component: components)
        {
            if(overridden)
                continue;
            component.mousePressed(event);
            component.setMouseHeld(true);
        }
        mouseHeld = true;
        lastMouse = event;
        clearPending();
    }

    /**
     * Used as an additional mousePressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     */
    public void mousePressed(MouseEvent event)
    {
    }

    /**
     * Run directly from an application upon the release of the mouse. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void mouseReleaseEvent(MouseEvent event)
    {
        clearPending();
        if(mouseHeld) mouseReleased(event);
        Collections.sort(components);
        Collections.reverse(components); // Reverses so that components near the top are given the trigger first.
        for(ScreenComponent component: components)
        {
            if(component.isMouseHeld())
            {
                component.mouseReleased(event);
                component.setMouseHeld(false);
            }
        }
        mouseHeld = false;
        clearPending();
    }

    /**
     * Used as an additional mouseReleased function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public void mouseReleased(MouseEvent event)
    {
    }

    /**
     * Run directly from an application upon the click of the key. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void keyPressEvent(KeyEvent event)
    {
        overridden = false;
        clearPending();
        keyPressed(event);
        Collections.sort(components);
        Collections.reverse(components);
        for(ScreenComponent component: components)
        {
            if(overridden)
                continue;
            component.keyPressed(event);
            component.setKeyHeld(true);
        }
        keyHeld = true;
        key = event;
        clearPending();
    }

    /**
     * Used as an additional keyPressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     */
    public void keyPressed(KeyEvent event)
    {
    }

    /**
     * Run directly from an application upon the release of the key. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void keyReleaseEvent(KeyEvent event)
    {
        clearPending();
        if(keyHeld) keyReleased(event);
        Collections.sort(components);
        Collections.reverse(components);
        for(ScreenComponent component: components)
        {
            if(component.isKeyHeld())
            {
                component.keyReleased(event);
                component.setKeyHeld(false);
            }
        }
        keyHeld = false;
        clearPending();
    }

    /**
     * Used as an additional keyPressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public void keyReleased(KeyEvent event)
    {
    }

    /**
     * Adds a single component to the list of components.
     *
     * @param component the component to add
     * @return successful or not
     */
    public boolean addComponent(ScreenComponent component)
    {
        if(component.getParentScreen() != null)
            throw new RuntimeException("Error: Cannot add the same component to multiple screens.");
        pendingAdditions.add(component);
        return true;
    }

    /**
     * Removes the given component from the list of components.
     *
     * @param component the component
     * @return successful or not
     */
    public boolean removeComponent(ScreenComponent component)
    {
        if(components.contains(component))
        {
            pendingDeletions.add(component);
            return true;
        }
        return false;
    }

    @Override
    public MultiPanel getParent()
    {
        return (MultiPanel) super.getParent();
    }

    /**
     * Removes all components that need to be deleted, and adds all components that need to be added..
     */
    private void clearPending()
    {
        if(pendingMouseRelease)
        {
            pendingMouseRelease = false;
            mouseReleaseEvent(lastMouse);
        }
        if(pendingKeyRelease)
        {
            pendingKeyRelease = false;
            keyReleaseEvent(key);
        }
        while(!pendingAdditions.isEmpty())
        {
            ScreenComponent component = pendingAdditions.poll();
            component.setParentScreen(this);
            components.add(component);
        }
        while(!pendingDeletions.isEmpty())
        {
            ScreenComponent component = pendingDeletions.poll();
            component.setParentScreen(null);
            components.remove(component);
        }
    }

    public boolean isMouseHeld()
    {
        return mouseHeld;
    }

    public boolean isKeyHeld()
    {
        return keyHeld;
    }

    public char getKey()
    {
        return key.getKeyChar();
    }

    public int getMouseX()
    {
        // Getting the current mouse location. https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, this);
        return (int) mouseLocation.getX();
    }

    public int getMouseY()
    {
        // Getting the current mouse location. https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, this);
        return (int) mouseLocation.getY();
    }

    /**
     * Simulates a key release using the most recently pressed key
     */
    public void simulateKeyRelease()
    {
        pendingKeyRelease = true;
    }

    /**
     * Simulates a mouse release using the most recent mouse press
     */
    public void simulateMouseRelease()
    {
        pendingMouseRelease = true;
    }
}