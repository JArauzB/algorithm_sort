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
public class SelectionSorter<T> implements Sorter<T> {

    @Override
    public Queue<T> sort(Queue<T> q, Comparator<T> comparator) {
        SimpleQueue<T> input = (SimpleQueue) q;
        SingleNode<T> smallestElement = input.head.getNext();
        SingleNode<T> currentPrevious = input.head;
        
        for (SingleNode<T> i = input.head.getNext(); i != null; i = i.getNext()) {
//            System.out.println("-----------------");
//            System.out.println("current i " + i);
            for (SingleNode<T> j = i.getNext(); j != null; j = j.getNext()) {
//                System.out.println("        current j " + j);
                if (comparator.compare(smallestElement.getT(), j.getT()) > 0) {
                    smallestElement = j;
                }
            }
            SingleNode<T> previous = getPrevious(i, smallestElement);
            SingleNode<T> bigCurrent = new SingleNode<>(i.getT());
            SingleNode<T> next = smallestElement.getNext();
            boolean noSwap = true;
            if (comparator.compare(smallestElement.getT(), i.getT()) < 0) {
                noSwap = false;
//                System.out.println("--- swap --");
//                showNodes(i);
//                System.out.println("changing!");
//                System.out.println("smalles " + smallestElement);
////                Swap Data
//                System.out.println("pre " + previous);
//                System.out.println("curr " + bigCurrent);
//                System.out.println("next " + next);
//                System.out.println("smal " + smallestElement);
                if (previous.equals(smallestElement)) {
                    i = i.getNext();
                }
                previous.setNext(bigCurrent);
                bigCurrent.setNext(next);
                currentPrevious.setNext(smallestElement);
                currentPrevious.getNext().setNext(i.getNext());
//                System.out.println("---new---");
//                showNodes(i);
//                System.out.println("--- end --");
            }

            currentPrevious = currentPrevious.getNext();
            i = currentPrevious;
            
//            System.out.println("selected min " + smallestElement.getT());
//            input.put(smallestElement.getT());
            if (noSwap) {
                smallestElement = i.getNext();
            } else {
                smallestElement = bigCurrent;
            }
        }

        return input;
    }

    public SingleNode<T> getPrevious(SingleNode<T> ele1, SingleNode<T> ele2) {
        SingleNode<T> previous = ele2;
        while (ele1.getNext() != null) {
            if (ele2.equals(ele1.getNext())) {
                break;
            }

            previous = ele1.getNext();
            ele1 = ele1.getNext();
        }

        return previous;
    }
}
