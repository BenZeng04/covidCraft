import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - Jun 17 2020: Improved scoring system and transitions ~Nathan Lu, 30 mins
 * - Jun 16 2020: Updated with images ~Nathan Lu, 1 hr
 * - Jun 12 2020: Created ~Nathan Lu, 30 mins
 * Class representing a single room within the levels. A "room" is a section of the house, such as the living room or bedroom, and contains a variety of interactable components.
 * @version 3
 */
public class PouringMinigame extends ScreenPanel
{
    /*
    Background for when the player is not pouring
     */
    private Image notPouring;
    /*
    Background for when the player is pouring alcohol
     */
    private Image pouringAlcohol;
    /*
    Background for when the player is pouring essential oil
     */
    private Image pouringEssentialOil;
    /*
    Background that will be displayed (chosen from other 3 images)
     */
    private Image curBackground;
    /*
    state variable that denotes whether the player is preparing to pour, pouring, or reviewing results
    */
    private int isPouring; // 0 is not pouring, 1 is pouring, 2 and 3 is for viewing results
    /*
    state variable for which of the 4 stages of the mini-game, the player is on: instructions, pouring rubbing alcohol, pouring essential oil, and viewing results/exiting
    */
    private char curStage; //'i' for instructions, 'a' for rubbing alcohol, 'e' for essential oil, 'x' for exit
    /*
    When the current liquid started pouring
    */
    private long startTime;
    /*
    How long the current liquid pour
    */
    private long elapsedTime;
    /*
    The final score of the mini-game and the quality of the item
    */
    private int finalScore;
    /*
    The storage unit that the new created item will be put in
     */
    private StorageUnit storageUnit;
    /*
    The base item for the quality creation
     */
    private Item item;

    /**
     * Constructorfor the minigame
     */
    public PouringMinigame(Item baseItem, StorageUnit toAdd)
    {
        item = baseItem;
        storageUnit = toAdd;
        notPouring = Item.loadImage("Backgrounds/PouringMinigameBackground1.png");
        pouringAlcohol = Item.loadImage("Backgrounds/PouringMinigameBackground2.png");
        pouringEssentialOil = Item.loadImage("Backgrounds/PouringMinigameBackground3.png");
        curBackground = notPouring;
        isPouring = 0;
        curStage = 'i';
        finalScore = 100;
        addComponent(new DialogueGUI("Here's your second mini-game! Here, we will simulate a part of making the hand sanitizer. Usually, while mixing, you would carefully measure out the amount needed with measuring devices, but what's the fun in that? Instead, for this mini-game, we will instead be using time to measure out the amount we need. See how close to the desired time you can get!")
        {
            @Override
            public void whenExited()
            {
                curStage = 'a';
            }
        });
    }
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(curBackground, 0, 0, 1080, 720, null);
        display(g);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(8));
    }
    @Override
    public void mousePressed(MouseEvent event) {
        if(curStage == 'i')
        {
            curStage = 'a';
        }
        else if(curStage != 'x')
        {
            isPouring = isPouring + 1;
            isPouring %= 4;
            if (isPouring == 1) {
                startTime = System.currentTimeMillis();
            }
            else if (isPouring == 2) {
                elapsedTime = System.currentTimeMillis() - startTime;
                long intendedTime = 0;
                if (curStage == 'a')
                    intendedTime = 4000;
                else if (curStage == 'e')
                    intendedTime = 1500;

                int scoreToSubtract = (int) (Math.abs(elapsedTime-intendedTime)/50);
                finalScore -= scoreToSubtract;
            }
            else if (isPouring == 3){
                if(curStage == 'a')
                {
                    curStage = 'e';
                }
                else if (curStage == 'e') {
                    if (finalScore<0) {
                        finalScore = 0;
                    }
                    storageUnit.addItem(item.changeQuality(finalScore));
                    addComponent(new DialogueGUI("Congratulations! You have completed this mini-game with a score of " + finalScore + "%! Press the \"Return\" button to continue playing.")
                    {
                        @Override
                        public void whenExited()
                        {
                            ScreenChangeButton button = new ScreenChangeButton("Gameplay", 385, 490, 330, 75, 5);
                            button.setText("Return",482, 537, 40, new Color(0x68D4FF));
                            button.setColor(new Color(0x3B6EFF), new Color(0x02A0FF), new Color(0x68D4FF));
                            button.setColor(Color.BLACK, Color.BLACK, Color.WHITE);
                            addComponent(button);
                        }
                    });
                    curStage = 'x';
                }
            }
        }
    }

    /**
     * Helper method for display
     * @param g The graphics context
     */
    public void display(Graphics g) {
        if (curStage == 'a') {
            displayTime(4, "rubbing alcohol", g);
            if (isPouring==1) {
                curBackground = pouringAlcohol;
            }
            else if (isPouring==2 || isPouring==3) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 20));
                double elapsedTimeInSeconds = elapsedTime/1000.0;
                g.drawString( "Your time is: "+elapsedTimeInSeconds, 650, 280);
                curBackground = notPouring;
            }
            else if (isPouring==0) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 20));
                g.drawString( "Click to begin!", 650, 280);
                curBackground = notPouring;
            }
        }
        else if (curStage == 'e') {
            displayTime(1.5, "essential oils", g);
            if (isPouring==1) {
                curBackground = pouringEssentialOil;
            }
            else if (isPouring==2 || isPouring==3) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 20));
                double elapsedTimeInSeconds = elapsedTime/1000.0;
                g.drawString( "Your time is: "+elapsedTimeInSeconds, 650, 280);
                curBackground = notPouring;
            }
            else if (isPouring==0) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 20));
                g.drawString( "Click to begin!", 650, 280);
                curBackground = notPouring;
            }
        }
        else if (curStage == 'x') {
            curBackground = notPouring;
        }
    }

    /**
     *Helper method that displays the instructions for how much time the player is supposed to pour for as well as how to pour that appear on screen during the mini-game
     * @param timeInSeconds the amount of time the player is supposed to pour for
     * @param liquid the liquid the player is supposed to pour
     * @param g Graphics item that will display the instructions
     */
    public void displayTime(double timeInSeconds, String liquid, Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("monospaced", Font.BOLD, 30));
        g.drawString(timeInSeconds+" seconds", 650, 170);
        g.setFont(new Font("monospaced", Font.PLAIN, 15));
        String output = "Pour "+liquid+" for "+timeInSeconds+" seconds. When you're ready, press the mouse to start pouring, and press the mouse again to stop. You only get one shot. Try to time " + timeInSeconds + " seconds in your head!";
        String[] words = output.split(" ");
        String currentLine = "";
        int lineCount = 0;
        for(int i = 0; i < words.length; i++)
        {
            if(currentLine.length() + words[i].length() >= 40)
            {
                g.drawString(currentLine, 610, 190+lineCount*15);
                currentLine = "";
                lineCount++;
            }
            currentLine += words[i] + " ";
        }
        if (currentLine.length()>0) {
            g.drawString(currentLine,610, 190+lineCount*15);
        }
    }
}
