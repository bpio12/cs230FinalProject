
/**
 * Write a description of class CompareWords here.
 *
 * @author Audrea Huang
 * @version May 11, 2019
 */
import java.util.Comparator;

public class CompareWords implements Comparator<Row>
{
    /**
     * Compares two rows to determine which word has a higher percentage of
     * children at age 16 months who can fully pronounce the word. The 
     * parameters are in reverse order (row2, row1) in order to eventually
     * sort the words in descending order.
     * 
     * @param  row1, row2  two rows from the data to compare percentages of
     * @return int  1 if the word corresponding to the second row has a higher 
     *              percentage of children who can pronounce the word,
     *              -1 if lower, and 0 if equal
     */
    
    public int compare(Row row2, Row row1) {
        double wordDiff = row1.getDataValue("16") - row2.getDataValue("16");
        return wordDiff > 0.0 ? 1 : 
               wordDiff == 0.0 ? 0 : -1;
    }
}
