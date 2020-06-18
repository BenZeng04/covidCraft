import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccuracyMinigame extends ScreenPanel
{
    private boolean gameStarted;
    private Image background, bucket;
    private ArrayList<Position> lines;
    private Position lastPos;
    private boolean initialized;
    private Item item;
    private class Pointer extends ScreenComponent{ // ovals- component
        private int x;
        private int y;

        public Pointer(int x, int y){
            this.x= x;
            this.y= y;
        }

        public void draw(Graphics g){
            g.fillOval(x-5,y-5,10,10);
        }
    }

    private class Line extends ScreenComponent{ // lines- component
        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private int stroke;

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

    public AccuracyMinigame(Item item)
    {
        this.item = item;
        background = Item.loadImage("Backgrounds/MinigameBackground1.png");
        bucket = Item.loadImage("Other/BucketY.png");
        lines= new ArrayList<Position>();
        lastPos= new Position(-1,0);
        addComponent(new DialogueGUI("In this mini-game, you will create a visor out of a bucket in order to create a face shield.")
        {
            @Override
            public void whenExited() {
                addComponent(new DialogueGUI("Draw a top cut line PARALLEL to the top opening of the bucket. Your bucket will automatically be cut once your line(s) cross the bucket. When you click with your mouse, it creates a line that serves as a guide for the knife to follow.")
                {
                    @Override
                    public void whenExited() {
                        addComponent(new DialogueGUI("*Hint: Make sure your line is a reasonable height, and make sure it is as parallel to the top opening as possible!")
                        {
                            @Override
                            public void whenExited() { }});
                    }
                });
            }
        });
    }
    public void draw(Graphics g)
    {
        g.drawImage(background, 0, 0, 1080, 720, null);
        g.drawImage(bucket, 290, 10, 500, 700, null);
        g.setColor(Color.BLACK);
        if(lastPos.getX() != -1) {
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawLine(getMouseX(),getMouseY(),lastPos.getX(),lastPos.getY());
        }
        // as long as x is not -1, draw line from mouse position to most recent position
    }
    // xs 380 688  ys 210 620

    public void mousePressed(MouseEvent event){
        if(!initialized){
            if((getMouseX() <= 380 || getMouseX() >= 688) && (getMouseY() <= 210 || getMouseY() >= 620))
            {
                addComponent(new Pointer(getMouseX(),getMouseY()));
                lines.add(new Position(getMouseX(),getMouseY()));
                lastPos.setX(getMouseX());
                lastPos.setY(getMouseY());
                initialized= true;
            }
        }
        else{
            if((getMouseX() >= 380 && getMouseX() <= 688) && (getMouseY() >= 210 && getMouseY() <= 620)){ // line crosses bucket- check for proper line
                // TODO- when the user has completed the minigame; final check has to create polygon
            } // only have to add final line and dot here as well
            else{
                addComponent(new Pointer(getMouseX(),getMouseY()));
                addComponent(new Line(lastPos.getX(),lastPos.getY(),getMouseX(),getMouseY(),5));
                lines.add(new Position(getMouseX(),getMouseY()));
                lastPos.setX(getMouseX());
                lastPos.setY(getMouseY());
                // new last positions
            }
        }
        // first time- make sure properly pressed
        // next time- always check if its made a proper line
        // or: just create a line
        // either way-draw line and oval
    }
}
