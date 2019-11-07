/**
 * This class will create a hash table using the WordTable class. It will also contain an ageList which will be a linked list of all of the ages in the specified range. 
 * This list will later be used to index the hashtable in the Evaluator class and find the percentage of children who can pronounce the given word associated with the age.
 * It contains a constructor, helper method findIndexOfAge() to find the index of the age in the LinkedList in the hashtable and Processor(), which performs the analysis
 * and generates a response. 
 *
 * @author hmay2, bpio, ahuang6
 * @version 15 May 2019
 */

import java.util.Set;
import java.util.*;
import java.io.*;
import java.util.Enumeration;

public class Processor
{
    private LinkedList <Integer> ages;
    private int ageIndex;
    private WordTable table;
    private UserProfile child;
    private Evaluator evaluator;
    private int childAge;
    private String lastWord;
    private Hashtable<String, Vector<Double>> hashTable;
    
    /**
     * Constructor for objects of class Processor
     */
    
    public Processor(int age, String lw) {
        ages = new LinkedList <Integer>();
        
        childAge = age;
        lastWord = lw;
        
        try {
            table = new WordTable("item_data.csv");
            ages = table.getAgesList();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
    /**
     * Finds the index of a specific age in the list
     * 
     * @return int the index of the age
     */
    public int findIndexOfAge(){

        for (int i = 0 ; i < ages.size(); i++){
            //System.out.println(childAge);
            if (ages.get(i) == childAge){
                ageIndex = ages.get(i);  
            }
        }
        return ageIndex;
    }

    /**
     * This method takes the age of the child and a word they can pronounce as a parameter. First it goes to the age Vector and finds the 
     * index corresponding to the age of the child. It then takes that index and finds the value of the word in the hash table associated with
     * that index to grab the percentage of children who can pronounce that word at that age. If the percentage is over 75%, the program will 
     * provide tips for improving the children's vocabulary acquisition. 
     * 
     * @return String resonse, containing the tips, or an indication that their child appears to be acquiring language at a normal rate. 
     */
    public String perWhoHaveWordAtAge(){
        hashTable = table.getTable();
        int index = this.findIndexOfAge();
        // System.out.println(lastWord);
        // System.out.println(table);
        //ages = table.get(lastWord);
        String response = "";
        //System.out.println("word prompt: " + evaluator.wordPrompt());
        if (lastWord.equals("All words exception")){
            response += "Your child can pronounce all of the words in the database. Congratulations!";
            return response;
        }
        
        // if first 3 responses are no, there is no percentage to extract
        if(lastWord.equals("N/A")){
            return "According to our analysis, your child, on average, is having difficulty pronouncing "
            + "words that over 75% of children their age can effectively pronounce. Below are ideas of ways that "
            + " may create opportunites for your child to practice their language development skills. \n"
            + " *Disclaimer: in no way should this program be considered a diagnostic tool. If you have serious "
            + "concerns about your child and their rate of language acquisition, please refer to a medical professional. \n"
            + "*******************************************************************************************************\n"
            + "Ways to create opportunities for more complex language acquisition: \n"
            + "1. Wait for your child to ask for what they want. \n Instead of handing your child what you know they want, "
            + "wait for them to either verbally or non-verbally communicate to you that they want the object."
            + "Give your child a chance to verbally advocate for themself.\n"
            + "2. Manipulate the environment. \n If you physically alter the space that your child is living by placing "
            + "objects that your child frequently desires in sight but out of reach, it will encourage them to ask for what they want. \n"
            + "*******************************************************************************************************\n"
            + "Strategies to expand language skills: \n"
            + "1. Imitate the words and sounds that your child is making. This demonstrates to them that they are being "
            + "heard. It also promotes conversational talking, which leads to more complex language utterances. \n"
            + "2. Interpret the nonverbal signals that your child is giving you and respond with the verbal equivalent.\n "
            + "For example, if your child points to a granola bar, respond by saying 'you're pointing to a granola bar.' \n"
            + "3. Expand upon and recast the phrases that your child is saying. \n"
            + "Repeat the sentences that your child is saying, but also add words to them. For example, if your child "
            + "observes a banana, you can say to them 'Yes, that is a yellow banana'\n"
            +"4. Verbally describe what your child is doing while they are doing it. \n"
            +"5. Respond immediately to all attempts to communicate. \n"
            +"6. Label objects around the home. \n"
            +"7. Try not to directly 'test' your child \n"
            +"For example, if your child is drawing a cow, instead of directly asking your child where a cow lives, "
            + "you could say, 'I wonder where the cow is going.' This will encourage them to respond in a non-stressful way and eliminates "
            + "negative talk. All attempts to communicate should be encouraged.\n"
            +"8. Use labeled praise \n"
            +"Instead of just saying 'good job' to your child, indicate to them what they did a good job of.\n"
            + "*******************************************************************************************************\n"
            +"Information gathered from the Child Mind Institute";
        }
        //System.out.println(index);
       // System.out.println(hashTable.get(lastWord).size());
        double percentage = hashTable.get(lastWord).get(index);
        //System.out.println(percentage);
        
        /* since words are organized in decreasing order (with words with a higher percentage of children
         * who can pronounce it appearing before words with a lower percentage) */
        if (percentage > 0.25){
            response += "According to our analysis, your child, on average, is having difficulty pronouncing "
            + "words that over 75% of children their age can effectively pronounce. Below are ideas of ways that "
            + " may create opportunites for your child to practice their language development skills. \n"
            + " *Disclaimer: in no way should this program be considered a diagnostic tool. If you have serious "
            + "concerns about your child and their rate of language acquisition, please refer to a medical professional. \n"
            + "*******************************************************************************************************\n"
            + "Ways to create opportunities for more complex language acquisition: \n"
            + "1. Wait for your child to ask for what they want. \n Instead of handing your child what you know they want, "
            + "wait for them to either verbally or non-verbally communicate to you that they want the object."
            + "Give your child a chance to verbally advocate for themself.\n"
            + "2. Manipulate the environment. \n If you physically alter the space that your child is living by placing "
            + "objects that your child frequently desires in sight but out of reach, it will encourage them to ask for what they want. \n"
            + "*******************************************************************************************************\n"
            + "Strategies to expand language skills: \n"
            + "1. Imitate the words and sounds that your child is making. This demonstrates to them that they are being "
            + "heard. It also promotes conversational talking, which leads to more complex language utterances. \n"
            + "2. Interpret the nonverbal signals that your child is giving you and respond with the verbal equivalent.\n "
            + "For example, if your child points to a granola bar, respond by saying 'you're pointing to a granola bar.' \n"
            + "3. Expand upon and recast the phrases that your child is sauying. \n"
            + "Repeat the sentences that your child is saying, but also add words to them. For example, if your child "
            + "observes a banana, you can say to them 'Yes, that is a yellow banana'\n"
            +"4. Verbally describe what your child is doing while they are doing it. \n"
            +"5. Respond immediately to all attempts to communicate. \n"
            +"6. Label objects around the home. \n"
            +"7. Try not to directly 'test' your child \n"
            +"For example, if your child is drawing a cow, instead of directly asking your child where a cow lives, "
            + "you could say, 'I wonder where the cow is going.' This will encourage them to respond in a non-stressful way and eliminates "
            + "negative talk. All attempts to communicate should be encouraged.\n"
            +"8. Use labeled praise \n"
            +"Instead of just saying 'good job' to your child, indicate to them what they did a good job of.\n"
            + "*******************************************************************************************************\n"
            +"Information gathered from the Child Mind Institute";
        }
        else {
            response += "Your child appears to be acquiring their language at a rate of above 75%. \n"
            +" *Disclaimer: in no way should this program be considered a diagnostic tool. If you have serious "
            + "concerns about your child and their rate of language acquisition, please refer to a medical professional \n";
        }
        return response;
    }

    /**
     * Main method that we used to test the program running on the command line
     */
    public static void main(String[] args){
        // try {
            // //Processor testingProcessor = new Processor();
            // WordTable testingTable = new WordTable("/Users/hannahmay/Downloads/item_data.csv");
            // //UserProfile testingChild = new UserProfile();

            // //System.out.println(testingTable.getAgesList());
            // //LinkedList<Integer> ages = testingTable.getAgesList();
            // //int index = testingProcessor.findIndexOfAge();
            // //System.out.println(index);
            
            // System.out.println(testingProcessor.perWhoHaveWordAtAge());
            
        // }
        // catch(IOException e) {
            // System.out.println("File not found");
        // }
    }
}