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
        mainApplication.add(new ScissorsMinigame(null), "minigametest");
        mainApplication.add(new AccuracyMinigame(null), "tester");
        mainApplication.add(new SplashScreen(),"SplashScreen");
        mainApplication.add(new MainMenu(), "MainMenu");
        // Gameplay will be initialized in MainMenu.
        mainApplication.add(new InstructionsMenu(),"InstructionsMenu");
        mainApplication.add(new Highscores(),"HighScoresMenu");
        mainApplication.add(new ExitMenu(),"ExitMenu");
        //mainApplication.displayPanel("SplashScreen");
        mainApplication.displayPanel("tester");
        // mainApplication.displayPanel("minigametest");
        frame.setSize(1080, 720);
        frame.setTitle("covidCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
