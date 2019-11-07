/**
 * filename: FilterPredicate.java
 * description: Represents a predicate that is used to filter a collection of items.
 * date: 04/01/19
 * @author Angelina Li
 *
 * NOTE: Do NOT modify this class.
 */

public interface FilterPredicate<T> {
    /**
     * Given an element, will return true if this element should be included
     * in the Collection<T> this predicate is filtering over.
     * @return whether the specified element should be included in the filtered
     *         Collection<T> of elements.
     */
    public boolean apply(T element);
}