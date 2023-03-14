import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}


public class SingleLinkedList implements ILinkedList {
    private class Node {
        public int value;
        public Node next;

        Node(int val) {
            value = val;
            next = null;
        }

        Node(int val, Node nex) {
            value = val;
            next = nex;
        }
    }

    private Node head, tail;
    int size;

    SingleLinkedList() {

        head = new Node(0);
        tail = head;
        size = 0;

    }

    public void add(int index, Object element) {

        if (index < 0 || index > size) {
            System.out.println("Error");
            return;
        }

        Node temp = head;

        for (int i = 0; i < index; ++i)
            temp = temp.next;

        Node elem = new Node((int) element, temp.next);
        temp.next = elem;

        size++;
        printList();

    }

    public void add(Object element) {

        tail.next = new Node((int) element);
        tail = tail.next;

        size++;
        printList();

    }

    public Object get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }

        Node temp = head.next;
        for (int i = 0; i < index; ++i)
            temp = temp.next;

        return temp.value;

    }

    public void set(int index, Object element) {

    }

    public void clear() {

    }

    public boolean isEmpty() {
        return false;
    }

    public void remove(int index) {

    }

    public int size() {
        return 0;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    public boolean contains(Object o) {
        return false;
    }

    private void printList() {
        Node temp = head.next;

        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.value);

            if (temp.next != null)
                System.out.print(", ");

            temp = temp.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        SingleLinkedList ls = new SingleLinkedList();
        ls.add(2);
        ls.add(3);
        ls.add(4);
        ls.add(6);

        ls.add(1, 10);
        ls.add(10, 10);
        ls.add(5, 60);
        ls.add(-5, 50);
        ls.add(0, 20);
        ls.add(4, 30);

        System.out.println(ls.get(0));
        System.out.println(ls.get(4));
        System.out.println(ls.get(2));
        System.out.println(ls.get(15));
    }
}