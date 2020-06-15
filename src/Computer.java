// Oscar Han

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.Key;

public class Computer extends Interactable
{

    public Computer(int layer, int xStart, int yStart, int xEnd, int yEnd)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
    }

    public void whenInteractedWith()
    {
        if(!getGame().isComputerUsed())
        {
            getGame().setComputerUsed(true);
            // Tutorial dialogue box pops up if user never opened a computer
            addComponent(new DialogueGUI("This is a computer! In here, you can look up all the recipes", "for every item in the game! Make advantage of it!")
            {
                @Override
                public void whenExited()
                {
                    createGUI();
                }
            });
        }
        else createGUI();
    }

    public void createGUI()
    {
        addComponent(new ComputerGUI());
    }

    private class ComputerGUI extends ScreenComponent
    {
        private Image comp_bg;
        private final int WIDTH,HEIGHT;

        private Game gm;
        private Item[] userInventory; // User's inventory- checks for which materials are needed
        private Recipe[] levelRecipes;
        private boolean initialize;
        private int startY;

        private String title;

        public ComputerGUI()
        {
            super(1000);
            initialize= true;

            try{
                comp_bg= ImageIO.read(new File("Other/Computer_Bg.png"));
            }catch(IOException e) { }
            WIDTH= 800;
            HEIGHT= 400;
        }

        @Override
        public void draw(Graphics g)
        {
            if(initialize){
                gm= (Game) getParentScreen().getParent(); // game
                userInventory= gm.getInventory(); // user inventory
                levelRecipes= new Level(gm.getLevel()).getRecipes(); // Recipes for this level
                initialize = false;
            }

            startY= 250;
            g.setColor(new Color(0, 0, 0, 114));
            g.fillRect(0,0,1080,720);
            g.drawImage(comp_bg,140,150, WIDTH, HEIGHT,null);
            for(int i= 0; i < levelRecipes.length; i++) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("monospaced", Font.BOLD, 25)); // change font
                if (i == 0)
                    title = "OBJECTIVE: ";
                else
                    title = "Sub-Task: ";
                g.drawString(title + levelRecipes[i].getName(), 160, startY);
                startY+= 30;
                g.setFont(new Font("monospaced", Font.PLAIN, 15));
                int startX= 180;
                for(int a= 0; a< levelRecipes[i].getIngredientIDs().size(); a++){ // recipes
                    g.drawString(levelRecipes[i].getDictionary().get(levelRecipes[i].getIngredientIDs().get(a)) + "+ ",startX,startY);
                    startX+= 80;
                }
                startY+= 20;
            }
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
            if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                removeComponent(this); // this is how you close out of a screen
            }
            denyComponents();
        }

        @Override
        public void mousePressed(MouseEvent me)
        {
            denyComponents();
        }
    }
}