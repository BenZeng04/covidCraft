/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */

import processing.core.PApplet;

/**
 * @author Ben Zeng
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 30m
 * - May 28, 2020: Updated ~Ben Zeng. Time Spent: 20m
 * The application context which all graphics are displayed on. Can be instantiated and a specific screen can be set to be played.
 * Essentially an extention to the PApplet library, which includes several methods that allow for straight-forward screen switching.
 * @version 1
 */
public final class PApplication extends PApplet
{
    /**
     * The current screen which the application is displaying
     */
    private PScreen currentScreen;
    /**
     * Width and height for program
     */
    private final int WIDTH, HEIGHT;
    /**
     * Click delay upon mouse input
     */
    private final int CLICK_DELAY;
    /**
     * Frames since last click
     */
    private int frames_since_last_click;

    /**
     * Default constructor.
     */
    public PApplication()
    {
        this(100, 100, 2);
    }

    /**
     * More detailed constructor.
     *
     * @param WIDTH  the width
     * @param HEIGHT the height
     */
    public PApplication(int WIDTH, int HEIGHT)
    {
        this(WIDTH, HEIGHT, 2);
    }

    /**
     * More detailed constructor with click delay.
     *
     * @param WIDTH       the width
     * @param HEIGHT      the height
     * @param CLICK_DELAY the click delay
     */
    public PApplication(int WIDTH, int HEIGHT, int CLICK_DELAY)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.CLICK_DELAY = CLICK_DELAY;
    }

    @Override
    public void settings()
    {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void draw()
    {
        currentScreen.draw();
        frames_since_last_click++;
    }

    @Override
    public void mousePressed()
    {
        if(frames_since_last_click < CLICK_DELAY)
            return;
        currentScreen.mousePressed();
        frames_since_last_click = 0;
    }

    @Override
    public void keyPressed()
    {
        currentScreen.keyPressed();
    }

    /**
     * Sets and initializes the current screen.
     *
     * @param currentScreen the current screen
     */
    public void setCurrentScreen(PScreen currentScreen)
    {
        this.currentScreen = currentScreen;
        currentScreen.setContext(this);
        currentScreen.initialize();
    }

    /**
     * A getter for the current screen.
     *
     * @return the current screen that the application is playing
     */
    public PScreen getCurrentScreen()
    {
        return currentScreen;
    }
}
