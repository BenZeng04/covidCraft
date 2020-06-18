import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * @author Oscar Han
 * Revision History:
 * Jun 15 2020: Updated ~Nathan Lu, 10m
 * Jun 11 2020: Created ~Oscar Han, 20m
 * Class that creates a a clickable and customizable button within a screen.
 * @version 1
 */
public class Button extends ScreenComponent
{
    /**
     * Attributes for display
     */
    private int x, y, length, height, borderThickness;
    /**
     * Colours and colour modes
     */
    private Color color, borderStay, borderChange;
    /**
     * Button text
     */
    private String text;
    /**
     * Text display location
     */
    private int textX, textY, size;
    /**
     * The colour for the text
     */
    private Color textColor;

    /**
     * Default constructor with no set layer
     * @param x x position
     * @param y y position
     * @param length the length
     * @param height the height
     * @param borderThickness the thickness of the border
     */
    public Button(int x, int y, int length, int height, int borderThickness) // creates button outline
    {
        this(x, y, length, height, borderThickness, 5);
    }

    /**
     * More detailed constructor with layet
     * @param x x position
     * @param y y position
     * @param length the length
     * @param height the height
     * @param borderThickness the thickness of the border
     * @param layer the layer
     */
    public Button(int x, int y, int length, int height, int borderThickness, int layer) // creates button outline
    {
        super(layer);
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.borderThickness = borderThickness;
        color = new Color(0);
        borderStay = new Color(0);
        borderChange = new Color(0);
        text = "";
        textX = 0;
        textY = 0;
        size = 0;
        textColor = new Color(255);
    }

    /**
     * Sets the colour, border colour, and colour when mouse is hovered over it
     * @param color original colour fill
     * @param borderStay original border colour
     * @param borderChange mouse hover
     */
    public void setColor(Color color, Color borderStay, Color borderChange) // sets color and highlight of the button
    {
        this.color = color;
        this.borderStay = borderStay;
        this.borderChange = borderChange;
    }

    /**
     * Sets the text for the button
     * @param text The text
     * @param textX The x-position
     * @param textY The y-position
     * @param size Font size
     * @param color Text colour
     */
    public void setText(String text, int textX, int textY, int size, Color color) // text + text placement
    {
        this.text = text;
        this.textX = textX;
        this.textY = textY;
        this.size = size;
        this.textColor = color;
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        if(isInArea(event))
            buttonPressed(event);
    }

    /**
     * Event that gets called when the button gets pressed
     * @param event The mouse event / location
     */
    public void buttonPressed(MouseEvent event)
    {

    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, length, height);
        g.setColor(borderStay);
        if(getParentScreen().getMouseX() >= x && getParentScreen().getMouseX() <= x + length && getParentScreen().getMouseY() >= y && getParentScreen()
            .getMouseY() <= y + height)
        g.setColor(borderChange);
        g.fillRect(x, y, length, borderThickness);
        g.fillRect(x, y + height - borderThickness, length, borderThickness);
        g.fillRect(x, y, borderThickness, height);
        g.fillRect(x + length - borderThickness, y, borderThickness, height);
        g.setColor(textColor);
        g.setFont(new Font("monospaced", Font.PLAIN, size)); // change font
        g.drawString(text, textX, textY);
    }

    /**
     * Helper method to determine whether or not the mouse is in range
     * @param event the mouse position
     * @return whether or not the mouse is in range
     */
    public boolean isInArea(MouseEvent event)
    {
        return event.getX() >= x && event.getX() <= x + length && event.getY() >= y && event.getY() <= y + height;
    }
}