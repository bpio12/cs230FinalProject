/**
 * GetInfoPanel creates the panel of the GUI that prompts the user for the
 * child's name, age and gender.
 *
 * @author Audrea Huang
 * @version 16 May 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetInfoPanel extends JPanel
{
    private JLabel statusLabel, genderPrompt, agePrompt, namePrompt, wordQuestion;
    private JButton quitButton, submitButton, yesButton, noButton;
    private JPanel mainPanel, footerPanel;
    private JComboBox agesCombo, gendersCombo;
    private JTextField userNameField;
    private String currentAnswer;
    private String currentWord;
    private Evaluator eval;
    
    private UserProfile profile;
    
    private static final int NUM_AGES = 15;
    
    /**
     * Constructor for objects of class GetInfoPanel
     */
    public GetInfoPanel(UserProfile p) 
    {
        profile = p;
        currentAnswer = "N/A";
        currentWord = "mommy*";
        
        // initialize visual properties of panel
        setPreferredSize (new Dimension (560, 250));
        //setLayout(new BorderLayout(10, 10)); // hgap, vgap
        setBackground(Color.pink); 

        statusLabel = new JLabel(
            "<html>COMPARATIVE LANGUAGE ACQUISITION<br>" +
            "Bianca Pio, Hannah May, Audrea Huang</html>");  
        add(statusLabel);
        
        /* mainPanel portion of window that prompts for child's
         * gender, age, and native language(s) */
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize (new Dimension (540, 125));
        mainPanel.setLayout(new GridLayout(3, 2, 20, 20));

        // child name
        namePrompt = new JLabel("Please input your child's name: ");
        mainPanel.add(namePrompt);
        userNameField = new JTextField(5);
        mainPanel.add(userNameField, BorderLayout.NORTH);

        // gender info
        genderPrompt = new JLabel("Please select your child's gender: ");
        mainPanel.add(genderPrompt, BorderLayout.CENTER);
        
        String[] genders = {"female", "male"};
        gendersCombo = new JComboBox (genders); // drop down menu
        gendersCombo.setBackground(Color.lightGray);

        mainPanel.add(gendersCombo, BorderLayout.CENTER);

        // age info
        agePrompt = new JLabel("Please select your child's age in months: ");
        mainPanel.add (agePrompt);

        Integer[] ages = new Integer[NUM_AGES];
        for (int i = 0; i < NUM_AGES; i++) {
            ages[i] = i+16;
            //System.out.println(ages[i]);
        }
        agesCombo = new JComboBox (ages); // drop down menu
        agesCombo.setBackground(Color.lightGray);

        mainPanel.add(agesCombo, BorderLayout.SOUTH);

        eval = new Evaluator();
        
        // bottom panel with submit and quit buttons
        // footerPanel = new JPanel();
        // footerPanel.setBackground(Color.pink);
        // footerPanel.setLayout(new GridLayout(1, 2, 10, 10));
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ButtonListener());
        add(submitButton);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ButtonListener());
        add (quitButton);
        
        // add subpanels 
        add(mainPanel);
    }

    /**
     * Responds to a button being selected.
     */
    private class ButtonListener implements ActionListener{
        /**
         * Explains what happens when a button is pushed.
         * 
         * @param  event  button push
         * @return  void
         */
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();

            if (source == quitButton) {
                System.exit(0);
            } else if (event.getSource() == submitButton) {
                profile.setName(userNameField.getText());
                profile.setAge(agesCombo.getSelectedIndex());
                profile.setGender((String) gendersCombo.getSelectedItem());
                
                wordQuestion = new JLabel("Can your child say this word? " + currentWord);
                add(wordQuestion);
 
                yesButton = new JButton("Yes");
                noButton = new JButton("No");
                ButtonGroup answerOptions = new ButtonGroup();
                answerOptions.add(yesButton);
                answerOptions.add(noButton);
                yesButton.addActionListener(new ButtonListener());
                noButton.addActionListener(new ButtonListener());
                add(yesButton);
                add(noButton);
                validate(); // update frame to reflect question and Y/N buttons
            }
            else if (event.getSource() == yesButton) {
                currentAnswer = "yes";
                currentWord = eval.getNextQuestion(currentAnswer);
                wordQuestion.setText("Can your child pronounce this word? " + currentWord);
            }
            else if (event.getSource() == noButton) {
                currentAnswer = "no";
                currentWord = eval.getNextQuestion(currentAnswer);
                if (eval.isDone()) {
                    // stop asking questions
                    // do the processor
                    //System.out.println(profile.getAge() + currentWord);
                    Processor p = new Processor(profile.getAge(), currentWord);
                    String result = p.perWhoHaveWordAtAge();
                    System.out.println(result);
                } 
                else {
                    wordQuestion.setText("Can your child pronounce this word? " + currentWord);
                }
            }
        }
    }
}