import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Oscar Han
 * Revision History:
 * June 16, 2020: Updated ~Oscar Han.
 * - June 12, 2020: Created ~Oscar Han.
 * The class representing the minigame that shows up upon crafting the Visor Handle.
 */
public class AccuracyMinigame extends ScreenPanel
{
    private boolean gameStarted;
    private Image background, bucket;
    private ArrayList<Position> lines;
    private Position lastPos;
    private StorageUnit storageUnit;
    private Item item;
    private boolean initialized;
    private boolean second;
    private int tally;

    /**
     * Class that adds an ellipse as a component to the screen.
     */
    private class Pointer extends ScreenComponent
    {
        private int x;
        private int y;

        /**
         * Constructor
         * @param x center x coord of the ellipse
         * @param y center y coord fo the ellipse
         */
        public Pointer(int x, int y){
            this.x= x;
            this.y= y;
        }

        public void draw(Graphics g){
            g.fillOval(x-5,y-5,10,10);
        }
    }
    /**
     * Class that adds a line as a component to the screen.
     */
    private class Line extends ScreenComponent{
        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private int stroke;
        /**
         * Constructor
         * @param x1 x coord of first end
         * @param y1 y coord of first end
         * @param x2 x coord of other end
         * @param y2 y coord of other end
         * @param stroke thickness of the line
         */
        public Line(int x1, int y1, int x2, int y2, int stroke){
            this.x1= x1;
            this.y1= y1;
            this.x2= x2;
            this.y2= y2;
            this.stroke= stroke;
        }

        public void draw(Graphics g){
            ((Graphics2D) g).setStroke(new BasicStroke(stroke));
            g.drawLine(x1,y1,x2,y2);
        }
    }

    /**
     * Constructor
     */
    public AccuracyMinigame(Item baseItem, StorageUnit toAdd)
    {
        item = baseItem;
        storageUnit = toAdd;
        background = Item.loadImage("Backgrounds/MinigameBackground1.png");
        bucket = Item.loadImage("Backgrounds/BucketY.png");
        lines= new ArrayList<>();
        lastPos= new Position(-1,0);
        addComponent(new DialogueGUI("In the third mini-game, you will create a visor out of a bucket in order to create a face shield.")
        {
            @Override
            public void whenExited() {
                addComponent(new DialogueGUI("Draw a top cut line PARALLEL to the top opening of the bucket using three pointers, created using the click of your mouse. When you click with your mouse, it creates a line that serves as a guide for the knife to follow.")
                {
                    @Override
                    public void whenExited() {
                        addComponent(new DialogueGUI("The first and last pointers must be outside of the bucket, while the middle one must be inside. Make sure the height of the visor is reasonable. This game requires extreme precision!")
                        {
                            @Override
                            public void whenExited() {
                                gameStarted = true;
                            }});
                    }
                });
            }
        });
    }
    public void draw(Graphics g)
    {
        g.drawImage(background, 0, 0, 1080, 720, null);
        g.drawImage(bucket, 290, 10, 500, 700, null);
        g.setColor(Color.WHITE);
        if(lastPos.getX() != -1 && lastPos.getY() != -1) {
            ((Graphics2D) g).setStroke(new BasicStroke(8));
            g.drawLine(getMouseX(),getMouseY(),lastPos.getX(),lastPos.getY());
        }
        // line connecting the last point to user mouse
    }

    public void mousePressed(MouseEvent event)
    {
        if(!gameStarted) return;
        if(!initialized && lastPos.getY() != -1){
            if((getMouseX() <= 380 || getMouseX() >= 688) || (getMouseY() <= 210 || getMouseY() >= 620))
            {
                addComponent(new Pointer(getMouseX(),getMouseY()));
                lines.add(new Position(getMouseX(),getMouseY()));
                lastPos.setX(getMouseX());
                lastPos.setY(getMouseY());
                initialized= true;
                second= true;
                // first point
            }
        }
        else if(lastPos.getY() != -1){
            if (second && (getMouseX() >= 380 && getMouseX() <= 688) && (getMouseY() >= 210 && getMouseY() <= 620)) {
                addComponent(new Pointer(getMouseX(), getMouseY()));
                addComponent(new Line(lastPos.getX(), lastPos.getY(), getMouseX(), getMouseY(), 8));
                lines.add(new Position(getMouseX(), getMouseY()));
                lastPos.setX(getMouseX());
                lastPos.setY(getMouseY());
                second = false;
                // second option point inside bucket
            } else if (!second){
                if ((getMouseX() <= 380 || getMouseX() >= 688) || (getMouseY() <= 210 || getMouseY() >= 620)) {
                    if((lines.get(0).getX() <= 380 && getMouseX() > 380) || (lines.get(0).getX() >= 688 && getMouseX() < 688) || (lines.get(0).getY() <= 210 && getMouseY() > 210) || (lines.get(0).getX() >= 620 && getMouseX() < 620)) {
                        addComponent(new Pointer(getMouseX(), getMouseY()));
                        addComponent(new Line(lastPos.getX(), lastPos.getY(), getMouseX(), getMouseY(), 8));
                        lines.add(new Position(getMouseX(), getMouseY()));
                        lastPos.setY(-1);
                        // last point
                        tally = calculateScore(lines);
                        storageUnit.addItem(item.changeQuality(tally));
                        // sets quality of item
                        addComponent(new DialogueGUI("Congratulations! You have completed this mini-game with a score of " + tally + "%! Press the \"Return\" button to return back to playing.") {
                            @Override
                            public void whenExited() {
                                ScreenChangeButton button = new ScreenChangeButton("Gameplay", 385, 490, 330, 75, 5);
                                button.setText("Return", 482, 537, 40, new Color(201, 192, 52));
                                button.setColor(new Color(235, 232, 199), new Color(204, 204, 204), new Color(143, 141, 141));
                                addComponent(button);
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * Returns score based on performance in the minigames
     * @param pointers All pointers that create lines for the utility knife.
     */
    public int calculateScore(ArrayList<Position> pointers) {
        int score= 100;
        if(Math.max(pointers.get(0).getY(),pointers.get(pointers.size()-1).getY()) > 620) // improper line
            return 0;
        else if(Math.min(pointers.get(0).getY(),pointers.get(pointers.size()-1).getY()) < 210) // improper line
            return 0;
        for(int i=0; i < pointers.size()-1; i++){ // checks how parallel the lines are
            double pointOne= 0, pointTwo;
            double slope= (1.0* pointers.get(i).getY() - pointers.get(i+1).getY()) / (pointers.get(i).getX() - pointers.get(i+1).getX());
            double constant= pointers.get(i).getY() - slope * pointers.get(i).getX();
            if(i == 0){
                pointOne= 380 * slope + constant;
                System.out.println(pointOne);
                int straight= Math.abs(pointers.get(i+1).getY() - (int) pointOne);
                System.out.println(straight);
                score-= straight;
            }
            else{
                pointTwo= 688 * slope + constant;
                System.out.println(pointTwo);
                int straight= Math.abs(pointers.get(i).getY() - (int) pointTwo);
                System.out.println(straight);
                score-= straight;
                // checks whether the height is the valid size
                double distance= Math.max(Math.max(pointOne,pointTwo),pointers.get(i).getY())-210;
                System.out.println(distance);
                if(distance > 70)
                   score-= (distance- 70);
            }
        }
        return Math.max(score, 0);
    }
}
