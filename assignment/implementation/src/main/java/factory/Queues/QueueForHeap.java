/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Queues;

import factory.Nodes.TreeNode;
import java.util.Comparator;
import java.util.Iterator;
import sortingservice.PriorityQueue;

/**
 *
 * @author jorge
 * @param <T>
 */
public class QueueForHeap<T> implements PriorityQueue<T> {

    private final Comparator comparator;

    //next parents 
    private final TreeNode<T> headSecondary = new TreeNode<>(null);
    private final TreeNode<T> tailSecondary = new TreeNode<>(null);
    private TreeNode<T> current;
    private TreeNode<T> lasElement;
    private int size = 0;

    public QueueForHeap(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void put(T t) {

        TreeNode<T> newElement = new TreeNode(t);
        //add root
        if (headSecondary.getNext() == null) {
            current = newElement;
            addNextTmpParent(newElement);
            size = size + 1;
            return;
        }
        //get first element from the tmp queue
        TreeNode<T> lastIncompletedParent = current;
        // check if parent is full
        if (lastIncompletedParent.getLeft() == null) {
            lastIncompletedParent.setLeftChild(newElement);
            addNextTmpParent(newElement);
            swim(lastIncompletedParent);
        } else if (lastIncompletedParent.getRight() == null) {
            lastIncompletedParent.setRightChild(newElement);
            addNextTmpParent(newElement);
            //check other previous nodes for min heap
            swim(lastIncompletedParent);
            deleteFirstElement();
        }
        lasElement = newElement;
        size = size + 1;

    }

    @Override
    public T get() {
        if (isEmpty()) {
            return null;
        }
//        System.out.println("----------------------------------------------------");
        TreeNode<T> returnableElement = headSecondary.getNext();
        System.out.println("returnable element " + returnableElement.getT());
//        System.out.println("");
        //if queue has only one element
        if (size < 2) {
            size = 0;
            headSecondary.setNext(null);
            return returnableElement.getT();
        } //if queue has only two elements
        else if (size < 3) {
            if (comparator.compare(returnableElement.getT(), returnableElement.getLeft().getT()) > 0) {
                sink(returnableElement, returnableElement.getLeft());
            }
            T tmpElement = returnableElement.getT();
            sink(returnableElement, returnableElement.getLeft());
            headSecondary.getNext().setNext(tailSecondary);
            returnableElement.setLeftChild(null);
            size = size - 1;
            //delete last element
            return tmpElement;
        } //if queue has only two elements
        else if (size < 3) {
            if (comparator.compare(returnableElement.getT(), returnableElement.getLeft().getT()) > 0) {
                sink(returnableElement, returnableElement.getLeft());
            }
            T tmpElement = returnableElement.getT();
            sink(returnableElement, returnableElement.getLeft());
            headSecondary.getNext().setNext(tailSecondary);
            returnableElement.setLeftChild(null);
            //delete last element
            return tmpElement;
        }
//        System.out.println("current tree");
//        showNodes();
        sink(returnableElement, lasElement);
//        System.out.println("sink first element to the last position");
//        showNodes();
        T tmpT = lasElement.getT();
        TreeNode<T> next = headSecondary;
        while (next.getNext().getT() != null) {
            //remove last element
            if (next.getNext().getLeft() == null && next.getNext().getRight() == null) {
                if (next.getRight() == null) {
                    next.setLeftChild(null);
                    lasElement = next.getPrev().getRight();
                } else {
                    next.setRightChild(null);
                    lasElement = next.getLeft();
                }
                //removing last element from the queue
                TreeNode<T> newPrev = tailSecondary.getPrev().getPrev();
                newPrev.setNext(tailSecondary);
                tailSecondary.setPrev(newPrev);
                break;
            }
            next = next.getNext();
        }
        sinkRoot(headSecondary.getNext());
//        System.out.println("new queue ");
//        showNodes();
        size = size - 1;
        return tmpT;
    }

    @Override
    public <E> Comparator<E> getComparator() {
        return this.comparator;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return (size > 0);
            }

            @Override
            public T next() {
                return get();
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public long size() {
        return this.size;
    }

    private void deleteFirstElement() {
        current = current.getNext();
    }

//    public void showNodes() {
//        TreeNode<T> next = headSecondary;
//        while (next.getNext().getT() != null) {
//            if (next.getNext().getLeft() == null && next.getNext().getRight() == null) {
//                break;
//            }
//            System.out.println(next.getNext());
//            next = next.getNext();
//
//        }
//    }

    private void addNextTmpParent(TreeNode<T> newParent) {
        if (headSecondary.getNext() == null) {
            headSecondary.setNext(newParent);
            newParent.setPrev(headSecondary);
            newParent.setNext(tailSecondary);
            tailSecondary.setPrev(newParent);
        } else {
            tailSecondary.getPrev().setNext(newParent);
            newParent.setPrev(tailSecondary.getPrev());
            newParent.setNext(tailSecondary);
            tailSecondary.setPrev(newParent);
        }

    }

    private void sinkRoot(TreeNode<T> root) {
//        System.out.println("setting new root to the right position");
        TreeNode<T> tmpCurrent = root;

//        System.out.println("current parent p " + tmpCurrent.getT());
        //loop next parents 
        while (tmpCurrent.getNext().getT() != null) {
            //stop if next parent has no children
            if (tmpCurrent.getNext().getLeft() == null && tmpCurrent.getNext().getRight() == null) {
                break;
            }
            //if left side is smaller than parent element then sink
//            System.out.println(tmpCurrent.getLeft().getT() + " " + tmpCurrent.getRight().getT());
//            System.out.println(tmpCurrent);

            if (comparator.compare(tmpCurrent.getLeft().getT(), tmpCurrent.getRight().getT()) <= 0) {
                if (comparator.compare(tmpCurrent.getT(), tmpCurrent.getLeft().getT()) > 0) {
                    sink(tmpCurrent, tmpCurrent.getLeft());
//                    System.out.println("left " + tmpCurrent);
                }

                tmpCurrent = tmpCurrent.getLeft();
//                System.out.println("new current " + tmpCurrent);

            } else {
                if (comparator.compare(tmpCurrent.getT(), tmpCurrent.getLeft().getT()) < 0
                        && comparator.compare(tmpCurrent.getT(), tmpCurrent.getRight().getT()) < 0) {
                    break;
                }
                sink(tmpCurrent, tmpCurrent.getRight());
//                System.out.println("right " + tmpCurrent);
                tmpCurrent = tmpCurrent.getRight();
//                System.out.println("new current " + tmpCurrent);
//                System.out.println(tmpCurrent.getNext());
            }

        }
        //min heap for last position
        if (tmpCurrent.getLeft() != null && comparator.compare(tmpCurrent.getT(), tmpCurrent.getLeft().getT()) > 0) {
            sink(tmpCurrent, tmpCurrent.getLeft());
        }
        if (tmpCurrent.getRight() != null && comparator.compare(tmpCurrent.getT(), tmpCurrent.getRight().getT()) > 0) {
            sink(tmpCurrent, tmpCurrent.getRight());
        }
        //change root for min heap
        root = headSecondary.getNext();
        if (root.getLeft() != null && comparator.compare(root.getT(), root.getLeft().getT()) > 0) {
            sink(root, root.getLeft());
        }
        if (root.getRight() != null && comparator.compare(root.getT(), root.getRight().getT()) > 0) {
            sink(root, root.getRight());
        }
    }

    private void swim(TreeNode<T> parent) {
        //loop previous nodes
        TreeNode<T> tmpCurrent = parent;
//        System.out.println("current parent p " + tmpCurrent.getT());
        while (tmpCurrent.getPrev().getT() != null) {
            if (comparator.compare(tmpCurrent.getT(), tmpCurrent.getLeft().getT()) > 0) {
                sink(tmpCurrent, tmpCurrent.getLeft());
            }
            if (tmpCurrent.getRight() != null && comparator.compare(tmpCurrent.getT(), tmpCurrent.getRight().getT()) > 0) {
                sink(tmpCurrent, tmpCurrent.getRight());
            }
            tmpCurrent = tmpCurrent.getPrev();
        }

        //change root for min heap
        TreeNode<T> root = headSecondary.getNext();
        if (comparator.compare(root.getT(), root.getLeft().getT()) > 0) {
            sink(root, root.getLeft());
        }
        if (root.getRight() != null && comparator.compare(root.getT(), root.getRight().getT()) > 0) {
            sink(root, root.getRight());
        }
    }

    private void sink(TreeNode<T> parent, TreeNode<T> child) {
        //sink current 
        T newChildElement = parent.getT();
        parent.setNewT(child.getT());
        child.setNewT(newChildElement);
    }
}
