/**
 * Ben Zeng, Oscar Han, Nathan Lu
 * 5/26/2020
 * Ms. Krasteva
 * The main method / driver class of this program.
 * Usage of Processing's PApplet Libraries: https://forum.processing.org/two/discussion/12319/using-papplet-runsketch-to-create-multiple-windows-in-a-ps3-sketch
 */

import javax.swing.*;

/**
 * @author Ben Zeng, Oscar Han, Nathan Lu
 * Revision History:
 * - May 26, 2020: Created ~Ben Zeng. Time Spent: 10m
 * - May 31, 2020: Updated ~Ben Zeng. Time Spent: 5m
 * - June 11, 2020: Updated ~Ben Zeng. Time Spent: 5m
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
        JFrame frame = new JFrame();
        HostApplication mainApplication = new HostApplication();
        frame.setContentPane(mainApplication);
        mainApplication.add(new SplashScreen(),"SplashScreen");
        mainApplication.add(new MainMenu(), "MainMenu");
        // Gameplay will be initialized in MainMenu.
        mainApplication.add(new InstructionsMenu(),"InstructionsMenu");
        mainApplication.add(new ExitMenu(),"ExitMenu");
        mainApplication.displayPanel("SplashScreen");
        frame.setSize(1080, 720);
        frame.setTitle("covidCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
