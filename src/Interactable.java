/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of generic / abstract classes that will be relevant for gameplay.
 */

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nathan Lu
 * Revision History:
 * - Jun 4, 2020: Changed drawDiamond() to drawOutline() and modified its contents ~Nathan Lu. Time Spent: 5m
 * - Jun 1, 2020: Added draw() and mousePressed() and drawDiamond() ~Ben Zeng. Time Spent: 20m
 * - May 29, 2020: Created ~Nathan Lu. Time Spent: 20m
 * The class representing collisions. Extends Collisions because interactables are just collisions that can be interacted with
 * @version 2
 */
public abstract class Interactable extends HitBox
{
    public Interactable(int layer, int xStart, int yStart, int xEnd, int yEnd)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
    }

    public boolean canInteract(int x, int y)
    {
        y += Player.playerHitboxMiddleOffset();
        int xOffset = Math.max(0, Math.max(x - getXEnd(), getXStart() - x));
        int yOffset = Math.max(0, Math.max(y - getYEnd(), getYStart() - y));
        if(xOffset + yOffset <= 200)
        {
            return true;
        }
        return false;
    }
    @Override
    public void draw(Graphics g)
    {
        GameplayRoom screen = (GameplayRoom) getParentScreen();
        Player player = screen.getThisPlayer();
        if(canInteract(player.getPlayerX(), player.getPlayerY()))
            drawOutline((Graphics2D) g);
    }
    @Override
    public void mousePressed(MouseEvent event)
    {
        GameplayRoom screen = (GameplayRoom) getParentScreen();
        Player player = screen.getThisPlayer();
        if(canInteract(player.getPlayerX(), player.getPlayerY()) && isColliding(event.getX(), event.getY(), 0, 0))
        {
            // Simulates key and mouse release such that the player doesn't move / stops moving while it's interacting with something!
            getParentScreen().simulateKeyRelease();
            getParentScreen().simulateMouseRelease();
            whenInteractedWith();
            denyComponents();
        }
    }
    public Item[] getPlayerInventory()
    {
        return getGame().getInventory();
    }
    public Game getGame()
    {
        return (Game) getParentScreen().getParent();
    }
    private void drawOutline(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(5));
        g.drawRect(getXStart(), getYStart(), getXEnd()-getXStart(), getYEnd()-getYStart());
        g.setColor(new Color(255, 255, 255, 75));
        g.fillRect(getXStart(), getYStart(), getXEnd()-getXStart(), getYEnd()-getYStart());
    }
    public abstract void whenInteractedWith();
}





//subclasses:
//  StorageComponent
//  CraftingStation
//  Roommate
//  Door