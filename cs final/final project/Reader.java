/**
 * filename: Reader.java
 * description: Takes a CSV datafile and converts it into a FilterableDataset.
 * date: 01/09/19
 * @author Angelina Li
 *
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import fastcsv.*;

public class Reader {

    private final FilterableDataset data;

    /**
     * Constructor for Reader class. Given a path to where eviction data is being
     * stored on your local machine, as well as a number of rows desired, will
     * initialize a FilterableDataset containing the first numRows rows in the dataset.
     *
     * @param filepath - A filepath representing where the csv data file 
     *                   containing eviction data is kept. This can be either a
     *                   relative or absolute filepath - e.g. "eviction.csv" or
     *                   "Users/angie/Desktop/pset4/eviction.csv".
     * @param numRows -  The number of rows desired. Note that if you specify
     *                   more rows of data than exist in the dataset, you will
     *                   get the entire dataset.
     */
    public Reader(String filepath, int numRows) throws IOException {
        this.data = new FilterableDataset();

        File file = new File(filepath);
        CsvReader reader = new CsvReader();
        reader.setContainsHeader(true);

        try (CsvParser parser = reader.parse(file, StandardCharsets.UTF_8)) {
            CsvRow dataRow;
            int currentNumberRows = 0;
            while ( ((dataRow = parser.nextRow()) != null) && 
                    currentNumberRows < numRows ) {
                Row row = new Row(
                    dataRow.getField("definition"),
                    dataRow.getField("category"),
                    getDoubleField(dataRow, "16"),
                    getDoubleField(dataRow, "17"),
                    getDoubleField(dataRow, "18"),
                    getDoubleField(dataRow, "19"),
                    getDoubleField(dataRow, "20"),
                    getDoubleField(dataRow, "21"),
                    getDoubleField(dataRow, "22"),
                    getDoubleField(dataRow, "23"),
                    getDoubleField(dataRow, "24"),
                    getDoubleField(dataRow, "25"),
                    getDoubleField(dataRow, "26"),
                    getDoubleField(dataRow, "27"),
                    getDoubleField(dataRow, "28"),
                    getDoubleField(dataRow, "29"),
                    getDoubleField(dataRow, "30")
                );
                data.add(row);
                currentNumberRows++;
            }
        }
    }

    private static double getDoubleField(CsvRow row, String field) {
        return Double.parseDouble(row.getField(field));
    }

    public final FilterableDataset getDataset() {
        return this.data;
    }

    /**
     * Some starter code to help you understand how filterBy works.
     */
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader(
            "/Users/audreahuang/Downloads/item_data.csv", 680);
        FilterableDataset dataset = reader.getDataset();
        System.out.println("first element: " + dataset.get(0)); // baa baa
        System.out.println("dataset size: " + dataset.size());
    }
}