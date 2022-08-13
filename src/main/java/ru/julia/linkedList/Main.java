package ru.julia.linkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        System.out.println(list);
        System.out.println(list.get(3));
        list.remove(2);
        System.out.println(list);
    }
}
