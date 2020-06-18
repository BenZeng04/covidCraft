//Nathan Lu
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ScreenChangeButton extends Button {
    private String toScreen;

    public ScreenChangeButton(String toScreen, int x, int y, int length, int height, int borderThickness) // creates button outline
    {
        this(toScreen, x, y, length, height, borderThickness, 5);
    }
    public ScreenChangeButton(String toScreen, int x, int y, int length, int height, int borderThickness, int layer) // creates button outline
    {
        super(x, y, length, height, borderThickness, layer);
        this.toScreen = toScreen;
    }
    public void buttonPressed(MouseEvent event)
    {
        addComponent(new TransitionEvent(toScreen));
    }
}