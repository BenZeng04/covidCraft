/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * Implementation of generic / abstract classes that will be relevant for gameplay.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Nathan Lu
 * Revision History:
 * - May 29, 2020: Created ~Nathan Lu. Time Spent: 5m
 * - Oscar: added values for HashMap
 * The class representing items.
 * CREDIT TO ITEM SOURCES:
 * - https://pbs.twimg.com/media/D7mSLOMUIAAGYfa.png (Scissors)
 *
 * @version 1
 */
public class Item
{
    public static HashMap<Integer, Item> IDtoItem = new HashMap<Integer, Item>();
    public final static Item TSHIRT, SCISSORS, HOLEPUNCHER, UTILITYKNIFE, TAPE, TAPEMEASURE, THREAD, ELASTIC, BUCKET, PLASTICBOTTLE, PLASTICSHEET, PLASTICGUARD, VISORHANDLE, SHIELDCOVER, FACESHIELD, FACEMASK, RUBBINGALCOHOL, ALOEVERA,  ESSENTIALOIL, SANITIZERGEL, HANDSANITIZER;
    static
    {
        TSHIRT = new Item("T Shirt", 1, "Monkey", loadImage("Items/TShirt.png"));
        IDtoItem.put(1, TSHIRT);
        SCISSORS = new Item("Scissors", 2, "Snip Snip... Used to cut things of all sorts.", loadImage("Items/Scissors.png"));
        IDtoItem.put(2, SCISSORS);
        HOLEPUNCHER = new Item("Hole Puncher", 3, "Monkey", loadImage("Items/HolePuncher.png"));
        IDtoItem.put(3, HOLEPUNCHER);
        UTILITYKNIFE = new Item("Utility Knife", 4, "Monkey", loadImage("Items/UtilityKnife.png"));
        IDtoItem.put(4, UTILITYKNIFE);
        TAPE = new Item("Tape", 5, "Monkey", loadImage("Items/Tape.png"));
        IDtoItem.put(5, TAPE);
        TAPEMEASURE = new Item("Tape Measure", 6, "Monkey", loadImage("Items/TapeMeasure.png"));
        IDtoItem.put(6, TAPEMEASURE);
        THREAD = new Item("Thread", 7, "Monkey", loadImage("Items/Thread.png"));
        IDtoItem.put(7, THREAD);
        ELASTIC = new Item("Elastic", 8, "Monkey", loadImage("Items/Elastic.png"));
        IDtoItem.put(8, ELASTIC);
        BUCKET = new Item("Bucket", 9, "Monkey", loadImage("Items/Bucket.png"));
        IDtoItem.put(9, BUCKET);
        PLASTICBOTTLE = new Item("Plastic Bottle", 10, "Monkey", loadImage("Items/PlasticBottle.png"));
        IDtoItem.put(10, PLASTICBOTTLE);
        PLASTICSHEET = new Item("Plastic Sheet", 11, "Monkey", loadImage("Items/PlasticSheet.png"));
        IDtoItem.put(11, PLASTICSHEET);
        PLASTICGUARD = new Item("Plastic Guard", 12, "Monkey", loadImage("Items/PlasticGuard.png"));
        IDtoItem.put(12, PLASTICGUARD);
        VISORHANDLE = new Item("Visor Handle", 13, "Monkey", loadImage("Items/VisorHandle.png"));
        IDtoItem.put(13, VISORHANDLE);
        SHIELDCOVER = new Item("Shield Cover", 14, "Monkey", loadImage("Items/ShieldCover.png"));
        IDtoItem.put(14, SHIELDCOVER);
        FACESHIELD = new Item("Face Shield", 15, "Monkey", loadImage("Items/FaceShield.png"));
        IDtoItem.put(15, FACESHIELD);
        FACEMASK = new Item("Face Mask", 16, "Monkey", loadImage("Items/FaceMask.png"));
        IDtoItem.put(16, FACEMASK);
        RUBBINGALCOHOL = new Item("Rubbing Alcohol", 17, "Monkey", loadImage("Items/RubbingAlcohol.png"));
        IDtoItem.put(17, RUBBINGALCOHOL);
        ALOEVERA = new Item("Aloe Vera Gel", 18, "Monkey", loadImage("Items/AloeVera.png"));
        IDtoItem.put(18, ALOEVERA);
        ESSENTIALOIL = new Item("Essential Oil", 19, "Monkey", loadImage("Items/EssentialOil.png"));
        IDtoItem.put(19, ESSENTIALOIL);
        SANITIZERGEL = new Item("Sanitizer Gel", 20, "Monkey", loadImage("Items/SanitizerGel.png"));
        IDtoItem.put(20, SANITIZERGEL);
        HANDSANITIZER = new Item("Hand Sanitizer", 21, "Monkey", loadImage("Items/HandSanitizer.png"));
        IDtoItem.put(21, HANDSANITIZER);
    }
    public static Image loadImage(String filePath)
    {
        try
        {
            return ImageIO.read(new File(filePath));
        }
        catch(IOException e)
        {
            return null;
        }
    }
    public final int ID;
    public final String NAME;
    public final String DESCRIPTION;
    public final Image ICON;

    public Item(String name, int id, String description, Image icon)
    {
        this.NAME = name;
        this.ID = id;
        this.DESCRIPTION = description;
        this.ICON = icon;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Item)) return false;
        return ((Item) o).ID == ID;
    }
}