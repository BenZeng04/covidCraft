/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ben Zeng
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 45m
 * - May 29, 2020: Updated ~Ben Zeng. Time Spent: 15m
 * Class representing the current screen which the application context must display. Contains multiple components that are all a part of the screen.
 * @version 1
 */
public class PScreen
{
    /**
     * Stores components that need to be added
     */
    private Queue<PComponent> pendingAdditions;
    /**
     * Stores components that need to be deleted
     */
    private Queue<PComponent> pendingDeletions;
    /**
     * The context for the given screen that the graphics are displayed on
     */
    private PApplication context;
    /**
     * List of all components in the screen
     */
    private ArrayList<PComponent> components;


    /**
     * Public constructor for PScreen
     */
    public PScreen()
    {
        components = new ArrayList<>();
        pendingDeletions = new LinkedList<>();
        pendingAdditions = new LinkedList<>();
    }

    /**
     * Sets the context for the given screen. Automatically called when setting the current screen of an application.
     *
     * @param context the context
     */
    public final void setContext(PApplication context)
    {
        this.context = context;
    }

    /**
     * Getter for the context
     * Can be final, as there is no need to override this.
     *
     * @return the context
     */
    public final PApplication getContext()
    {
        return context;
    }

    /**
     * Used to initialize components. Meant to be overridden by subclasses, but not necessary.
     */
    public void initialize()
    {
    }

    /**
     * Run directly from an application, several times per second. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void draw()
    {
        run();
        Collections.sort(components);
        for(PComponent component: components)
            component.draw();
        clearPending();
    }

    /**
     * Used as an additional draw function, run prior to any of the components. Meant to be overridden, but not necessary.
     */
    public void run()
    {

    }

    /**
     * Run directly from an application upon the click of the mouse. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void mousePressed()
    {
        if(mousePressEvent())
            return;
        Collections.sort(components);
        Collections.reverse(components); // Reverses so that components near the top are given the trigger first.
        for(PComponent component: components)
        {
            if(component.mousePressed())
                break;
        }
        clearPending();
    }

    /**
     * Used as an additional mousePressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public boolean mousePressEvent()
    {
        return false;
    }

    /**
     * Run directly from an application upon the click of the key. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void keyPressed()
    {
        if(keyPressEvent())
            return;
        Collections.sort(components);
        for(PComponent component: components)
        {
            if(component.keyPressed())
                break;
        }
        clearPending();
    }

    /**
     * Used as an additional keyPressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public boolean keyPressEvent()
    {
        return false;
    }

    /**
     * Adds a single component to the list of components.
     *
     * @param component the component to add
     * @return successful or not
     */
    public boolean addComponent(PComponent component)
    {
        if(component.getParentScreen() != null) return false;
        pendingAdditions.add(component);
        return true;
    }

    /**
     * Removes the given component from the list of components.
     *
     * @param component the component
     * @return successful or not
     */
    public boolean removeComponent(PComponent component)
    {
        if(components.contains(component))
        {
            pendingDeletions.add(component);
            return true;
        }
        return false;
    }

    /**
     * Getter for the list of components. Ideally should not need to be used in a program.
     *
     * @return the list
     */
    public ArrayList<PComponent> getComponents()
    {
        return components;
    }

    /**
     * Removes all components that need to be deleted, and adds all components that need to be added..
     */
    private void clearPending()
    {
        while(!pendingAdditions.isEmpty())
        {
            PComponent component = pendingAdditions.poll();
            component.setContext(context);
            component.setParentScreen(this);
            components.add(component);
        }
        while(!pendingDeletions.isEmpty())
        {
            PComponent component = pendingDeletions.poll();
            component.setContext(null);
            component.setParentScreen(null);
            components.remove(component);
        }
    }
}