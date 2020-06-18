//Nathan Lu
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextBox extends Button{
    private boolean currentlyTyping;
    private String input;
    private int inputBoxX, inputBoxY, inputBoxLength, inputBoxWidth;
    private Color inputBoxColour;
    private Color inputBoxTextColour;

    public TextBox(int x, int y, int length, int height, int borderThickness) // creates textbox outline
    {
        this(x, y, length, height, borderThickness, 5);
    }
    public TextBox(int x, int y, int length, int height, int borderThickness, int layer) // creates textbox outline
    {
        super(x, y, length, height, borderThickness, layer);
        input="";
    }
    public TextBox(int x, int y) // simplified constructor
    {
        this(x, y, 400, 120, 5);
        this.setText("Enter Name", x+40, y+40, 30, Color.white);
        this.setColor(Color.GRAY, Color.BLACK, Color.WHITE);
        this.setInputBox(x+40, x+60, 320, 40, Color.DARK_GRAY, Color.WHITE);
        input="";
    }
    public void setInputBox(int inputBoxX, int inputBoxY, int inputBoxLength, int inputBoxWidth, Color inputBoxColour, Color inputBoxTextColour) // text + text placement
    {
        this.inputBoxX = inputBoxX;
        this.inputBoxY= inputBoxY;
        this.inputBoxLength= inputBoxLength;
        this.inputBoxWidth= inputBoxWidth;
        this.inputBoxColour = inputBoxColour;
        this.inputBoxTextColour = inputBoxTextColour;
    }
    public void drawInputBox(Graphics g) {
        g.setColor(inputBoxColour);
        g.drawRect(inputBoxX, inputBoxY, inputBoxLength, inputBoxWidth);
        g.setColor(inputBoxTextColour);
        g.drawString(input, inputBoxX+10, inputBoxY+inputBoxWidth-10);
    }
    public void buttonPressed (MouseEvent event)
    {
        if(super.isInArea(event))
            currentlyTyping = true;
        else
            currentlyTyping = false;
        denyComponents();
    }
    public void keyPressed(KeyEvent event) {
        if(currentlyTyping)
        {
            if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE && input.length() > 0)
            {
                input = input.substring(0, input.length() - 1);
            }
            else if((event.getKeyCode() >= 'a' && event.getKeyCode() <= 'z' || event.getKeyCode() >= 'A' && event.getKeyCode() >= 'Z' || event
                .getKeyCode() >= '0' && event.getKeyCode() >= '9') && input.length() <= 16)
            {
                input += event.getKeyChar();
            }
        }
        denyComponents();
    }
    public String getInput() {
        return input;
    }

    public void draw(Graphics g)
    {
        super.draw(g);
        drawInputBox(g);
    }
}
/*
        TextBox temp = new TextBox(500, 20, 400, 120, 5);
        temp.setText("Enter Name",540,60,30,Color.white);
        temp.setColor(Color.GRAY, Color.BLACK, Color.WHITE);
        temp.setInputBox(540, 80, 320, 40, Color.DARK_GRAY, Color.WHITE);
        addComponent(temp);

        TextBox temp = new TextBox(x, y, 400, 120, 5);
        temp.setText("Enter Name", x+40, y+40, 30, Color.white);
        temp.setColor(Color.GRAY, Color.BLACK, Color.WHITE);
        temp.setInputBox(x+40, x+60, 320, 40, Color.DARK_GRAY, Color.WHITE);
        addComponent(temp);
 */