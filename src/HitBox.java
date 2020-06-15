/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of generic / abstract classes that will be relevant for gameplay.
 */

/**
 * @author Nathan Lu, Ben Zeng
 * Revision History:
 * - Jun 9, 2020: Modified isColliding() method ~Ben Zeng. Time Spent: 5m
 * - Jun 8, 2020: Fixed isColli() method ~Nathan Lu. Time Spent: 10m
 * - May 29, 2020: Created ~Nathan Lu. Time Spent: 10m
 * The class representing collisions. Extends PComponent because collisions have to be drawn on screen
 * @version 1
 */
public class HitBox extends ScreenComponent
{
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;

    public int getXStart()
    {
        return xStart;
    }

    public int getYStart()
    {
        return yStart;
    }

    public int getXEnd()
    {
        return xEnd;
    }

    public int getYEnd()
    {
        return yEnd;
    }

    public HitBox(int layer, int xStart, int yStart, int xEnd, int yEnd)
    {
        super(layer);
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    /**
     * Checks if the area inputted is colliding with this instance or not
     *
     * @param x       The x coordinate of the top left corner of the area
     * @param y       The y coordinate of the top left corner of the area
     * @param xLength The length, horizontally, of the area
     * @param yLength The length, vertically, of the area
     * @return true if the coordinate inputted collides with this instance, false otherwise
     */
    public boolean isColliding(int x, int y, int xLength, int yLength)
    {
        // x1 <= y2 && y1 <= x2
        if(xStart <= x + xLength && x <= xEnd && yStart <= y + yLength && y <= yEnd) {
            return true;
        }
        return false;
    }

    /**
     * Rect is first 4 params mouse is last 2
     * @param x
     * @param y
     * @param xL
     * @param yL
     * @param mx
     * @param my
     * @return
     */
    public static boolean pointRectCollision(int x, int y, int xL, int yL, int mx, int my)
    {
        return (mx >= x && mx <= x + xL && my >= y && my <= y + yL);
    }
}
