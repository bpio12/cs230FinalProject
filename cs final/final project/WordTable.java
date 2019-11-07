import java.io.File;
import java.util.Hashtable;
import java.util.Vector;
import java.util.LinkedList;
import fastcsv.CsvRow;
import java.io.IOException;
import java.util.Comparator;

/**
 * WordTable reads in a file and organizes the data into the hashtable, with
 * the keys being the words and corresponding values as vectors of the
 * percentages of children at that age who can fully pronounce the word.
 *
 * @author Audrea Huang
 * @version May 11, 2019
 */
public class WordTable extends Hashtable
{
    private Hashtable<String, Vector<Double>> table;
    private LinkedList<Integer> agesList;
    private Vector<Double> wordPercentages;
    private Vector<String> words;
    private FilterableDataset dataset;

    /**
     * Constructor for objects of class WordTable
     */
    public WordTable(String input) throws IOException
    {
        Reader read = new Reader(input, 680);
        table = new Hashtable<String, Vector<Double>>();
        agesList = new LinkedList<Integer>();
        words = new Vector<String>();
        dataset = read.getDataset();

        Vector<Double> wordPercentages = new Vector<Double>();
        for (int i = 0; i < dataset.size(); i++) {
            Row row  = dataset.get(i);
            wordPercentages = new Vector<Double>();
            words.add(row.getWord());
            
            for (int numMonths = 16; numMonths < 31; numMonths++) {
                agesList.add(numMonths);
                String strAge = "" + numMonths;
                wordPercentages.add(row.getDataValue(strAge));

            }
            table.put(row.getWord(), wordPercentages);

        }
        //System.out.println("WORDS ARE: " + words);
        
        Comparator<Row> wordComparator = new CompareWords();
        Sorting.mergeSort(dataset, wordComparator);
        //System.out.println(dataset);
        // System.out.println("KEYS IN TABLE: " + table.keySet());
        // System.out.println("VALUE ASSOCIATED WITH 'IF': " + table.get("if"));
    }
    
    /**
     * Gets all the words in the dataset.
     * 
     * @return  Vector<String>  all words in the set
     */
    public Vector<String> getWords() {
        return words;
    }

    /**
     * Gets all of the ages of children in the dataset.
     * 
     * @return  LinkedList<Integer>  ages of children in 
     *          sorted order from 16 - 30 months
     */
    public LinkedList<Integer> getAgesList() {
        return agesList; 
    }

    /**
     * Returns the words in the dataset in sorted order of
     * increasing difficulty based on how many children at
     * age 16 months can fully pronounce a certain word
     * 
     * @return  LinkedList<String>  words in sorted order
     */
    public LinkedList<String> getSortedWords() {
        LinkedList<String> wordList = new LinkedList<String>(); 
        for (Row row : dataset) {
            wordList.add(row.getWord());
        }
        return wordList;
    }
    
    /**
     * Gets the percentages of pronunciation ability for a
     * given word.
     * 
     * @return  Vector<Double>  percentages reflecting
     *          how many children in can pronounce the word;
     *          value of hashtable associated with that word
     */
    public Vector<Double> get(String word) {
        int wordIndex = getSortedWords().indexOf(word);
        return table.get(wordIndex);
    }
    
    /**
     * Gets the percentages of pronunciation ability for a
     * given word.
     * 
     * @return  Vector<Double>  percentages reflecting
     *          how many children in can pronounce the word;
     *          value of hashtable associated with that word
     */
    public Hashtable<String, Vector<Double>> getTable() {
        return table;
    }
    
    /**
     * Present string representation of object
     * 
     * @return  String  representation
     */
    public String toString() {
        return table.toString();
    }

    /**
     * Main method 
     */
    public static void main(String[] args)
    {
        try {
            WordTable tableOfWords = new WordTable(
                    "/Users/audreahuang/Desktop/CS230/wordData/item_data.csv");
            System.out.println(tableOfWords.getTable());
            System.out.println(tableOfWords.getWords());
            System.out.println(tableOfWords.getSortedWords());
            System.out.println(tableOfWords);
            System.out.println(tableOfWords.getTable().isEmpty());
            System.out.println(tableOfWords.getTable().get("if")); //returning null?
            //System.out.println(tableOfWords.keySet()); // []?
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
    }
}