package ru.julia.linkedList;

public class Node {
    private String item;
    private Node next;

    @Override
    public String toString() {
        return "Node{" +
                "item='" + item + '\'' +
                ", next=" + next +
                '}';
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
