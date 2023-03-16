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


class SingleLinkedList implements ILinkedList {
    private class Node {
        public Object value;
        public Node next;

        Node(Object val) {
            value = val;
            next = null;
        }

        Node(Object val, Node nex) {
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

        Node elem = new Node(element, temp.next);
        temp.next = elem;

        size++;

//        printList(this);

    }

    public void add(Object element) {

        tail.next = new Node(element);
        tail = tail.next;

        size++;

    }

    public Object get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }

        Node temp = head.next;
        for (int i = 0; i < index; ++i)
            temp = temp.next;

        return temp.value;

    }

    public void set(int index, Object element) {

        if (index < 0 || index >= size) {
            System.out.println("Error");
            return;
        }

        Node temp = head;
        for (int i = 0; i <= index; ++i)
            temp = temp.next;

        temp.value = element;

//        printList(this);

    }

    public void clear() {

        size = 0;
        head.next = null;
        tail = head;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {

        if (index < 0 || index >= size) {
            System.out.println("Error");
            return;
        }

        Node temp = head;
        for (int i = 0; i < index; ++i)
            temp = temp.next;

        temp.next = temp.next.next;
        size--;

        printList(this);
    }

    public int size() {
        return size;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {

        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            return null;
        }

        ILinkedList sub = new SingleLinkedList();
        Node temp = head;

        for (int i = 0; i <= fromIndex; ++i)
            temp = temp.next;

        for (int i = fromIndex; i <= toIndex; ++i) {
            sub.add(temp.value);
            temp = temp.next;
        }

        return sub;

    }

    public boolean contains(Object o) {

        Node temp =  head.next;

        while (temp != null) {
            if (temp.value == o)
                return true;

            temp = temp.next;
        }

        return false;

    }

    public static void printList(ILinkedList lst) {
        int n = lst.size();

        System.out.print("[");
        for (int i = 0; i < n; ++i) {
            System.out.print(lst.get(i));

            if (i + 1 < n)
                System.out.print(", ");
        }
        System.out.println("]");

    }

    public static int[] getArr(Scanner scanner) {
        String line = scanner.nextLine().replaceAll("\\[|\\]", "");
        String[] s = line.split(", ");

        int[] arr = new int[s.length];

        if (s.length == 1 && s[0].isEmpty()) {
            arr = new int[]{};
        } else {
            for (int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }

        return arr;
    }

    public static void HandleInput(Scanner scanner, ILinkedList list) {
        String operation = scanner.nextLine();

        int num, idx;
        switch (operation) {
            case "add":
                num = scanner.nextInt();
                list.add(num);
                printList(list);
                break;

            case "addToIndex":
                idx = scanner.nextInt();
                num = scanner.nextInt();
                list.add(idx, num);
                break;

            case "get":
                idx = scanner.nextInt();
                Object valid = list.get(idx);
                if (valid == null)
                    System.out.println("Error");
                else
                    System.out.println((int) valid);
                break;

            case "set":
                idx = scanner.nextInt();
                num = scanner.nextInt();
                list.set(idx, num);
                break;

            case "clear":
                list.clear();
                printList(list);
                break;

            case "isEmpty":
                boolean empty = list.isEmpty();
                if (empty)
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "remove":
                idx = scanner.nextInt();
                list.remove(idx);
                break;

            case "sublist":
                int start = scanner.nextInt();
                int end = scanner.nextInt();

                ILinkedList sub = list.sublist(start, end);
                if (sub == null)
                    System.out.println("Error");
                else
                    printList(sub);
                break;

            case "contains":
                num = scanner.nextInt();
                boolean found = list.contains(num);

                if (found)
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "size":
                num = list.size();
                System.out.println(num);
                break;

            default:
                System.out.println("Error");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = getArr(scanner);
        ILinkedList list = new SingleLinkedList();

        for (int i : arr) {
            list.add(i);
        }

        HandleInput(scanner, list);

        scanner.close();
    }
}