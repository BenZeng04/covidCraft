/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 * RESOURCES: https://stackoverflow.com/questions/16316132/animate-jpanel-slide-in-with-timer
 */

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 4, 2020: Created ~Ben Zeng. Time Spent: 20m
 * - June 9, 2020: Updated ~Ben Zeng. Time Spent: 5m (Added Key and Mouse release listeners)
 * An extension of the custom MultiPanel which allows for animations and key listeners between multiple panels. Any given JPanel can be set as the current screen and animated. Meant to be the main "host" for the application.
 * @version 1
 */
public class HostApplication extends MultiPanel
{
    /**
     * Timer used for animation
     */
    private Timer animations;

    /**
     * Default constructor.
     */
    public HostApplication()
    {
        super();
        animations = new Timer(0, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                // Calls the paint function from here. Because of how JComponent.repaint() automatically calls repaint() for its children, no additional code is necessary
                repaint();
            }
        });
        animations.start();
        animations.setDelay(16);
        setFocusable(true);
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent event)
            {
                // Calls the keyPressEvent function from here.
                getLeaf().keyPressEvent(event);
            }
            @Override
            public void keyReleased(KeyEvent event)
            {
                // Calls the keyPressEvent function from here.
                getLeaf().keyReleaseEvent(event);
            }
        });
    }

    /**
     * Returns the root of a panel in the context of MultiPanels in an Application.
     *
     * @param panel the panel
     * @return the root
     */
    public static HostApplication getHost(JPanel panel)
    {
        while(!(panel instanceof HostApplication))
            panel = (JPanel) panel.getParent();
        return (HostApplication) panel;
    }
}
