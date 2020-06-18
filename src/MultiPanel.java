/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of additional libraries that will be useful throughout the coding process.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 3, 2020: Created ~Ben Zeng. Time Spent: 20m
 * - June 9, 2020: Updated ~Ben Zeng. Time Spent: 5m (Added Key and Mouse release listeners)
 * - June 12, 2020: Updated ~Ben Zeng. Time Spent: 5m
 * An extension of the built-in JPanel which allows for easy switching of screens / panels. Any given JPanel can be set as the current screen.
 * Alternatively, another MultiPanel can be added as a screen to this structure, allowing for "Nested MultiPanels".
 * This can effectively create a "tree" structure, as each panel in this structure can be set to either be a ScreenPanel (A leaf node, where graphic logic is controlled, such as MainMenu or Instructions)
 * Or a MultiPanel, containing "child nodes" of it's own (Such as Game, which contains all the different rooms.)
 * @version 1
 */
public class MultiPanel extends JPanel
{
    /**
     * The layout of the given panel
     */
    private CardLayout layout;
    /**
     * The current screen being displayed
     */
    private JPanel currentScreen;
    /**
     * Maps String IDs to screens
     */
    private HashMap<String, JPanel> map;

    /**
     * Default constructor for MultiPanel.
     */
    public MultiPanel()
    {
        layout = new CardLayout();
        map = new HashMap<>();
        setLayout(layout);
    }

    /**
     * Adds a panel to the layout. This panel can now be "switched" to using its ID. Takes into account whether or not the panel is a MultiPanel.
     * @param panel The panel
     * @param panelID the ID of the panel
     */
    public final void add(JPanel panel, String panelID)
    {
        if(!(panel instanceof ScreenPanel) && !(panel instanceof MultiPanel)) throw new IllegalArgumentException("Invalid Panel Type!");
        map.put(panelID, panel);
        if(currentScreen == null) currentScreen = panel; // Sets a default screen to prevent nulls
        super.add(panel, panelID);
    }

    /**
     * Sets a panel to be displayed via its ID.
     * @param panelID the ID
     */
    public final void displayPanel(String panelID)
    {
        currentScreen = map.get(panelID);
        layout.show(this, panelID);
    }

    /**
     * Getter for a panel via its ID.
     * @param panelID the ID
     * @return the panel
     */
    public final JPanel getPanel(String panelID)
    {
        return map.get(panelID);
    }

    /**
     * Displays a screen directly from the host. Useful when there are nested MultiPanels creating a tree structure.
     *
     * @param IDs Each panel's ID through the tree structure.
     */
    public final void displayPanelNested(String... IDs)
    {
        JPanel current = this;
        for(String panelID: IDs)
        {
            ((MultiPanel) current).displayPanel(panelID);
            current = ((MultiPanel) current).getPanel(panelID);
        }
    }
    /**
     * Returns a screen directly from the host. Useful when there are nested MultiPanels creating a tree structure.
     *
     * @param IDs Each panel's ID through the tree structure.
     */
    public final JPanel getPanelNested(String... IDs)
    {
        JPanel current = this;
        for(String panelID: IDs)
            current = ((MultiPanel) current).getPanel(panelID);
        return current;
    }
    /**
     * Utilizes recursion to find the "leaf node" of the application structure.
     */
    public final ScreenPanel getLeaf()
    {
        if(currentScreen instanceof ScreenPanel)
            return (ScreenPanel) currentScreen; // The current screen is already a leaf node (It isn't a multi panel)
        return ((MultiPanel) currentScreen).getLeaf(); // The current screen is NOT a leaf node (The current screen contains multiple screens that can be switched and displayed)
    }

    public HashMap<String, JPanel> IDtoJPanel() {
        return map;
    }
    public HashMap<JPanel, String> JPaneltoID() {
        HashMap<JPanel, String> reverse = new HashMap<>();
        for (String x : map.keySet()) {
            reverse.put(map.get(x), x);
        }
        return reverse;
    }
}