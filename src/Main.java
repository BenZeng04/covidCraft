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
    boolean clickedOnObject;
    public void initialize()
    {
        final PApplication context = getContext();
        addComponent(new PComponent(0) // Background
        {
            @Override
            public void run()
            {
                context.background(255, 255, 255);
                context.noStroke();
                context.fill(255, 0, 0);
                context.ellipse(300, 300, 100, 100);
            }
        });
    }
}