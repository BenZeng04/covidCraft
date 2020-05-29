import processing.core.PApplet;

public class Main
{

    /**
     * https://forum.processing.org/two/discussion/12319/using-papplet-runsketch-to-create-multiple-windows-in-a-ps3-sketch
     * @param args
     */
    public static void main(String[] args)
    {
        args = new String[]{"", ""};
        PApplication application = new PApplication(800, 500);
        application.setCurrentScreen(new TestScreen());
        PApplet.runSketch(args, application);
    }
}

/**
 * Tester class as an exemplar. Will be removed in later builds.
 */
class TestScreen extends PScreen
{
    PComponent player = new Player();
    @Override
    public void initialize()
    {
        addComponent(player);
    }
    @Override
    public void run()
    {
        getContext().background(0);
    }

    @Override
    public boolean keyPressEvent()
    {
        if(getContext().key == 'g')
        {
            player.hide();
            return true;
        }
        else if(getContext().key == 'h')
        {
            player.show();
            return true;
        }
        return false;
    }
}

class Player extends PComponent
{
    private int X, Y;
    public Player()
    {
        X = 300;
        Y = 300;
    }
    @Override
    public void run()
    {
        PApplication context = getContext();
        context.ellipse(X, Y, 100, 100);
    }

    @Override
    public boolean keyPressEvent()
    {
        PApplication context = getContext();
        System.out.println(context.key);
        if(context.key == 'd')
            X += 10;
        else if(context.key == 'a')
            X -= 10;
        else if(context.key == 's')
            Y += 10;
        else if(context.key == 'w')
            Y -= 10;
        return false;
    }
}