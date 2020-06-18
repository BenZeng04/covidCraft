import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * @author Nathan Lu
 * Revision History:
 * - June 16, 2020: Created ~Nathan Lu. Time Spent: 30m
 * @version 1
 */
public class PauseMenu extends ScreenComponent
{
    /**
     * Buttons inside of the menu
     */
    private Button[] fromMenu = new Button[2];
    /**
     * Whether or not buttons have been added / initialized yet
     */
    private boolean initialized;

    /**
     * Default constructor
     */
    public PauseMenu()
    {
        super(100);
        fromMenu[0] = new ScreenChangeButton("MainMenu", 340, 250, 400, 60, 5, 101)
        {
            @Override
            public void buttonPressed(MouseEvent event)
            {
                super.buttonPressed(event);
                removeComponent(fromMenu[0]);
                removeComponent(fromMenu[1]);
                removeComponent(PauseMenu.this);
                saveGame((Game) getParentScreen().getParent());
            }
        };
        fromMenu[1] = new ScreenChangeButton("Gameplay", 340, 410, 400, 60, 5, 101)
        {
            @Override
            public void buttonPressed(MouseEvent event)
            {
                removeComponent(fromMenu[0]);
                removeComponent(fromMenu[1]);
                removeComponent(PauseMenu.this);
            }
        };
        fromMenu[0].setText("Save and Exit", 420, 290, 30, Color.white);
        fromMenu[1].setText("Resume", 485, 450, 30, Color.white);
    }

    /**
     * Saves the game g into a file.
     * @param g The game that needs to be saved
     */
    public void saveGame(Game g)
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(new FileWriter("saveFile.txt"));
        }
        catch(Exception e)
        {
        }

        //printing player inventory
        for(Item x: g.getInventory())
        {
            if(x != null)
                out.println(x.ID + " " + x.QUALITY);
            else
                out.println("NULL");
        }
        out.println(StorageUnit.IDToStorageUnit.keySet().size());
        for(int ID: StorageUnit.IDToStorageUnit.keySet())
        {
            StorageUnit entry = StorageUnit.IDToStorageUnit.get(ID);
            out.println(ID);
            for(Item x: entry.getStorage())
                if(x != null)
                    out.println(x.ID + " " + x.QUALITY);
                else
                    out.println("NULL");
        }
        out.println(g.getCurrentLevel());
        out.close();
        // Resetting the main menu buttons
        getHostPanel().add(new MainMenu(), "MainMenu");
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 114));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        if(!initialized)
        {
            for(int i = 0; i < 2; i++)
            {
                fromMenu[i].setColor(Color.GRAY, Color.BLACK, Color.WHITE);
                addComponent(fromMenu[i]);
            }
            initialized = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        denyComponents();
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        denyComponents();
    }
}