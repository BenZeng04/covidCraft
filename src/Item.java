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
 * - Updated: ~Oscar- added values for HashMap Time SPend: 15m
 * - June 17, 2020: Updated ~Ben Zeng, Oscar Han, Nathan Lu. Time Spent: 30m
 * The immutable class representing items.
 * CREDIT TO ITEM SOURCES:
 * - https://pbs.twimg.com/media/D7mSLOMUIAAGYfa.png (Scissors)
 *
 * @version 1
 */
public class Item
{
    /**
     * Static map to map IDs to specific items
     */
    public static HashMap<Integer, Item> IDtoItem = new HashMap<>();
    /**
     * Constants for all items in the game
     */
    public final static Item TSHIRT, SCISSORS, HOLEPUNCHER, UTILITYKNIFE, TAPE, TAPEMEASURE, THREAD, ELASTIC, BUCKET, PLASTICBOTTLE, PLASTICSHEET, PLASTICGUARD, VISORHANDLE, SHIELDCOVER, FACESHIELD, FACEMASK, RUBBINGALCOHOL, ALOEVERA,  ESSENTIALOIL, SANITIZERGEL, HANDSANITIZER;
    static
    {
        TSHIRT = new Item("T Shirt", 1, "It's a T-Shirt! Wear it... or use it to make a mask?", loadImage("Items/TShirt.png"));
        IDtoItem.put(1, TSHIRT);
        TSHIRT.addLocations(
            AliceRoom.LAUNDRY_BASKET,
            FirstBathroom.LAUNDRY_BASKET
        );

        SCISSORS = new Item("Scissors", 2, "Snip Snip... Used to cut things of all sorts.", true, loadImage("Items/Scissors.png"));
        IDtoItem.put(2, SCISSORS);
        // Given by NPC

        HOLEPUNCHER = new Item("Hole Puncher", 3, "Usually the 3-holed version is better, but they can be inconvenient when you need custom lengths.", true, loadImage("Items/HolePuncher.png"));
        IDtoItem.put(3, HOLEPUNCHER);
        HOLEPUNCHER.addLocations(
            AliceRoom.DRAWERS,
            LivingRoom.DESK,
            LivingRoom.DRESSER
        );

        UTILITYKNIFE = new Item("Utility Knife", 4, "DO NOT use as a weapon, please! You have been warned.", true, loadImage("Items/UtilityKnife.png"));
        IDtoItem.put(4, UTILITYKNIFE);
        // Given by NPC.

        TAPE = new Item("Tape", 5, "Connects items together... Briefly.", loadImage("Items/Tape.png"));
        IDtoItem.put(5, TAPE);
        TAPE.addLocations(
            AliceRoom.DRAWERS,
            LivingRoom.DESK,
            LivingRoom.DRESSER,
            Kitchen.BOTTOM_CABINET,
            DiningRoom.DRAWER
        );

        TAPEMEASURE = new Item("Tape Measure", 6, "As the name implies, it is used to measure lengths. Useful for making precise cuts.", true, loadImage("Items/TapeMeasure.png"));
        IDtoItem.put(6, TAPEMEASURE);
        TAPEMEASURE.addLocations(
            LivingRoom.TV_DRAWER,
            Kitchen.BOTTOM_CABINET
        );

        THREAD = new Item("Thread", 7, "Just some common string.", true, loadImage("Items/Thread.png"));
        IDtoItem.put(7, THREAD);
        THREAD.addLocations(
            LivingRoom.TV_DRAWER,
            Kitchen.BOTTOM_CABINET,
            DiningRoom.DISPLAY_CASE
        );

        ELASTIC = new Item("Elastic", 8, "Stretch! The more this is streched the firmer the item is.", loadImage("Items/Elastic.png"));
        IDtoItem.put(8, ELASTIC);
        ELASTIC.addLocations(
            LivingRoom.TV_DRAWER,
            Kitchen.BOTTOM_CABINET,
            DiningRoom.DISPLAY_CASE
        );

        BUCKET = new Item("Bucket", 9, "Stores liquids that you can mix together!", loadImage("Items/Bucket.png"));
        IDtoItem.put(9, BUCKET);
        BUCKET.addLocations(
            FirstBathroom.LEFT_CABINET,
            FirstBathroom.RIGHT_CABINET,
            SecondBathroom.CABINET
        );

        PLASTICBOTTLE = new Item("Plastic Bottle", 10, "An empty spray bottle. To be used with a liquid first!", loadImage("Items/PlasticBottle.png"));
        IDtoItem.put(10, PLASTICBOTTLE);
        PLASTICBOTTLE.addLocations(
            Kitchen.SINK,
            Kitchen.TOP_CABINET,
            DiningRoom.DRAWER,
            FirstBathroom.LEFT_CABINET,
            FirstBathroom.RIGHT_CABINET,
            SecondBathroom.CABINET
        );

        PLASTICSHEET = new Item("Plastic Sheet", 11, "A clear sheet. Useful for simple coverings.", loadImage("Items/PlasticSheet.png"));
        IDtoItem.put(11, PLASTICSHEET);
        PLASTICSHEET.addLocations(
            LivingRoom.TV_DRAWER,
            AliceRoom.DRAWERS
        );

        PLASTICGUARD = new Item("Plastic Guard", 12, "A see through covering with holes.", loadImage("Items/PlasticGuard.png"));
        IDtoItem.put(12, PLASTICGUARD); // CRAFTABLE
        
        VISORHANDLE = new Item("Visor Handle", 13, "The handle of a Visor hat. Wraps comfortably around your head!", loadImage("Items/VisorHandle.png"));
        IDtoItem.put(13, VISORHANDLE); // CRAFTABLE
        
        SHIELDCOVER = new Item("Shield Cover", 14, "One small step away from being a protective face shield!", loadImage("Items/ShieldCover.png"));
        IDtoItem.put(14, SHIELDCOVER); // CRAFTABLE
        
        FACESHIELD = new Item("Face Shield", 15, "A protective piece of gear that will protect you well against the virus, while still allowing for comfort!", loadImage("Items/FaceShield.png"));
        IDtoItem.put(15, FACESHIELD); // CRAFTABLE
        
        FACEMASK = new Item("Face Mask", 16, "A physical barrier between your mouth and the outside environment!", loadImage("Items/FaceMask.png"));
        IDtoItem.put(16, FACEMASK); // CRAFTABLE
        
        RUBBINGALCOHOL = new Item("Rubbing Alcohol", 17, "A disinfectant that can be used to battle germs of all kinds!", loadImage("Items/RubbingAlcohol.png"));
        IDtoItem.put(17, RUBBINGALCOHOL);
        RUBBINGALCOHOL.addLocations(
            FirstBathroom.LEFT_CABINET,
            FirstBathroom.RIGHT_CABINET,
            SecondBathroom.CABINET,
            Kitchen.TOP_CABINET,
            Kitchen.SINK,
            DiningRoom.DISPLAY_CASE
        );

        ALOEVERA = new Item("Aloe Vera Gel", 18, "A moisturizer that can help treat burns and dry skin.", loadImage("Items/AloeVera.png"));
        IDtoItem.put(18, ALOEVERA);
        ALOEVERA.addLocations(
            FirstBathroom.LEFT_CABINET,
            FirstBathroom.RIGHT_CABINET,
            SecondBathroom.CABINET
        );

        ESSENTIALOIL = new Item("Essential Oil", 19, "Natural chemical compounds that can be found from plants.", loadImage("Items/EssentialOil.png"));
        IDtoItem.put(19, ESSENTIALOIL);
        ESSENTIALOIL.addLocations(
            FirstBathroom.LEFT_CABINET,
            FirstBathroom.RIGHT_CABINET,
            SecondBathroom.CABINET
        );

        SANITIZERGEL = new Item("Sanitizer Gel", 20, "The gel used inside of hand sanitizer bottles! Now if only you had an actual bottle...", loadImage("Items/SanitizerGel.png"));
        IDtoItem.put(20, SANITIZERGEL); // CRAFTABLE

        HANDSANITIZER = new Item("Hand Sanitizer", 21, "The hand sanitizer you all know and love! Great for disinfecting your hands in public.", loadImage("Items/HandSanitizer.png"));
        IDtoItem.put(21, HANDSANITIZER); // CRAFTABLE
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
    // Immutable as there will only be one item used per game.
    public final boolean IS_TOOL;
    public final int ID;
    public final String NAME;
    public final String DESCRIPTION;
    public final Image ICON;
    public final ArrayList<Integer> STORAGE_LOCATIONS;
    public final int QUALITY;
    public Item(String name, int id, String description, Image icon)
    {
        this(name, id, description, false, icon);
    }
    public Item(String name, int id, String description, boolean isTool, Image icon)
    {
        this(name, id, description, isTool, icon, -1);
    }
    public Item(String name, int id, String description, boolean isTool, Image icon, int quality)
    {
        this.NAME = name;
        this.ID = id;
        this.DESCRIPTION = description;
        this.ICON = icon;
        this.STORAGE_LOCATIONS = new ArrayList<>();
        this.IS_TOOL = isTool;
        this.QUALITY = quality;
    }
    public void addLocations(int... IDs)
    {
        for(int i: IDs)
            STORAGE_LOCATIONS.add(i);
    }
    public void addToStorageDevices()
    {
        if(STORAGE_LOCATIONS.size() == 0) return;
        ArrayList<StorageUnit> validDevices = new ArrayList<>();
        for(int ID: STORAGE_LOCATIONS)
        {
            StorageUnit storageUnit = StorageUnit.IDToStorageUnit.get(ID);
            if(storageUnit.hasSpaceLeft())
                validDevices.add(storageUnit);
        }
        int randomIndex = (int) (Math.random() * validDevices.size());
        validDevices.get(randomIndex).addItem(this);
    }
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Item)) return false;
        return ((Item) o).ID == ID;
    }

    public Item changeQuality(int quality)
    {
        return new Item(NAME, ID, DESCRIPTION, IS_TOOL, ICON, quality);
    }
}