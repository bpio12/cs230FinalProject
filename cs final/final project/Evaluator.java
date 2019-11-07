/**
 * Evaluator determines the child's word proficiency by asking the user 
 * whether or not the child can pronounce a series of words. The word 
 * proficiency is equal to the percentage of the children who can pronounce 
 * the last word given; i.e. if the last word that the user responded   
 * to is "baa baa" and 67% of the children in the database can pronounce 
 * "baa baa" then the child will have a word proficiency level of 0.67. 
 *
 * @author bpio, ahuang6
 * @version 5/16/2019
 */
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;

public class Evaluator
{
    private int age;
    private String yesW;
    private int countNo;
    private WordTable tableOfWords;
    private String answer;
    private Scanner scan;
    private LinkedList<String> list;
    private int wordCounter; 

    /**
     * Constructor for objects of class Evaluator
     */
    public Evaluator(){
        scan = new Scanner(System.in);
        countNo = 0;
        wordCounter = 0;
        yesW = "NA";
        try {
            WordTable tableOfWords = new WordTable("item_data.csv");
            //System.out.println(tableOfWords.getWords());
            list = tableOfWords.getSortedWords();
        }
        catch(IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Updates the question with the next word in the dataset. Takes into
     * consideration the response the user enters, incrementing the number
     * of "no" responses to keep track of when to terminate the program.
     * 
     * @param  response  "yes" or "no" indicating whether or not the child
     *                   can pronounce a certain word
     * @return  String  next word in the sorted dataset
     * 
     */
    public String getNextQuestion(String response) {
        String nextWord = "";
        if (!isDone()) {
            if (response.equals("N/A")) {
                nextWord = list.get(wordCounter);
            }
            else {
                if (response.equals("yes")) {
                    countNo = 0;
                }
                else {
                    countNo++;
                }
                /* add one to index to avoid asking if child can pronounce
                 * the word "mommy" twice */
                nextWord = list.get(wordCounter + 1);
                wordCounter ++;
            }
        }
        return nextWord;
    }

    /**
     * Checks if the program has finished (if 3 "no" responses have been
     * entered consecutively or end of dataset has been reached).
     * 
     * @return  boolean  true if program should be terminated, 
     *                   false otherwise
     */
    public boolean isDone(){
        if (countNo == 3){
            return true;
        }
        return false;
    } 
}