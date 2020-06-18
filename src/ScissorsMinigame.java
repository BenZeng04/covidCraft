import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Ben Zeng
 * Revision History:
 * - June 16, 2020: Updated ~Ben Zeng. Time Spent: 30 min
 * - June 12, 2020: Created ~Ben Zeng. Time Spent: 1h 30m
 * The class representing the minigame that shows up upon crafting the face mask.
 * @version 1
 */
public class ScissorsMinigame extends ScreenPanel
{
    /**
     * The ball object / component that needs to be dragged
     */
    private Ball ball;
    /**
     * Current frame
     */
    private int currentFrame;
    /**
     * Images that will be used as display
     */
    private Image background, shirt;
    /**
     * Constant for ball radius
     */
    private final static int BALL_RADIUS = 30;
    /**
     * Storage unit that item will be created in
     */
    private StorageUnit storageUnit;
    /**
     * Base item
     */
    private Item item;

    /**
     * Helper class / component
     */
    private class Ball extends ScreenComponent
    {
        private int x, y;
        private double followError;
        private boolean started;
        private int currentLine; // There are 4 lines on the rectangle that need to be effectively traced

        public Ball()
        {
            x = 320;
            y = 260;
        }

        @Override
        public void draw(Graphics g)
        {
            if(!started) // Arrow pointing to where to go
                g.fillPolygon(new int[]{395, 395, 420}, new int[]{240, 280, 260}, 3);
            int transparency;
            if(!started || currentLine == 4 || dist(getMouseX(), getMouseY()) > BALL_RADIUS)
                transparency = 80;
            else transparency = getSineWave() / 2 + 127;
            g.setColor(new Color(2, 160, 255, transparency));
            g.fillOval(x - BALL_RADIUS, y - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);

            g.setColor(new Color(59, 110, 255, transparency));
            final int SMALLER_RADIUS = BALL_RADIUS * 4 / 5;
            g.fillOval(x - SMALLER_RADIUS, y - SMALLER_RADIUS, SMALLER_RADIUS * 2, SMALLER_RADIUS * 2);
            if(started) update();
        }

        @Override
        public void mousePressed(MouseEvent event)
        {
            if(dist(event.getX(), event.getY()) <= BALL_RADIUS && !started)
                started = true;
        }

        /**
         * Helper methodto find distance from ball to mouse
         * @param mouseX mouse x
         * @param mouseY mouse y
         * @return the distance
         */
        private double dist(int mouseX, int mouseY)
        {
            return Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));
        }

        /**
         * Helper method that updates variables
         */
        private void update()
        {
            // Checkpoints
            final int SPEED = 2;
            if(currentLine == 0)
            {
                if(x == 780)
                    currentLine++;
                else
                    x += SPEED;
            }
            else if(currentLine == 1)
            {
                if(y == 440)
                    currentLine++;
                else
                    y += SPEED;
            }
            else if(currentLine == 2)
            {
                if(x == 320)
                    currentLine++;
                else
                    x -= SPEED;
            }
            else if(currentLine == 3)
            {
                if(y == 260)
                {
                    currentLine++;
                    gameFinished();
                }
                else
                    y -= SPEED;
            }
            if(currentLine <= 3)
                followError += (int) Math.pow(Math.max(0, (dist(getMouseX(), getMouseY()) - BALL_RADIUS) / 60), 3);
        }

        /**
         * Event that happens when the game gets completed
         */
        private void gameFinished()
        {
            int score = (int) (100000 / (followError + 1000));
            storageUnit.addItem(item.changeQuality(score));
            addComponent(new DialogueGUI("Congratulations! You have completed your first ever mini-game with a score of " + score + "%! Press the \"Return\" button to return back to playing.")
            {
                @Override
                public void whenExited()
                {
                    ScreenChangeButton button = new ScreenChangeButton("Gameplay", 385, 490, 330, 75, 5);
                    button.setText("Return",482, 537, 40, new Color(0x68D4FF));
                    button.setColor(new Color(0x3B6EFF), new Color(0x02A0FF), new Color(0x68D4FF));
                    addComponent(button);
                }
            });
        }
    }
    public ScissorsMinigame(Item baseItem, StorageUnit toAdd)
    {
        item = baseItem;
        storageUnit = toAdd;
        background = Item.loadImage("Backgrounds/MinigameBackground1.png");
        shirt = Item.TSHIRT.ICON;
        addComponent(new DialogueGUI("Welcome to your first ever mini-game! Here, you will be tracing a rectangle at a set speed, following a glowing circle! Once you click the glowing circle, you MUST hold on to it for the entire duration! You will be graded on how accurately you can follow the circle, so good luck!")
        {
            @Override
            public void whenExited()
            {
                ball = new Ball();
                addComponent(ball);
            }
        });
    }
    @Override
    public void draw(Graphics g)
    {
        currentFrame++;
        g.drawImage(background, 0, 0, 1080, 720, null);
        g.drawImage(shirt, 50, 50, 980, 620, null);
        g.setColor(new Color(0, 0, 0, 180));
        ((Graphics2D) g).setStroke(new BasicStroke(8));
        g.drawRect(320, 260, 460, 180);
        g.setColor(new Color(59, 110, 255, getSineWave()));
    }

    /**
     * Helper method that utilizes a sine wave to produce a ripple effect
     * @return
     */
    private int getSineWave()
    {
        return (int) (127 * Math.sin(currentFrame * Math.PI / 30)) + 127; // Using a sine wave for the highlight.
    }
}
