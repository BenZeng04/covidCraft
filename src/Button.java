// Oscar Han

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends ScreenComponent{
    private String toScreen;
    private int x, y, length, height, borderThickness;
    private Color color,borderStay, borderChange;
    private String text;
    private int textX, textY, size;
    private Color textColor;

    public Button(String toScreen, int x, int y, int length, int height, int borderThickness) // creates button outline
    {
        this(toScreen, x, y, length, height, borderThickness, 5);
    }
    public Button(String toScreen, int x, int y, int length, int height, int borderThickness, int layer) // creates button outline
    {
        super(layer);
        this.toScreen= toScreen;
        this.x= x;
        this.y= y;
        this.length= length;
        this.height= height;
        this.borderThickness= borderThickness;
        color= new Color(0);
        borderStay= new Color(0);
        borderChange= new Color(0);
        text= "";
        textX= 0;
        textY= 0;
        size= 0;
        textColor= new Color(255);
    }

    public void setColor(Color color, Color borderStay, Color borderChange) // sets color and highlight of the button
    {
        this.color= color;
        this.borderStay= borderStay;
        this.borderChange= borderChange;
    }

    public void setText(String text, int textX, int textY, int size, Color color) // text + text placement
    {
        this.text= text;
        this.textX= textX;
        this.textY= textY;
        this.size= size;
        this.textColor= color;
    }
    public boolean isInArea(MouseEvent event) {
        return event.getX() >= x && event.getX() <= x+length && event.getY() >= y && event.getY() <= y+ height;
    }
    public void mousePressed(MouseEvent event) // goes to next Screen
    {
        if(isInArea(event))
            buttonPressed(event);
    }
    public void buttonPressed(MouseEvent event)
    {
        addComponent(new TransitionEvent(toScreen));
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x,y,length,height);
        g.setColor(borderStay);
        if(getParentScreen().getMouseX() >= x && getParentScreen().getMouseX() <= x+length && getParentScreen().getMouseY() >= y && getParentScreen().getMouseY() <= y+ height)
            g.setColor(borderChange);
        g.fillRect(x,y,length,borderThickness);
        g.fillRect(x,y+height-borderThickness,length, borderThickness);
        g.fillRect(x,y,borderThickness,height);
        g.fillRect(x+length-borderThickness,y,borderThickness,height);
        g.setColor(textColor);
        g.setFont(new Font("monospaced",Font.PLAIN,size)); // change font
        g.drawString(text,textX,textY);
    }
}