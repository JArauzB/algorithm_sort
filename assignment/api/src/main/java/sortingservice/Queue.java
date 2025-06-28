package sortingservice;

/**
 * Simple FIFO queue.
 *
 * <p>
 * This queue is used as an interface to input and output data to the sorters. 
 * In particular, queue.put(E t) is used to provide data to the sorter and 
 * E queue.get() is used to get the data (in the order required by the comparator).
 * </p>
 *
 * <p>
 * This queue extends the {@link java.lang.Iterable Iterable} interface,
 * because it makes checking for ordered-ness particularly easy with assertJ.
 * </p>
 *
 * <p>
 * The developer may choose the best queue implementation for its sort strategy.
 * Note that singly-linked queues can be more space efficient than doubly linked
 * queues.
 * </p>
 *
 * @author Pieter van den Hombergh
 * @param <E> type of elements in queue.
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Add element to the end of queue.
     *
     * @param t element to add
     *
     */
    void put( E t );

    /**
     * Remove element of front of the queue and return it.
     *
     * @return the first element in this queue, or null if queue is empty.
     */
    E get();

    /**
     * Checks if queue is empty.
     *
     * @return true if empty, false if not.
     */
    boolean isEmpty();

    /**
     * The number of elements contained in this queue.
     *
     * @return the number of elements.
     */
    long size();
}
