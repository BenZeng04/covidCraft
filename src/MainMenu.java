import processing.core.*;
class MainMenu extends PScreen
{
    @Override
    public void run()
    {
        getContext().background(0);
        getContext().textAlign(PApplet.CENTER, PApplet.CENTER);
        getContext().text("Click to continue.", 400, 250);
    }

    @Override
    public boolean mousePressEvent()
    {
        getContext().setCurrentScreen(new TestScreen());
        return true;
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
        if(getContext().key == 'e')
        {
            getContext().setCurrentScreen(new MainMenu());
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

    @Override
    public boolean mousePressEvent()
    {
        PApplication context = getContext();
        PScreen screen = getParentScreen();
        screen.addComponent(new Projectile(X, Y, context.mouseX, context.mouseY));
        return false;
    }
}

class Projectile extends PComponent
{
    private float vX, vY, X, Y;
    public Projectile(int X, int Y, int destX, int destY)
    {
        super(-100); // Set layer to be really low
        setVelocities(X, Y, destX, destY);
    }

    @Override
    public void run()
    {
        PApplication context = getContext();
        PScreen screen = getParentScreen();
        if(X <= 0 || X >= context.width || Y <= 0 || Y >= context.height)
            screen.removeComponent(this);
        X += vX;
        Y += vY;
        context.ellipse(X, Y, 10, 10);
    }

    @Override
    public boolean mousePressEvent()
    {
        PApplication context = getContext();
        setVelocities(X, Y, context.mouseX, context.mouseY);
        return false;
    }

    private void setVelocities(float X, float Y, float destX, float destY)
    {
        this.X = X;
        this.Y = Y;
        float xVel = (destX - X);
        float yVel = (destY - Y);
        float tempVel = PApplet.dist(X, Y, destX, destY);
        vX = xVel / (tempVel / 10);
        vY = yVel / (tempVel / 10);
    }
}