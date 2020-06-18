import java.awt.*;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 11, 2020: Created ~Ben Zeng. Time Spent: 1h
 * The class representing a dialogue pop-up on the screen, specifically for the tutorial.
 * @version 1
 */
public class TutorialDialogue extends DialogueGUI
{
    /**
     * Static helper method to start and create tutorial dialogues
     * @param room the room
     */
    public static void startTutorial(GameplayRoom room)
    {
        TutorialDialogue[] dialogueList =
            {
                new TutorialDialogue("Hello! Welcome to covidCraft!"),
                new TutorialDialogue("Each round’s objective is to craft some sort of \"ultimate item\". This item will be displayed at the top right corner of the screen."),
                new TutorialDialogue("You can move around using the WASD keys, as well as use the E key to open your player inventory!"),
                new TutorialDialogue("There are several rooms around this house, which all contain a variety of interactable objects! These objects will have a white outline whenever you go near them, click on them to use them and find more information!"),
                new TutorialDialogue("You are given a computer which you can use to view how to craft certain items."),
                new TutorialDialogue("In order to craft an item, you must go to Alice’s room and use the anvil."),
                new TutorialDialogue("Some items may require you to craft, or find certain other items asprerequisites."),
                new TutorialDialogue("These items can be found all across the house, at where you would typically find them!"),
                new TutorialDialogue("If your inventory ever gets full, you can always store items you're not using at the moment in storage devices!"),
                new TutorialDialogue("If you ever need help, you can ask one of the NPCs in their rooms. They will not only give you hints, but they can give you certain items that you will need to complete certain recipes! Be sure to check them!"),
                new TutorialDialogue("Once you have all the items needed to craft the ultimate item, go to the anvil and craft it! You will then need to complete a \"mini-game\" to finish off the level, and receive the ultimate item! "),
                new TutorialDialogue("You will be rated on how well you perform on these mini-games, so make sure not to mess up!"),
                new TutorialDialogue("Once you finish all of that, exit through the yellow door in order to complete the level. A cut-scene will be played, and you will promptly be returned back to your (Alice’s) room to begin finding new items! "),
                new TutorialDialogue("Congratulations! You have finished the tutorial!")
            };
        dialogueList[1].setHighlight(new Position(1000, 115)); // Highlight the ultimate item
        dialogueList[4].setHighlight(new Position(540, 210)); // Computer
        dialogueList[5].setHighlight(new Position(735, 315)); // Anvil

        for(int i = 1; i < dialogueList.length; i++)
            dialogueList[i - 1].setNextDialogue(dialogueList[i]);
        room.addComponent(dialogueList[0]);
    }

    /**
     * The next dialogue that gets played after clicking this
     */
    private TutorialDialogue nextDialogue;
    /**
     * What item should be highlighted
     */
    private Position highlight;
    /**
     * Current frame upon starting
     */
    private int currentFrame;
    /**
     * Constant for highlight size
     */
    private final static int HIGHLIGHT_SIZE = 120;

    /**
     * Default constructor
     * @param textLines The text
     */
    public TutorialDialogue(String textLines)
    {
        super(textLines);
    }

    /**
     * More advanced constructor
     * @param displayAtTop Whether or not displayed at the top
     * @param textLines The text
     */
    public TutorialDialogue(boolean displayAtTop, String textLines)
    {
        super(displayAtTop, textLines);
    }
    @Override
    public void draw(Graphics g)
    {
        currentFrame++;
        super.draw(g);
        if(getHighlight() != null)
        {
            int transparency = (int) (127 * Math.sin(currentFrame * Math.PI / 30)) + 127; // Using a sine wave for the highlight.

            g.setColor(new Color(255, 255, 255, transparency));
            g.fillOval(highlight.getX() - HIGHLIGHT_SIZE / 2, highlight.getY() - HIGHLIGHT_SIZE / 2, HIGHLIGHT_SIZE, HIGHLIGHT_SIZE);
        }
    }
    @Override
    public void whenExited()
    {
        if(nextDialogue != null)
            addComponent(getNextDialogue());
    }

    /**
     * Returns the next dialogue
     * @return the next dialogue
     */
    public TutorialDialogue getNextDialogue()
    {
        return nextDialogue;
    }

    /**
     * Setter for next dialogue
     * @param nextDialogue the next dialogue
     */
    public void setNextDialogue(TutorialDialogue nextDialogue)
    {
        this.nextDialogue = nextDialogue;
    }

    /**
     * Getter for highlight position
     * @return the position
     */
    public Position getHighlight()
    {
        return highlight;
    }

    /**
     * Setter for highlight position
     * @param highlight the position
     */
    public void setHighlight(Position highlight)
    {
        this.highlight = highlight;
    }
}