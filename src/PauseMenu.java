import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class PauseMenu extends ScreenComponent
{
    private Button[] fromMenu= new Button[2];
    private boolean initialized;
    static PrintWriter out;
    static Scanner s = new Scanner(System.in);
    static StringTokenizer st;
    static BufferedReader br;
    public PauseMenu()
    {
        super(100);
        fromMenu[0]= new Button("MainMenu",340,250,400,60,5, 101)
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
        fromMenu[1]= new Button("Gameplay",340,410,400,60,5, 101)
        {
            @Override
            public void buttonPressed(MouseEvent event)
            {
                removeComponent(fromMenu[0]);
                removeComponent(fromMenu[1]);
                removeComponent(PauseMenu.this);
            }
        };
        fromMenu[0].setText("Save and Exit",420,290,30,Color.white);
        fromMenu[1].setText("Resume",485,450,30,Color.white);
    }
    public void saveGame(Game g) {
        try {
            out = new PrintWriter (new FileWriter("saveFile.txt"));
        }
        catch(Exception e){};
        TextBox name = new TextBox(340, 330);
        addComponent(name);

        //printing player inventory
        for (Item x : g.getInventory()) {
            out.print(x.ID+" ");
        }
        out.println();
        //saving locations of items
        ArrayList<GameplayRoom> rooms = g.getRooms();
        HashMap<JPanel, String> roomIDs = g.JPaneltoID();
        for(GameplayRoom r : rooms) { //loop through all the rooms
            String curRoomID = roomIDs.get(r);
            out.println(curRoomID);
            for (HitBox hb : r.getHitBoxes()) { //in each room, loop through each HitBox
                if (hb instanceof StorageUnit) { //if the HitBox is a StorageUnit
                    int storageID = ((StorageUnit) hb).getID();
                    out.println(storageID);
                    for (Item i : ((StorageUnit) hb).getStorage()) { //record the location and ID of each Item in this StorageUnit
                        out.println(i.ID);
                    }
                    out.println("exit");
                }
            }
            out.println("exit");
        }
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