//Nathan Lu
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * Jun 16 2020: Created ~Nathan Lu, 20 mins
 * A subclass of the Button class that changes the screen of the program.
 * @version 1
 */
public class ScreenChangeButton extends Button {
    private String toScreen;
    /**
     * Constructor for this class
     * @param toScreen String for the screen the button will send the player to if clicked
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param length the length of the button
     * @param height the height of the button
     * @param borderThickness the thickness of the border of the button
     */
    public ScreenChangeButton(String toScreen, int x, int y, int length, int height, int borderThickness, int layer) // creates button outline
    {
        super(x, y, length, height, borderThickness, layer);
        this.toScreen = toScreen;
    }
    /**
     * Alternate constructor for this class
     * @param toScreen String for the screen the button will send the player to if clicked
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param length the length of the button
     * @param height the height of the button
     * @param borderThickness the thickness of the border of the button
     */
    public ScreenChangeButton(String toScreen, int x, int y, int length, int height, int borderThickness) // creates button outline
    {
        this(toScreen, x, y, length, height, borderThickness, 5);
    }

    /**
     * Method for what happens when the button is pressed
     * @param event The mouse event / location
     */
    public void buttonPressed(MouseEvent event)
    {
        addComponent(new TransitionEvent(toScreen));
    }
}