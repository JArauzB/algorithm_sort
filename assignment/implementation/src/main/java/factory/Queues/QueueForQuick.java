/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Queues;

import factory.Nodes.DoubleNode;
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
public class QueueForQuick<T> implements Queue<T> {

    public final DoubleNode<T> head = new DoubleNode<>(null);
    public final DoubleNode<T> tail = new DoubleNode<>(null);
    private int currentPosition = 1;
    

    public QueueForQuick() {
        head.setNext(tail);
        tail.setPrev(head);
    }
    

    @Override
    public void put(T t) {
        DoubleNode<T> tmpPrevious = tail.getPrev();
        DoubleNode<T> newElement = new DoubleNode<>(t);
        newElement.setNewPosition(this.currentPosition);
        this.currentPosition = (currentPosition + 1);
        tail.setPrev(newElement);
        newElement.setNext(tail);
        tmpPrevious.setNext(newElement);
        newElement.setPrev(tmpPrevious);
    }

    @Override
    public T get() {
        if (head.getNext() == null || head.getNext().equals(tail)) {
            return null;
        }
        DoubleNode<T> nodeToBeRemoved = this.head.getNext();
        this.head.setNext(nodeToBeRemoved.getNext());
        this.head.getNext().setPrev(head);
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            DoubleNode<T> current = head;

            @Override
            public boolean hasNext() {
                return (current.getNext() != null && !(current.getNext().equals(tail)));
            }

            @Override
            public T next() {
                DoubleNode<T> currentNext = current.getNext();
                current = current.getNext();
                return currentNext.getT();
            }
        };
    }

    Stream<T> stream() {
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator(), ORDERED);
        return StreamSupport.stream(spliterator, false);
    }

}
