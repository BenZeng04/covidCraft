/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of additional libraries that will be useful throughout the coding process.
 *
 * @author Ben Zeng
 * Created on: 5/26/2020
 */
public class PScreen
{
    /**
     * The context for the given screen that the graphics are displayed on
     */
    private PApplication context;
    private ArrayList<PComponent> components;


    /**
     * Public constructor for PScreen
     */
    public PScreen()
    {
        components = new ArrayList<>();
    }

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
        mousePressEvent();
        Collections.sort(components);
        Collections.reverse(components); // Reverses so that components near the top are given the trigger first.
        for(PComponent component: components)
        {
            if(component.mousePressed())
                break;
        }
    }

    /**
     * Used as an additional mousePressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     */
    public void mousePressEvent()
    {
    }

    /**
     * Run directly from an application upon the click of the key. Not meant to be overridden, as it contains implementation for running components.
     */
    public final void keyPressed()
    {
        keyPressEvent();
        Collections.sort(components);
        for(PComponent component: components)
            if(component.keyPressed())
                break;
    }

    /**
     * Used as an additional keyPressed function, run prior to any of the components. Meant to be overridden, but not necessary.
     */
    public void keyPressEvent()
    {
    }

    /**
     * Adds a single component to the list of components.
     *
     * @param component
     */
    public void addComponent(PComponent component)
    {
        component.setContext(context);
        components.add(component);
    }
}