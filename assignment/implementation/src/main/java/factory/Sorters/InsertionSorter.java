/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import factory.Nodes.SingleNode;
import java.util.Comparator;
import sortingservice.Queue;
import sortingservice.Sorter;

/**
 *
 * @author jorge
 * @param <T>
 */
public class InsertionSorter<T> implements Sorter<T> {
//
//    private final DoubleLinkedList<T> head = new DoubleLinkedList<>(null);
//    private final DoubleLinkedList<T> tail = new DoubleLinkedList<>(null);
//    

    @Override
    public Queue<T> sort(Queue<T> q, Comparator<T> comparator) {
        SimpleQueue<T> input = (SimpleQueue) q;
        
        if(input.size() < 1){
            return input;
        }

        SingleNode<T> smallestElement = input.head.getNext();
        SingleNode<T> previous = input.head;
        SingleNode<T> min = new SingleNode(input.stream().min(comparator).get());
        for (SingleNode<T> i = input.head; i != null; i = i.getNext()) {
            if(i.getNext() == null) break;
//            System.out.println("------------------------------");
//            System.out.println("current checking i " + i);
            SingleNode<T> previousOfMin = i.getNext();
            for (SingleNode<T> j = i.getNext(); j != null; j = j.getNext()) {
//                System.out.println("    checking next values to get smallest " + j);
                if (i.getT() == null) {
                    //save previous of min element 
                    if (j.getNext() != null) {
                        if (comparator.compare((T) j.getNext().getT(), min.getT()) == 0) {
                            previousOfMin = j;
                        }
                    }
                }

                //check smallest
                if (comparator.compare(smallestElement.getT(), j.getT()) >= 0) {
                    smallestElement = j;
                }

            }
            //getting previous of smallest element
            for (SingleNode<T> j = i; j != null; j = j.getNext()) {
                if (j.getNext() != null) {
                    if (comparator.compare((T) j.getNext().getT(), smallestElement.getT()) == 0) {
                        
                        previousOfMin = j;
                    }
                }
            }
            //save element to the first postition
            if (previous.getT() == null) {
                previousOfMin.setNext(smallestElement.getNext());
                SingleNode<T> firstNode = input.head.getNext();
                input.head.setNext(smallestElement);
                smallestElement.setNext(firstNode);
                previous = i.getNext();
                i = i.getNext();
                smallestElement = i.getNext();
            } else {
                if (!smallestElement.equals(i)) {
//                    System.out.println("new smallest value " + smallestElement);
//                    System.out.println("previous of sorted elements " + previous);
//                    System.out.println("next of sorted elements " + previous.getNext().getNext());
//                    System.out.println("previous of new min value " + previousOfMin);
                    SingleNode<T> tmpNextOfMinElement = smallestElement.getNext();
//                    System.out.println("next of new min value " + tmpNextOfMinElement);
                    //removing smallest value 
                    previousOfMin.setNext(tmpNextOfMinElement);
                    //Put smallest element 
                    SingleNode<T> tmpNextElementsAfterNewElement = previous.getNext();
                    previous.setNext(smallestElement);
                    smallestElement.setNext(tmpNextElementsAfterNewElement);
                    previous = smallestElement;
                    smallestElement = smallestElement.getNext();
                    i = previous;
                } else {
                    previous = i;
                    smallestElement = i.getNext();
                }
            }

//            if (smallestElement.equals(i)) {
//                System.out.println("no smallest element found");
//
//            }
//            System.out.println("new smallest is " + smallestElement);
//            showNodes(input.head);
//            previous = previous.getNext();
        }

        return input;
    }
   

//    private void showNodes(Node<T> head) {
//        Node<T> next = head;
//        while (next.getNext() != null && next.getNext().getT() != null) {
//            System.out.print(next.getNext().getT() + " ");
//            next = next.getNext();
//        }
//        System.out.println("");
//
//    }
}
