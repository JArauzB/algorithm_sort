package sortingservice;

import java.util.Comparator;

/**
 *
 * @author Richard van den Ham <r.vandenham@fontys.nl>
 */
public interface SorterConfiguration {

    /**
     * Get name of sorter implementation.
     * @return name
     */
    String getName();

    /**
     * Get the SortKind of the sorter implementation.
     * @return sortKind
     */
    SortKind getSortKind();

    /**
     * Indicates wether this sorter uses a Priority Queue.
     * @return true if configuration uses priority queue, false otherwise.
     */
    boolean usesPriorityQueue();
    
    /**
     * Get queue for sorter.
     * @param <T>
     * @return queue for sorter
     */
    <T> Queue<T> getQueue();

    /**
     * Get priority queue.
     * @param <T>
     * @param comparator
     * @return priority queue or null if configuration does not use priority
     * queue.
     */
    <T> PriorityQueue<T> getPriorityQueue(Comparator<T> comparator);

    /**
     * Get a sorter. 
     * @param <T>
     * @return sorter
     */
    <T> Sorter<T> getSorter();
}
