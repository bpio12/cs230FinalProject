/**
 * filename: FilterableDataset.java
 * description: Represents a Vector of Row objects, that can be filtered
 *              based on various Row properties.
 * date: 04/01/19
 * @author Angelina Li
 *
 */

import java.util.Vector;

public class FilterableDataset extends Vector<Row> {

    /**
     * Returns a copy of the data in this dataset, as filtered by the rule 
     * specified in predicate.
     * @return a new FilterableDataset of all the rows in the dataset that meet the rule 
     *         specified in predicate.
     */
    public FilterableDataset filterBy(FilterPredicate<Row> predicate) {
        FilterableDataset newDataset = new FilterableDataset();
        for(Row row : this) {
            if (predicate.apply(row)) {
                newDataset.add(row);
            }
        }
        return newDataset;
    }
}