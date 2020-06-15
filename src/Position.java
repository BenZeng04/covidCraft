/**
 * A helper class that stores x and y positions on the screen, specifically for such GUI.
 */
public class Position
{
    /**
     * The x and y co-ordinates
     */
    private int x, y;

    /**
     * Constructor for the program
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Setter for x
     * @param x x
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Getter for y
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Setter for y
     * @param y y
     */
    public void setY(int y)
    {
        this.y = y;
    }
}