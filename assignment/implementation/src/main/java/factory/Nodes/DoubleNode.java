/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Nodes;

/**
 *
 * @author jorge
 * @param <T>
 */
public class DoubleNode<T> {

    private final T t;
    private int position;

    private DoubleNode next;
    private DoubleNode prev;

    public DoubleNode(T t) {
        this.t = t;
        this.next = null;
    }

    public void setNext(DoubleNode nextNode) {
        next = nextNode;
    }

    public DoubleNode getNext() {
        return this.next;
    }

    public void setPrev(DoubleNode prevNode) {
        prev = prevNode;
    }

    public DoubleNode getPrev() {
        return this.prev;
    }

    public T getT() {
        return this.t;
    }

    public int getPosition() {
        return this.position;
    }
    
    public void setNewPosition(int newPosition) {
        this.position = newPosition;
    }

//    @Override
//    public String toString() {
//        return "" + t + "(" + this.position + ")";
//    }
}
