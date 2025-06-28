package sortingservice;

import java.util.Comparator;

/**
 * A sorter sorts it input.
 *
 * The implementation is expected to return the <b>same</b> queue instance.
 *
 * @author Pieter van den Hombergh
 * @param <T> type of elements in queue.
 */
public interface Sorter<T> {

    /**
     * Return the input queue in sorted order, based on the passed comparator.
     *
     * @param q to be sorted
     * @param comparator to base sorting on
     * @return the queue with the elements in non-descending order as per the comparator.
     */
    Queue<T> sort( Queue<T> q, Comparator<T> comparator );
}
