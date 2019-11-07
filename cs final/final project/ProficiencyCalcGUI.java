/**
 * ProficiencyCalcGUI runs the program, creating the panel
 * that prompts the user for the child information and asks the
 * word questions.
 *
 * @author Audrea Huang
 * @version 5/15/2019
 */

import javax.swing.JFrame;

public class ProficiencyCalcGUI
{
    public static void main (String[] args) {
        // creates and shows a Frame 
        JFrame frame = new JFrame("Proficiency Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UserProfile profile = new UserProfile();
        //create a panel, and add it to the frame
        GetInfoPanel panel = new GetInfoPanel(profile);
        
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
