ces the element at the specified position in this list with the
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
        }
    }

    private Node head, tail;
    int size;

    public void add(int index, Object element) {
        
    }

    public void add(Object element) {

    }

    public Object get(int index) {
        return null;
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


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner sc = new Scanner(System.in);
        String linked = sc.nextLine();
        
        String op = sc.nextLine();
        System.out.println(linked);
        
        
        //Replace the printing statements by the function calls
        
        switch(op){
                
            case "add":
                 
                System.out.println(op);
                System.out.println(sc.nextInt());
                break;
            case "addToIndex":
                System.out.println(op);
                System.out.println(sc.nextInt());
                System.out.println(sc.nextInt());
                break;
            case "get":
                System.out.println(op);  
                System.out.println(sc.nextInt());
                break;  
            case "set":
                System.out.println(op); 
                System.out.println(sc.nextInt());
                System.out.println(sc.nextInt());
                break;   
            case "clear":
                System.out.println(op); 
                break;   
            case "isEmpty":
                System.out.println(op); 
                break;   
            case "remove":
                System.out.println(op);
                System.out.println(sc.nextInt());
                break;    
            case "size":
                System.out.println(op);
                break;    
            case "sublist":
                System.out.println(op); 
                System.out.println(sc.nextInt());
                System.out.println(sc.nextInt());
                break; 
            case "contains":
                System.out.println(op);
                System.out.println(sc.nextInt());
                break;    
                
                               
        }
    }
}

