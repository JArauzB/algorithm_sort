/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import factory.Nodes.SingleNode;
import java.util.Iterator;
import java.util.Spliterator;
import static java.util.Spliterator.ORDERED;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import sortingservice.Queue;

/**
 *
 * @author jorge
 * @param <T>
 */
public class SimpleQueue<T> implements Queue<T> {

    public final SingleNode<T> head = new SingleNode<>(null);

    @Override
    public void put(T t) {
        SingleNode<T> new_node = new SingleNode(t);
        //set new value to the last position
        SingleNode<T> last = this.head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        // Insert the new_node at last node
        last.setNext(new_node);

    }

    @Override
    public T get() {
        if(head.getNext() == null){
            return null;
        }
        SingleNode<T> nodeToBeRemoved = this.head.getNext();
        this.head.setNext(nodeToBeRemoved.getNext());
        return nodeToBeRemoved.getT();
    }

    @Override
    public boolean isEmpty() {
        return stream().count() > 0;
    }

    @Override
    public long size() {
        return stream().count();
    }

    
    Stream<T> stream() {
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator(), ORDERED);
        return StreamSupport.stream(spliterator, false);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            SingleNode<T> current = head;

            @Override
            public boolean hasNext() {
                return (current.getNext() != null);
            }

            @Override
            public T next() {
                SingleNode<T> currentNext = current.getNext();
                current = current.getNext();
                return currentNext.getT();
            }
        };
    }

}
