import java.awt.*;
import java.awt.event.MouseEvent;

public class PouringMinigame extends ScreenPanel {
    private boolean gameStarted;
    private Image notPouring;
    private Image pouringAlcohol;
    private Image pouringEssentialOil;
    private Image curBackground;
    private boolean isPouring;
    private char curStage; //'a' for rubbing alcohol, 'e' for essential oil
    private Item goalItem;
    private long startTime;
    private int finalScore;
    public PouringMinigame(Item goalItem)
    {
        notPouring = Item.loadImage("Backgrounds/PouringMinigameBackground1.png");
        pouringAlcohol = Item.loadImage("Backgrounds/PouringMinigameBackground2.png");
        pouringEssentialOil = Item.loadImage("Backgrounds/PouringMinigameBackground3.png");
        curBackground = notPouring;
        this.goalItem = goalItem;
        curStage = 'a';
        addComponent(new DialogueGUI("Here's your second mini-game! Here, we will simulate a part of making the hand sanitizer. Usually, while mixing, you would carefully measure out the amount needed with measuring devices, but what's the fun in that? Instead, for this mini-game, we will instead be using time to measure out the amount we need. See how close to the desired time you can get!")
        {
            @Override
            public void whenExited()
            {
                gameStarted = true;
            }
        });
        this.goalItem = goalItem;
    }
    public void draw(Graphics g)
    {
        g.drawImage(curBackground, 0, 0, 1080, 720, null);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(8));
    }
    public void mousePressed(MouseEvent event) {
        isPouring = !isPouring;
        if (isPouring) {
            startTime = System.currentTimeMillis();
        }
        else if (!isPouring) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (curStage=='a') {
                curStage = 'e';
            }

        }
    }
}
