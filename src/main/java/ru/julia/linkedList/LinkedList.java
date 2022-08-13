package ru.julia.linkedList;

public class LinkedList {
    Node head;
    Node tail;
    int size;

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    public void add(String item) {
        Node currentNode = new Node();
        currentNode.setItem(item);
        if(tail == null) {
            head = currentNode;
        } else {
            tail.setNext(currentNode);
        }
        size++;
        tail = currentNode;
    }

    public String get(int index) {
        if(checkIndex(index)) {
            Node currentNode = getNode(index);
            return currentNode.getItem();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }

    public Node getNode(int index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public String remove(int index) {
        if(checkIndex(index)) {
            Node removeNode;
            if(index == 0) {
                removeNode = head;
                head = head.getNext();
            } else {
                Node prevNode = getNode(index - 1);
                removeNode = getNode(index);
                Node nextNode = removeNode.getNext();
                prevNode.setNext(nextNode);
                if(nextNode == null) {
                    tail = prevNode;
                }
            }
            size--;
            return removeNode.getItem();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
