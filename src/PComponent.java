/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */


/**
 * Class representing a single component on the screen, that has its own unique set of listeners and graphics.
 */
public abstract class PComponent implements Comparable<PComponent>
{
    /**
     * An identifier for the layer of the component. Necessary when handling multi-layered components, with specific components on a higher level than others.
     */
    private final int layer;

    /**
     * The context for where everything is drawn.
     */
    private PApplication context;

    /**
     * Whether or not this component should be shown.
     */
    private boolean shown;

    /**
     * Default Constructor for PComponent.
     */
    public PComponent()
    {
        this(1);
    }

    /**
     * Constructor for PComponent.
     *
     * @param layer the layer of the component.
     */
    public PComponent(int layer)
    {
        this.layer = layer;
        shown = true;
    }

    @Override
    public final int compareTo(PComponent other)
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
     * Sets the context of a given application. Automatically called when adding a component to a screen.
     *
     * @param context
     */
    public final void setContext(PApplication context)
    {
        this.context = context;
    }

    /**
     * Getter for the context parameter.
     *
     * @return the context
     */
    public final PApplication getContext()
    {
        return context;
    }

    /**
     * Shows this component / unhides this component.
     */
    public final void show()
    {
        shown = true;
    }

    /**
     * Hides this component.
     */
    public final void hide()
    {
        shown = false;
    }

    /**
     * Ran directly from a PScreen. Performs a few un-overridable actions prior to calling run().
     */
    public final void draw()
    {
        if(shown)
            run();
    }

    /**
     * Effectively the draw function for an individual component.
     */
    public abstract void run();

    /**
     * Ran directly from a PScreen. Performs a few un-overridable actions prior to calling mousePressEvent().
     */
    public final boolean mousePressed()
    {
        if(shown)
            return mousePressEvent();
        return false;
    }

    /**
     * Effectively the mousePressed function for an individual component. Not necessary to override.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public boolean mousePressEvent()
    {
        return false;
    }

    /**
     * Ran directly from a PScreen. Performs a few un-overridable actions prior to calling keyPressEvent().
     */
    public final boolean keyPressed()
    {
        if(shown)
            return keyPressEvent();
        return false;
    }

    /**
     * Effectively the mousePressed function for an individual component. Not necessary to override.
     *
     * @return whether or not an event was triggered. If the 'true' value is returned, this prevents other components from being triggered to prevent a chain of events.
     */
    public boolean keyPressEvent()
    {
        return false;
    }
}