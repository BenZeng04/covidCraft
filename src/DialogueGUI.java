/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of more advanced elements of gameplay.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 11, 2020: Created ~Ben Zeng. Time Spent: 1h
 * The class representing a dialogue pop-up on the screen.
 * @version 1
 */
public abstract class DialogueGUI extends ScreenComponent
{
    /**
     * Constant for text font
     */
    private static final Font TEXT_FONT = new Font("monospaced", Font.BOLD, 20);
    /**
     * Smaller font at the bottom
     */
    private static final Font SMALL_FONT = new Font("monospaced", Font.PLAIN, 12);
    /**
     * The image for dialogue boxes
     */
    private static final Image DIALOGUE_BOX;

    static // Initialize the dialogue box
    {
        Image image;
        try
        {
            image = ImageIO.read(new File("Backgrounds/DialogueBox.png"));
        }
        catch(IOException e)
        {
            image = null;
        }
        DIALOGUE_BOX = image;
    }

    /**
     * Text for the dialogue
     */
    private String text;
    /**
     * Whether or not the dialogue is displayed at the top or bottom
     */
    private boolean displayAtTop;

    /**
     * Default constructor
     * @param text the text
     */
    public DialogueGUI(String text)
    {
        this(false, text);
    }

    /**
     * More advanced constructor
     * @param displayAtTop
     * @param text
     */
    public DialogueGUI(boolean displayAtTop, String text)
    {
        super(1005); // High layer that is slightly above InventoryGUI.
        this.text = text;
        this.displayAtTop = displayAtTop;
    }

    @Override
    public void draw(Graphics g)
    {
        if(getParentScreen() instanceof GameplayRoom)
        {
            Player player = ((GameplayRoom) getParentScreen()).getThisPlayer();
            if(player.isCurrentlyMoving())
                player.haltPlayer(); // Player should not be able to move while this dialogue is open
        }
        final int WIDTH = Game.GAME_WIDTH, HEIGHT = Game.GAME_HEIGHT;
        final int OFFSET = 40;
        final int Y_OFFSET = displayAtTop? -400: 0;
        final int BOX_WIDTH = WIDTH - OFFSET * 2, BOX_HEIGHT = BOX_WIDTH / 5;
        g.drawImage(DIALOGUE_BOX, OFFSET, HEIGHT - OFFSET - BOX_HEIGHT + Y_OFFSET, BOX_WIDTH, BOX_HEIGHT, null);
        g.setFont(TEXT_FONT);
        g.setColor(Color.BLACK);
        String[] words = text.split(" ");
        String currentLine = "";
        int lineCount = 0;
        for(int i = 0; i < words.length; i++)
        {
            if(currentLine.length() + words[i].length() >= 75)
            {
                final int POSITION = HEIGHT - OFFSET - BOX_HEIGHT + lineCount * 24;
                g.drawString(currentLine, OFFSET + 30, POSITION + 50 + Y_OFFSET);
                currentLine = "";
                lineCount++;
            }
            currentLine += words[i] + " ";
        }
        if(currentLine.length() > 0)
            g.drawString(currentLine,OFFSET + 30, HEIGHT - OFFSET - BOX_HEIGHT + lineCount * 24 + 50 + Y_OFFSET);

        g.setFont(SMALL_FONT);
        g.drawString("- CLICK TO CONTINUE -", OFFSET + 430, HEIGHT - OFFSET - 30 + Y_OFFSET);
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        whenExited();
        denyComponents(); // Disable interactions with any other component.
        removeComponent(this); // Close this dialogue off upon pressing the mouse
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        denyComponents(); // Make sure that key presses do NOT get triggered.
    }

    /**
     * Represents the Event that gets triggered upon exiting.
     */
    public abstract void whenExited();
}
