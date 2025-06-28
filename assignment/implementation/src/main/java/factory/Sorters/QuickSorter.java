/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package factory.Sorters;

import factory.Nodes.DoubleNode;
import factory.Queues.QueueForQuick;
import java.util.Comparator;
import sortingservice.Queue;
import sortingservice.Sorter;

/**
 *
 * @author jorge
 * @param <T>
 */
public class QuickSorter<T> implements Sorter<T> {

    public DoubleNode<T> head;
    public DoubleNode<T> tail;

    private int nodesCountL = 1;
    private int nodesCountR = 1;

    @Override
    public Queue<T> sort(Queue<T> q, Comparator<T> comparator) {
        QueueForQuick<T> input = (QueueForQuick) q;
        this.head = input.head;
        this.tail = input.tail;
        //in case of empty queue
        if (input.size() == 0) {
            return input;
        } //in case of small queue
        else if (input.size() == 1) {
            return input;
        } //in case there are only 2 or 3 elements
        else if (input.size() <= 3) {
            if (comparator.compare((T) this.head.getNext().getT(), (T) this.tail.getPrev().getT()) > 0) {
                swap(this.head.getNext(), this.tail.getPrev());
            }
            if (comparator.compare((T) this.head.getNext().getNext().getT(), (T) this.tail.getPrev().getT()) > 0) {
                swap(this.head.getNext().getNext(), this.tail.getPrev());
            }
            return input;
        }

        //if queue is reversed sorted
        boolean reversedSort = true;
        //if queue is already sorted
        boolean alreadySort = true;
        DoubleNode<T> current = this.head.getNext();
        for (DoubleNode<T> i = current.getNext(); i.getT() != null; i = i.getNext()) {
            if (comparator.compare(current.getT(), i.getT()) > 0) {
                alreadySort = false;
            } else if (comparator.compare(current.getT(), i.getT()) < 0) {
                reversedSort = false;
            }
            current = current.getNext();
        }
        //if only duplicates are found
        if (reversedSort && alreadySort) {
            return input;
        }
        if (alreadySort) {
            return input;
        }
        if (reversedSort) {
            long queueSize = input.size();
            for (DoubleNode<T> i = this.tail.getPrev(); i.getT() != null; i = i.getPrev()) {
                input.put(i.getT());
            }
            for (int i = 0; i < queueSize; i++) {
                input.get();
            }

            return input;
        }
        ////////////////////////////////////////////////
//        System.out.println("current queue");
//        showNodes(this.head, true, this.tail.getPrev().getPosition());

        applyMediamOfThreeElements(comparator, this.head.getNext(), this.tail.getPrev());
        partititon(comparator, this.head.getNext(), this.tail.getPrev());
        return input;
    }

    private void applyMediamOfThreeElements(Comparator<T> comparator, DoubleNode<T> lo, DoubleNode<T> hi) {
        //Apply median of three elements 
        //move medium next to the last element of the queue
        //Medium element will be the previous of last element
//        DoubleNode<T> tmpPrev = lo.getPrev();
        DoubleNode<T> tmpNext = hi.getNext();
        //switch places if element previous to the pive is smaller than the first element
        DoubleNode<T> pivot = hi;
        if (comparator.compare((T) pivot.getPrev().getT(), (T) lo.getT()) < 0) {
            swap(pivot.getPrev(), lo);
            lo = lo.getNext().getPrev();
            pivot = tmpNext.getPrev();
        }
        //compare now the first element with the pive
        //swap if the pive is smaller than the first element
        if (comparator.compare((T) lo.getT(), (T) pivot.getT()) > 0) {
            swap(pivot, lo);
            pivot = tmpNext.getPrev();
        }
//        pivot = tmpNext.getPrev();
//        //check that new pivot is smaller than prev
        if (comparator.compare(pivot.getT(), (T) pivot.getPrev().getT()) > 0) {
            swap(pivot, pivot.getPrev());
        }
    }

    private void partititon(Comparator<T> comparator, DoubleNode<T> lo, DoubleNode<T> hi) {
        //check that the lo.next is equal to the hi

        if (lo.getNext().equals(hi)) {
            //swap if lo is bigger than hi
            if (comparator.compare(lo.getT(), hi.getT()) > 0) {
                swap(hi, lo);
            }
            return;
        }
        boolean stopLoop = false;
        DoubleNode<T> tmpPrev = lo.getPrev();
        DoubleNode<T> tmpNext = hi.getNext();
        boolean movePivotToTheRightPosition = false;
        DoubleNode<T> currentLeft = tmpPrev.getNext();
        DoubleNode<T> currentRight = tmpNext.getPrev().getPrev();
        DoubleNode<T> newPivot = tmpNext.getPrev();

//        System.out.println("pivot " + newPivot);
//        System.out.println("--------------------------");
        while (!stopLoop) {
            stopLoop = true;
            boolean foundLeftElementBiggerThanPivot = false;
//            System.out.println("Checking Left side");
            while (currentLeft.getT() != null && foundLeftElementBiggerThanPivot == false) {
                //stop if left side is bigger or equal than right side
                if (currentLeft.getPosition() >= currentRight.getPosition()) {
                    currentRight = currentLeft;
                    break;
                }
                //check that left elements are always smaller than pivot
                if (comparator.compare(currentLeft.getT(), newPivot.getT()) >= 0) {
                    foundLeftElementBiggerThanPivot = true;
//                    System.out.print("found " + currentLeft);
                    continue;
                }
//                System.out.print(currentLeft + "   ");
                currentLeft = currentLeft.getNext();
            }
//            System.out.println("");
            boolean foundRightElementSmallerThanPivot = false;
//            System.out.println("Checking Right side");
            while (currentRight.getT() != null && foundRightElementSmallerThanPivot == false) {
                if (currentLeft.equals(currentRight)) {
                    currentRight = currentLeft.getNext();
                    movePivotToTheRightPosition = true;
                    break;
                }
                //stop if left side is bigger or equal than right side
                if (currentRight.getPosition() <= currentLeft.getPosition()) {
//                    System.out.println("");
                    movePivotToTheRightPosition = true;
                    break;
                }
                //check that right elements are always bigger than pivot
                if (comparator.compare(currentRight.getT(), newPivot.getT()) <= 0 || (currentLeft.equals(currentRight))) {
                    foundRightElementSmallerThanPivot = true;
//                    System.out.print("found " + currentRight);
                    continue;
                }
//                System.out.print(currentRight + "   ");
                currentRight = currentRight.getPrev();
            }

            //this means that there are elements that are not in the right place
            if ((foundLeftElementBiggerThanPivot) && (foundRightElementSmallerThanPivot)) {
//                System.out.println("");
//                System.out.println("swapping elements");
                //move element from right to the left
                swap(currentLeft, currentRight);
                DoubleNode<T> tmp = currentLeft.getNext().getPrev();
                currentLeft = tmp;
                currentRight = currentRight.getNext().getPrev();
                //Check for equals elements and put it to the front or to the end  
                if (comparator.compare(currentLeft.getT(), newPivot.getT()) == 0) {
                    //Loop left elements to put the same element as pivot
                    for (DoubleNode<T> i = tmpPrev.getNext(); i.getT() != null; i = i.getNext()) {
                        //check that the loop does not check other next
                        if (i.getPosition() > currentLeft.getPosition()) {
                            currentLeft = currentLeft.getNext();
                            break;
                        }
                        if (!(comparator.compare(currentLeft.getT(), i.getT()) == 0)) {
                            swap(i, currentLeft);
//                            System.out.println(currentLeft.getNext() + "l");
//                            currentLeft = i.getNext().getNext();
                            currentLeft = currentLeft.getNext();

//                            System.out.println("same element move to the first positions");
//                            showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());
                            break;
                        }
                    }
                } else {
                    currentLeft = currentLeft.getNext();
                }
//                 Check for equals elements and put it to the front or to the end  
                if (comparator.compare(currentRight.getT(), newPivot.getT()) == 0) {
                    //Loop right elements to put the same element as pivot
                    for (DoubleNode<T> i = tmpNext.getPrev().getPrev(); i.getT() != null; i = i.getPrev()) {
                        //check that the loop does not check other previous
                        if (i.getPosition() < currentRight.getPosition()) {
                            currentRight = currentRight.getPrev();
                            break;
                        }
                        if (!(comparator.compare(currentRight.getT(), i.getT()) == 0)) {

                            swap(i, currentRight);
//                            System.out.println(currentRight.getPrev().getNext() + " r");
//                            currentRight = i.getPrev().getPrev();
                            currentRight = currentRight.getPrev();
//                            System.out.println("same element move to the last positions");
//                            showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());
                            break;
                        }
                    }
                } else {
                    currentRight = currentRight.getPrev();
                }
                stopLoop = false;
            }
//            System.out.println("");
//            showNodes(this.head, true, newPivot.getPosition());
            if (movePivotToTheRightPosition) {
                if (comparator.compare(newPivot.getT(), currentLeft.getT()) > 0) {                
                    currentLeft = currentLeft.getNext();
                }
                swap(currentLeft, newPivot);
                newPivot = currentLeft.getNext().getPrev();
                //check that previous is smaller than pivot
//                if (comparator.compare((T) newPivot.getPrev().getT(), newPivot.getT()) > 0) {
//                    System.out.println("previous is bigger than pivot");
//                    DoubleNode<T> tmp = newPivot.getPrev();
//                    swap(newPivot, tmp);
//                    newPivot = tmp.getNext().getPrev();
//                }
//                //check that next is bigger than pivot
//                if (comparator.compare((T) newPivot.getNext().getT(), newPivot.getT()) > 0) {
//                    System.out.println("next is smaller than pivot");
//                    DoubleNode<T> tmp = newPivot.getNext();
//                    swap(newPivot, tmp);
//                    newPivot = tmp.getPrev().getNext();
//                }
//                System.out.println("hello");
//                showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());

                //Move duplicate elements next to the pivot
                currentLeft = tmpPrev.getNext();
                DoubleNode<T> leftPosition = newPivot;
                //Left side
                while (currentLeft.getT() != null) {
                    if (comparator.compare(currentLeft.getT(), newPivot.getT()) == 0) {
                        if (newPivot.getPosition() <= currentLeft.getPosition()) {
                            break;
                        }
                        swap(currentLeft, leftPosition.getPrev());
                        //move new position for inserting the same element
                        leftPosition = leftPosition.getPrev();

                        currentLeft = currentLeft.getNext();
                        continue;
                    }
                    break;
                }
//                System.out.println("left side move");
//                showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());
                currentRight = tmpNext.getPrev().getPrev();
                DoubleNode<T> rightPosition = newPivot;
                //Right side
                while (currentRight.getT() != null) {
                    if (comparator.compare(currentRight.getT(), newPivot.getT()) == 0) {
                        if (newPivot.getPosition() >= currentRight.getPosition()) {
                            break;
                        }
                        swap(currentRight, rightPosition.getNext());
                        //move new position for inserting the same element
                        rightPosition = rightPosition.getNext();

                        currentRight = currentRight.getPrev();
                        continue;
                    }
                    break;
                }
//                System.out.println("new positions");
//                showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());
//                System.out.println("----------------- NEW PARTITIONS ------------------------");
              
                tmpPrev = tmpPrev.getNext().getPrev();
                DoubleNode<T> lastElementOfLeftPart = getLastNodeOfPartition(comparator, newPivot);
                
//                System.out.println(lastElementOfLeftPart.getPosition() - tmpPrev.getNext().getPosition());
//                if (nodesCountL > 0) {
                nodesCountL = lastElementOfLeftPart.getPosition() - tmpPrev.getNext().getPosition();
////                }
//
                if (nodesCountL > 0 && nodesCountL < 2) {
//                    System.out.println("LEFT BLOCK");
//                    System.out.println("only two values");
                    if (comparator.compare((T) tmpPrev.getNext().getT(), lastElementOfLeftPart.getT()) > 0) {
//                        System.out.println("next is smaller than pivot");
                        swap(tmpPrev.getNext(), lastElementOfLeftPart);
                    }
                } else if (nodesCountL > 1) {
//                    System.out.println("LEFT BLOCK");
//                    showNodes(tmpPrev, true, lastElementOfLeftPart.getPosition());
//                    System.out.println("Applying mediam of three");
                    applyMediamOfThreeElements(comparator, tmpPrev.getNext(), lastElementOfLeftPart);
//                    showNodes(tmpPrev, true, lastElementOfLeftPart.getPosition());
                    lastElementOfLeftPart = lastElementOfLeftPart.getPrev().getNext();
                    
                    partititon(comparator, tmpPrev.getNext(), lastElementOfLeftPart);
                }
                DoubleNode<T> firstElementOfLeftPart = getFirstNodeOfPartition(comparator, newPivot);
                tmpNext = tmpNext.getPrev().getNext();
                nodesCountR = tmpNext.getPrev().getPosition() - firstElementOfLeftPart.getPosition();

//                System.out.println("R " + nodesCountR);
                if (nodesCountR > 0 && nodesCountR < 2) {
//                    System.out.println("RIGHT BLOCK");
                    if (comparator.compare(firstElementOfLeftPart.getT(), (T) tmpNext.getPrev().getT()) > 0) {
//                        System.out.println("next is smaller than pivot");
                        swap(firstElementOfLeftPart, tmpNext.getPrev());
                    }
                } else if (nodesCountR > 0) {
//                    System.out.println("RIGHT BLOCK");
//                    showNodes(firstElementOfLeftPart.getPrev(), true, tmpNext.getPrev().getPosition());
//                    System.out.println("Applying mediam of three");
                    applyMediamOfThreeElements(comparator, firstElementOfLeftPart, tmpNext.getPrev());
//                    showNodes(firstElementOfLeftPart.getPrev(), true, tmpNext.getPrev().getPosition());
                    applyMediamOfThreeElements(comparator, newPivot.getNext(), tmpNext.getPrev());
                    partititon(comparator, firstElementOfLeftPart, tmpNext.getPrev());
                }
            }
//            System.out.println("final positions");
//            showNodes(tmpPrev, true, tmpNext.getPrev().getPosition());

        }
    }

    private DoubleNode<T> getLastNodeOfPartition(Comparator<T> comparator, DoubleNode<T> pivotPosition) {
        if(pivotPosition.getPrev().equals(this.head)) {
            return pivotPosition;
        }
        //Make partitition for the left side of new queue
        if (comparator.compare(pivotPosition.getT(), (T) pivotPosition.getPrev().getT()) != 0) {
            return pivotPosition.getPrev();
        } else {
            for (DoubleNode<T> i = pivotPosition.getPrev(); i.getT() != null; i = i.getPrev()) {
                //This loop gives the previous element of the duplicated element 
                if (comparator.compare(pivotPosition.getT(), i.getT()) != 0) {
                    return i;
                }
            }
        }
        return pivotPosition;
    }

    private DoubleNode<T> getFirstNodeOfPartition(Comparator<T> comparator, DoubleNode<T> pivotPosition) {
        //Make partitition for the Right side of new queue
        if (comparator.compare(pivotPosition.getT(), (T) pivotPosition.getNext().getT()) != 0) {
            return pivotPosition.getNext();
        } else {
            for (DoubleNode<T> i = pivotPosition.getNext(); i.getT() != null; i = i.getNext()) {
                //This loop gives the next element of the duplicated element 
                if (comparator.compare(pivotPosition.getT(), i.getT()) != 0) {
                    return i;
                }
            }
        }
        return pivotPosition;
    }

    private void swap(DoubleNode<T> one, DoubleNode<T> two) {
        //swap nodes first left
        DoubleNode<T> newElementLeft = new DoubleNode(two.getT());
        newElementLeft.setNewPosition(one.getPosition());
        DoubleNode<T> newElementRight = new DoubleNode(one.getT());
        newElementRight.setNewPosition(two.getPosition());
        DoubleNode<T> nextAfterNewLeft = one.getNext();
        one.getPrev().setNext(newElementLeft);
        newElementLeft.setPrev(one.getPrev());
        newElementLeft.setNext(nextAfterNewLeft);
        nextAfterNewLeft.setPrev(newElementLeft);

        //put left element to the right
        DoubleNode<T> nextAfterNewRight = two.getNext();
        two.getPrev().setNext(newElementRight);
        newElementRight.setPrev(two.getPrev());
        newElementRight.setNext(nextAfterNewRight);
        nextAfterNewRight.setPrev(newElementRight);

    }

//    private void showNodes(DoubleNode<T> node, boolean reversed, int position) {
//        
//        if (!reversed) {
////            DoubleNode<T> previous = node;
////            while (previous.getPrev().getT() != null) {
////                System.out.print(previous.getPrev().getT() + "  ");
////                previous = previous.getPrev();
////            }
////            System.out.println("");
//        } else {
//            DoubleNode<T> next = node;
//            while (next.getNext().getT() != null) {
//                if (next.getNext().getPosition() > position) {
//                    break;
//                }
//                System.out.print(next.getNext().getT() + "(" + next.getNext().getPosition() + ")  ");
//                next = next.getNext();
//            }
//            System.out.println("");
//        }
//    }
}
