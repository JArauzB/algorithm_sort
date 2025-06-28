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
public class TreeNode<T> {

    private T t;

    private TreeNode next;
    private TreeNode prev;

    public TreeNode(T t) {
        this.t = t;
    }

    public void setNext(TreeNode nextNode) {
        next = nextNode;
    }

    public TreeNode getNext() {
        return this.next;
    }

    public void setPrev(TreeNode prevNode) {
        prev = prevNode;
    }
    
    public void setNewT(T t) {
        this.t = t;
    }

    public TreeNode getPrev() {
        return this.prev;
    }

    private TreeNode leftChild;
    private TreeNode rightChild;

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode getLeft() {
        return this.leftChild;
    }

    public TreeNode getRight() {
        return this.rightChild;
    }

    public T getT() {
        return this.t;
    }


    @Override
    public String toString() {
        String parent = (t == null ? "N/A" : getT()).toString();
        String left = (leftChild == null ? "N/A" : leftChild.getT()).toString();
        String right = (rightChild == null ? "N/A" : rightChild.getT()).toString();
        return "parent " + parent + " left: " + left + " right: " + right;
    }

}
