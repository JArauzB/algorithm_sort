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
public class SingleNode<T> {  
    private final T t;
    
    private SingleNode next;
 

    public SingleNode(T t) {
        this.t = t;
        this.next = null;
    }

    public void setNext(SingleNode nextNode) {
        next = nextNode;
    }

    public SingleNode getNext() {
        return this.next;
    }
    
    public T getT(){
        return this.t;
    }

//    @Override
//    public String toString() {
//        return "Node " + "" + t;
//    }
    
   

}
