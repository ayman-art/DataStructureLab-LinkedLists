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

        head = new Node(null);
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

        temp.value =  element;

     //   printList(this);

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

   

    
}





































interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{
    
      ILinkedList A = new SingleLinkedList();
    ILinkedList B = new SingleLinkedList();
    ILinkedList C = new SingleLinkedList();
    ILinkedList R = new SingleLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        ILinkedList temp = getPoly(poly);

        if (temp == null) {
            { System.out.println("Error");System.exit(0);}
            return;
        }

        temp.clear();
        if(terms.length==0){System.out.println("Error");System.exit(0);}
        int n = terms.length;
        for (int i = 0; i < n; ++i) {
            temp.add(terms[i]);
        }
    }

    public String print(char poly) {
        
        ILinkedList list = getPoly(poly);

        if (list == null) {
            {System.out.println("Error");System.exit(0);}
            return null;
        }
        if (list.size()==0){return "0";}

        int n = list.size();
        String expression = "";

        for(int i =0 ; i < n ; ++i)
            expression+= getTerm(((int[]) list.get(i)));

        if(expression.equals("")){return "0";}
        if(expression.charAt(0)=='+')
            return expression.substring(1);   
        return expression;
    }

    public void clearPolynomial(char poly) {
        ILinkedList temp = getPoly(poly);

        if (temp == null|| temp.size() == 0) {
            {System.out.println("Error");System.exit(0);}
            return;
        }

        temp.clear();
    }

    public float evaluatePolynomial(char poly, float value) {
        float res = 0;
        ILinkedList temp = getPoly(poly);

        if (temp == null|| temp.size() == 0)
        {System.out.println("Error");System.exit(0);}

        int n = temp.size();
        for (int i = 0; i < n; ++i) {
            int coef = ((int[]) temp.get(i))[0];
            int exp = ((int[]) temp.get(i))[1];

            res += coef *  Math.pow(value, exp);
        }

        return res;
    }

    public int[][] add(char poly1, char poly2) {
        ILinkedList temp1 = getPoly(poly1);
        ILinkedList temp2 = getPoly(poly2);

        if (temp1 == null || temp2 == null || temp1.size() == 0 || temp2.size() == 0)
        {System.out.println("Error");System.exit(0);return null;}

        R.clear();
        int i = temp1.size() - 1, j = temp2.size() - 1;
        while (i >= 0 && j >= 0) {
            int coef1 = ((int[]) temp1.get(i))[0], coef2 = ((int[]) temp2.get(j))[0];
            int exp = ((int[]) temp1.get(i))[1];

            int[] res = {coef1 + coef2, exp};
            R.add(0, res);

            i--;
            j--;
        }

        while (i >= 0) {
            int coef1 = ((int[]) temp1.get(i))[0];
            int exp = ((int[]) temp1.get(i))[1];

            int[] res = {coef1 , exp};
            R.add(0, res);

            i--;
        }

        while (j >= 0) {
            int coef2 = ((int[]) temp2.get(j))[0];
            int exp = ((int[]) temp2.get(j))[1];

            int[] res = {coef2, exp};
            R.add(0, res);

            j--;
        }

        return getArr2D(R);

    }

    public int[][] subtract(char poly1, char poly2) {
        ILinkedList temp1 = getPoly(poly1);
        ILinkedList temp2 = getPoly(poly2);

        if (temp1 == null || temp2 == null || temp1.size() == 0 || temp2.size() == 0)
        {System.out.println("Error");System.exit(0);return null;}

        R.clear();
        int i = temp1.size() - 1, j = temp2.size() - 1;
        while (i >= 0 && j >= 0) {
            int coef1 = ((int[]) temp1.get(i))[0], coef2 = ((int[]) temp2.get(j))[0];
            int exp = ((int[]) temp1.get(i))[1];

            int[] res = {coef1 - coef2, exp};
            R.add(0, res);

            i--;
            j--;
        }

        while (i >= 0) {
            int coef1 = ((int[]) temp1.get(i))[0];
            int exp = ((int[]) temp1.get(i))[1];

            int[] res = {coef1 , exp};
            R.add(0, res);

            i--;
        }

        while (j >= 0) {
            int coef2 = ((int[]) temp2.get(j))[0];
            int exp = ((int[]) temp2.get(j))[1];

            int[] res = {-1 * coef2, exp};
            R.add(0, res);

            j--;
        }
        if (R.size()==0){
            System.out.println("0");
        }

        return getArr2D(R);
        
    }

    public int[][] multiply(char poly1, char poly2) {

        ILinkedList temp1 = getPoly(poly1);
        ILinkedList temp2 = getPoly(poly2);

        if (temp1 == null || temp2 == null || temp1.size() == 0 || temp2.size() == 0)
        {System.out.println("Error");System.exit(0);return null;}

        R.clear();
        


        int s1 = temp1.size(), s2 = temp2.size();

        for(int i= s1 + s2-2 ; i>=0 ; i--){
            R.add(new int[]{0,i});
        }


        for(int i = 0 ; i < s1 ; i++){
            for(int j = 0 ; j < s2 ; j++){
                int coef1 = ((int[]) temp1.get(i))[0];
                int coef2 = ((int[]) temp2.get(j))[0];

                int resExp = (s1 -i -1) + (s2 -j -1) ;
                int index  = i+j;
                 

                int oldCoef = ((int[]) R.get(index))[0];
                R.set(index,new int[] {oldCoef + coef1* coef2, resExp });
                



            }
        }

        return getArr2D(R);
    }

    private ILinkedList getPoly(char poly) {
        switch (poly) {
            case 'A':
                return A;

            case 'B':
                return B;

            case 'C':
                return C;

            case 'R':
                return R;

            default:
                return null;
        }
    }

    private static int[] getArr1D(Scanner scanner) {
        String[] s = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");

        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for (int i = 0; i < s.length; ++i) {
                arr[i] = Integer.parseInt(s[i]);
            }
        }

        return arr;
    }

    private static int[][] getArr2D(int[] arr) {
        
        int n = arr.length;
        int[][] res = new int[n][2];

        for (int i = 0; i < n; ++i) {
            res[i][0] = arr[i];
            res[i][1] = n - i - 1;
        }

        return res;
    }

    private static int[][] getArr2D(ILinkedList list) {
        int n = list.size();
        int[][] res = new int[n][2];

        for (int i = 0; i < n; ++i){
            res[i][0] = ((int[]) list.get(i))[0];
            res[i][1] = ((int[]) list.get(i))[1];
        }

        return res;
    }
    
    private String getTerm(int[] term) {


        String termString = "";

        if(term[0]==0){
            return termString;
        }


        // Sign
        if(term[0]>0)
            termString += "+";


        // coof
        if(term[1]==0){
            return termString+String.valueOf(term[0]);
        }


        if(term[0]==1 );

        else if (term[0]==-1){
            termString += "-";
        }else{
            termString +=String.valueOf(term[0]);
        }

        // exp
        if(term[1]==0);

        else if(term[1]==1){
            termString += "x";
        }else{
            termString +="x^" + String.valueOf(term[1]);
        }



        return termString;
    }

    public static void HandleInput(IPolynomialSolver list) {
        Scanner scanner = new Scanner(System.in);

        String line;
        String poly1, poly2;
        int[] arr1D;
        int[][] arr2D;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            switch (line) {
                case "set":
                    poly1 = scanner.nextLine();
                    arr1D = getArr1D(scanner);
                    arr2D = getArr2D(arr1D);

                    list.setPolynomial(poly1.charAt(0), arr2D);
                    break;

                case "print":
                    poly1 = scanner.nextLine();
                    String res = list.print(poly1.charAt(0));
                    System.out.println(res);
                    break;

                case "add":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.add(poly1.charAt(0), poly2.charAt(0));
                    res = list.print('R');
                    System.out.println(res);
                    break;

                case "sub":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.subtract(poly1.charAt(0), poly2.charAt(0));
                    res = list.print('R');
                    System.out.println(res);
                    break;

                case "mult":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.multiply(poly1.charAt(0), poly2.charAt(0));
                    res = list.print('R');
                    System.out.println(res);
                    break;

                case "clear":
                    poly1 = scanner.nextLine();
                    list.clearPolynomial(poly1.charAt(0));
                    System.out.println("[]");
                    break;

                case "eval":
                    poly1 = scanner.nextLine();
                    float value = scanner.nextFloat();
                    int ans = (int)list.evaluatePolynomial(poly1.charAt(0), value);
                    System.out.println(ans);
                    break;

                default:
                    System.out.println("Error");System.exit(0);
                    break;
            }
        }

        scanner.close();
    }

    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try{
     
        IPolynomialSolver solver = new PolynomialSolver();

        HandleInput(solver);
        
         
        }catch(Exception e){
             System.out.println(e);
            e.printStackTrace(); 
            System.exit(0);
        }
       
        
        
        
    }
}