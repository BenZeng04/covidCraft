import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Ben Zeng
 * Revision History:
 * - Jun 1, 2020: Created ~Ben Zeng. Time Spent: 30m
 * The class used as a transition between two screens
 * @version 1
 */
public class TransitionEvent extends ScreenComponent
{
    /**
     * The transition time for the event
     */
    private final int TRANSITION_TIME;
    /**
     * Number of frames currently elapsed
     */
    private int framesElapsed;
    /**
     * Path representing which screen will be transitioned to
     */
    private String[] screenPath;

    /**
     * Default constructor
     * @param screenPath Path representing which screen will be transitioned to
     */
    public TransitionEvent(String... screenPath)
    {
        this(30, screenPath);
    }

    /**
     * More advanced constructor
     * @param transition Transition time
     * @param screenPath Path representing which screen will be transitioned to
     */
    public TransitionEvent(int transition, String... screenPath)
    {
        super(Integer.MAX_VALUE); // Must be maximum possible layer!
        TRANSITION_TIME = transition;
        this.screenPath = screenPath;
    }

    @Override
    public void draw(Graphics g)
    {
        framesElapsed++;
        g.setColor(new Color(0, 0, 0, framesElapsed * 255 / TRANSITION_TIME));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        if(framesElapsed == TRANSITION_TIME)
        {
            HostApplication application = HostApplication.getHost(getParentScreen());
            removeComponent(this); // Gets rid of this component
            JPanel panel = application.getPanelNested(screenPath); // Retrieves the screen that you need to go to
            if(panel instanceof MultiPanel) // If the screen contains multiple ScreenPanels (is a MultiPanel), rather than being a ScreenPanel itself, find the current leaf node using recursion.
                panel = ((MultiPanel) panel).getLeaf();
            ((ScreenPanel) panel).addComponent(new FadeOut()); // Add a FadeOut animated component to the new screen
            application.displayPanelNested(screenPath); // Set the current screen to the one specified by the screen path.
            // This component will now be flagged for deletion, however the new FadeOut instance will play a fade out animation for the new screen
        }
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        // Prevents other components from receiving any mouse events
        denyComponents();
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        // Prevents other components from receiving any key events
        denyComponents();
    }

    /**
     * Private helper class for the fade out transition
     */
    private class FadeOut extends ScreenComponent
    {
        public FadeOut()
        {
            super(Integer.MAX_VALUE);
        }

        @Override
        public void draw(Graphics g)
        {
            framesElapsed--;
            g.setColor(new Color(0, 0, 0, framesElapsed * 255 / TRANSITION_TIME));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            if(framesElapsed == 0)
                removeComponent(this);
        }

        @Override
        public void mousePressed(MouseEvent me)
        {
            denyComponents();
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
            denyComponents();
        }
    }
}
