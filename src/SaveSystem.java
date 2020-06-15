import javax.swing.*;
import java.util.*;
import java.io.*;
//to be saved:
/*
items -> ID, location
Player inventory
Player position for each room
 */
/*
Order of saved information:
    Player inventory
    Position of each item:
        curRoom
            curStorageUnit
                ID
            (word to go up a layer is "exit")
 */
public class SaveSystem extends ScreenComponent {
    static PrintWriter out;
    static Scanner s = new Scanner(System.in);
    static StringTokenizer st;
    static BufferedReader br;

    public void saveGame(Game g, Player p) {
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

    public void loadGame(Game g) {
        try {
            br = new BufferedReader(new FileReader("saveFile.txt"));
        }
        catch(Exception e){};

        //getting player inventory
        String playerInventory = "";
        try {
            playerInventory = br.readLine();
        }
        catch (Exception e) {}

        //setting player inventory
        String [] individualIDs = playerInventory.split(" ");
        Item [] inventory = g.getInventory();
        for (int x=0; x<individualIDs.length; x++) {
            String curID = individualIDs[x];
            int integerVersionOfID = Integer.parseInt(curID);
            inventory[x] = Item.IDtoItem.get(integerVersionOfID);
        }
        //getting locations of items
        HashMap<String, JPanel> IDToRoom = g.IDtoJPanel();
        String cur = null;
        try {
            cur = br.readLine();
        }
        catch (Exception e) {}
        while (cur!=null) {
            GameplayRoom curRoom = (GameplayRoom) IDToRoom.get(cur);
            try {
                cur = br.readLine();
            }
            catch (Exception e) {}
            while (!cur.equals("exit")) {
                StorageUnit curStorage = findStorageUnit(curRoom, Integer.parseInt(cur));
                int index = 0;
                while (!cur.equals("exit")) {
                    Item [] curStorageArray = curStorage.getStorage();
                    int integerItemID = Integer.parseInt(cur);;
                    curStorageArray[index] = Item.IDtoItem.get(integerItemID);
                    try {
                        cur = br.readLine();
                    }
                    catch (Exception e) {}
                }
                try {
                    cur = br.readLine();
                }
                catch (Exception e) {}
            }
            try {
                cur = br.readLine();
            }
            catch (Exception e) {}
        }
    }
    private StorageUnit findStorageUnit(GameplayRoom gpr, int desiredID) {
        ArrayList<HitBox> furniture = gpr.getHitBoxes();
        for (HitBox hb : furniture) {
            if (hb instanceof StorageUnit) { //if the HitBox is a StorageUnit
                if (((StorageUnit) hb).getID()==desiredID) {
                    return (StorageUnit) hb;
                }
            }
        }
        return null;
    }
}
