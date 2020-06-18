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
            addComponent(new DialogueGUI("This is a computer! In here, you can look up all the recipes for every item in the game! Make advantage of it!")
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
        private int startX;

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
                levelRecipes= gm.getLevel(gm.getCurrentLevel()).getRecipes(); // Recipes for this level
                initialize = false;
            }

            g.setColor(new Color(0, 0, 0, 114));
            g.fillRect(0,0,1080,720);
            g.drawImage(comp_bg,140,150, WIDTH, HEIGHT,null);
            // computer screen
            startY= 250;
            for(int i= 0; i < levelRecipes.length; i++) { // loops through all recipes
                boolean product= false;
                for(Item userItem: userInventory)
                {
                    if(userItem != null && userItem.ID == levelRecipes[i].getID()) {
                        product= true;
                        break;
                    }
                }

                if(product)
                g.setColor(Color.GREEN); // in inventory
                else
                    g.setColor(Color.RED); // not in inventory

                g.fillRect(170,startY-20,20,20);
                g.setColor(Color.WHITE);
                g.drawRect(170,startY-20,20,20);
                g.setFont(new Font("monospaced", Font.BOLD, 25));
                if (i == 0)
                    title = "OBJECTIVE: ";
                else
                    title = "Sub-Task: ";
                // recipe
                g.drawString(title + levelRecipes[i].getName(), 200, startY); // title
                startY+= 30;
                startX= 170;

                g.setFont(new Font("monospaced", Font.PLAIN, 15));
                for(int a= 0; a< levelRecipes[i].getIngredientIDs().size(); a++){ // all items in recipes
                    if(product)
                        g.setColor(Color.white);
                    else {
                        g.setColor(Color.RED); // not in inventory
                        for (Item userItem : userInventory) {
                            if (userItem != null && userItem.ID == levelRecipes[i].getIngredientIDs().get(a)) {
                                g.setColor(Color.GREEN); // in inventory
                                break;
                            }
                        }
                    }
                    String itemName= levelRecipes[i].getDictionary().get(levelRecipes[i].getIngredientIDs().get(a));
                    g.drawString(itemName,startX,startY);
                    startX+= itemName.length() * 9;
                    if(a != levelRecipes[i].getIngredientIDs().size()-1) {
                        g.setColor(Color.WHITE);
                        g.drawString(" + ",startX,startY);
                        startX+= 27;
                    }
                }
                startY+= 40;
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