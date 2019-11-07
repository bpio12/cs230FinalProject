/**
 * filename: Row.java
 * description: Initializes Row class
 * date: 05/07/19
 * @author Audrea Huang
 *
 */

import java.util.HashMap;

public class Row {
    
    private final String definition;
    private final HashMap<String, Double> data;

    /**
     * Constructor for Row class. 
     * Each Row represents data on the city-year level.
     */
    public Row(
        String definition,
        String category,
        double sixteen,
        double seventeen,
        double eighteen,
        double nineteen,
        double twenty,
        double twentyOne,
        double twentyTwo,
        double twentyThree,
        double twentyFour,
        double twentyFive,
        double twentySix,
        double twentySeven,
        double twentyEight,
        double twentyNine,
        double thirty
    ) {
        this.definition = definition;
        
        this.data = new HashMap<String, Double>();
        this.data.put("16", sixteen);
        this.data.put("17", seventeen);
        this.data.put("18", eighteen);
        this.data.put("19", nineteen);
        this.data.put("20", twenty);
        this.data.put("21", twentyOne);
        this.data.put("22", twentyTwo);
        this.data.put("23", twentyThree);
        this.data.put("24", twentyFour);
        this.data.put("25", twentyFive);
        this.data.put("26", twentySix);
        this.data.put("27", twentySeven);
        this.data.put("28", twentyEight);
        this.data.put("29", twentyNine);
        this.data.put("30", thirty);
    }

    /**
     * Getter method for all String values associated with this row.
     * @return String value associated with this row.
     */
    public final String getWord() {
        return this.definition;
    }
    
    /**
     * Getter method for all numerical values associated with this row.
     * @return numerical value associated with this row.
     */
    public final double getDataValue(String variableName) {
        if( this.data.containsKey(variableName) ) {
            return this.data.get(variableName);
        }
        throw new IllegalArgumentException(
            "Row objects do not contain the variable '" + variableName + "'!");
    }

    /**
     * Returns a String representing all the data in this Row instance.
     @return a String representation of this Row.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("word=" + this.definition);
        sb.append("}");
        return sb.toString();
    }
}