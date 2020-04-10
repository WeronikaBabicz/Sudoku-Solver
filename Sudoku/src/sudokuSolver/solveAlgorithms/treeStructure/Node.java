package sudokuSolver.solveAlgorithms.treeStructure;

import java.util.LinkedList;

public class Node<T> {
    private Node<T> parent = null;
    private LinkedList<Node<T>> offsprings = new LinkedList<Node<T>>();
    private T value;

    public Node(T value, Node<T> parent){
        this.value = value;
        this.parent = parent;
    }

    public Node(T value){
        this.value = value;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isLeaf(){
        return offsprings.isEmpty();
    }

    public Node<T> getOffspring(int idx){
        return offsprings.get(idx);
    }

    public Node<T> addOffspring(T value){
        Node<T> offspring = new Node<T>(value, this);
        return addOffspring(offspring);
    }

    public Node<T> addOffspring(Node<T> node){
        offsprings.add(node);
        return node;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public LinkedList<Node<T>> getOffsprings() {
        return offsprings;
    }

    public void setOffsprings(LinkedList<Node<T>> offsprings) {
        this.offsprings = offsprings;
    }
}
