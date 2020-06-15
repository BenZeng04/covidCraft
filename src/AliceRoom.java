import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AliceRoom extends GameplayRoom
{
    public AliceRoom(boolean createTutorial)
    {
        addHitBox(new HitBox(0, 10, 0, 1060, 320)); // Wall
        addHitBox(new HitBox(0, -500, 0, 30, 660)); // Left Border
        addHitBox(new HitBox(0, 0, 660, 1060, 1000)); // Bottom Border
        addHitBox(new HitBox(0, 1060, 0, 2600, 670)); // Right Border

        addHitBox(new HitBox(0, 210, 140, 410, 490)); //bed
        addHitBox(new HitBox(0, 20, 140, 650, 380)); //furniture at left
        addHitBox(new CraftingStation(0, 650, 240, 830, 380, 0)); //anvil
        addHitBox(new HitBox(0, 810, 120, 1050, 380)); //bulletin board
        addHitBox(new Computer(0,497,155,595,315)); // computer
        addHitBox(new Door(0, 560, 650, 720, 670, "Hallway")); //Door at the bottom

        if(createTutorial)
            startTutorial();
    }
    @Override
    public int getStartX()
    {
        return 500;
    }

    @Override
    public int getStartY()
    {
        return 500;
    }
    @Override
    public Image getRoomBackground()
    {
        try
        {
            return ImageIO.read(new File("House_CovidCraft/FINAL_Bed_Alice.png"));
        }
        catch(IOException e)
        {
            return null;
        }
    }
    private void startTutorial()
    {
        TutorialDialogue dialogueList[] =
            {
                new TutorialDialogue("Hello! Welcome to covidCraft!"),
                new TutorialDialogue("Each round’s objective is to craft some sort of \"ultimate item\".", "This item will be displayed at the top right corner of the screen."),
                new TutorialDialogue("You can move around using the WASD keys, as well as use the E key to open your", "player inventory!"),
                new TutorialDialogue("There are several rooms around this house, which all contain a variety of", "interactable objects! These objects will have a white outline whenever", "you go near them, click on them to use them and find more information!"),
                new TutorialDialogue("You are given a computer which you can use to view how to craft certain items."),
                new TutorialDialogue("In order to craft an item, you must go to Alice’s room and use the anvil."),
                new TutorialDialogue("Some items may require you to craft, or find certain other items as", "prerequisites."),
                new TutorialDialogue("These items can be found all across the house, at where you would", "typically find them!"),
                new TutorialDialogue("If your inventory every gets full, you can always store items you're", "not using at the moment in storage devices!"),
                new TutorialDialogue("If you ever need help, you can ask one of the NPCs in their rooms. ", "They will not only give you hints, but they can give you certain", "items that you will need to complete certain recipes! Be sure to check them!"),
                new TutorialDialogue("To leave a room, simply click on one of the doors in the room!"),
                new TutorialDialogue("Once you have all the items needed to craft the ultimate item, go to the anvil", "and craft it! You will then need to complete a \"mini-game\"", "to finish off the level, and receive the ultimate item! "),
                new TutorialDialogue("You will be rated on how well you perform on these mini-games, so make sure", "not to mess up!"),
                new TutorialDialogue("Once you finish all of that, exit through the yellow door in order to ", "complete the level.", "A cut-scene will be played, and you will promptly be returned back to your", "(Alice’s) room to begin finding new items! "),
                new TutorialDialogue("Congratulations! You have finished the tutorial!")
            };
        for(int i = 1; i < dialogueList.length; i++)
            dialogueList[i - 1].setNextDialogue(dialogueList[i]);
        addComponent(dialogueList[0]);
    }
    private class TutorialDialogue extends DialogueGUI
    {
        private TutorialDialogue nextDialogue;
        private Position highlight;
        private int currentFrame;
        private final int HIGHLIGHT_SIZE = 60;
        public TutorialDialogue(String... textLines)
        {
            super(textLines);
        }

        @Override
        public void draw(Graphics g)
        {
            currentFrame++;
            super.draw(g);
            if(getHighlight() != null)
            {
                int transparency = (int) (255 * Math.sin(currentFrame * Math.PI / 30)); // Using a sine wave for the highlight.
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

        public TutorialDialogue getNextDialogue()
        {
            return nextDialogue;
        }

        public void setNextDialogue(TutorialDialogue nextDialogue)
        {
            this.nextDialogue = nextDialogue;
        }

        public Position getHighlight()
        {
            return highlight;
        }

        public void setHighlight(Position highlight)
        {
            this.highlight = highlight;
        }
    }
}
