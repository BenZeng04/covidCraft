/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * The main method / driver class of this program.
 * Usage of Processing's PApplet Libraries: https://forum.processing.org/two/discussion/12319/using-papplet-runsketch-to-create-multiple-windows-in-a-ps3-sketch
 */

import processing.core.PApplet;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 10m
 * @version 1
 */
public class Main
{
    /**
     * The main method for the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        args = new String[] {"", ""};
        PApplication application = new PApplication(800, 500);
        application.setCurrentScreen(new MainMenu());
        PApplet.runSketch(args, application);
    }
}

