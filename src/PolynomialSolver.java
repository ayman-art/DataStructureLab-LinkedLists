import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


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

    ILinkedList_ A = new DoubleLinkedList();
    ILinkedList_ B = new DoubleLinkedList();
    ILinkedList_ C = new DoubleLinkedList();
    ILinkedList_ R = new DoubleLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        ILinkedList_ temp = getPoly(poly);

        if (temp == null) {
            System.out.println("Error");
            return;
        }

        temp.clear();
        int n = terms.length;
        for (int i = 0; i < n; ++i) {
            temp.add(terms[0]);
        }
    }

    public String print(char poly) {
        ILinkedList_ temp = getPoly(poly);

        
        return null;
    }

    public void clearPolynomial(char poly) {
        
        ILinkedList_ temp = getPoly(poly);

        if (temp == null) {
            System.out.println("Error");
            return;
        }
        
        temp.clear();
        
    }

    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    public int[][] add(char poly1, char poly2) {
        return new int[0][];
    }

    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
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

    private static int[][] getArr2D(ILinkedList_ list) {
        int n = list.size();
        int[][] res = new int[n][2];

        for (int i = 0; i < n; ++i){
            res[i][0] = ((int[]) list.get(i))[0];
            res[i][1] = ((int[]) list.get(i))[1];
        }

        return res;
    }

    private ILinkedList_ getPoly(char poly) {
        switch (poly) {
            case 'A':
                return A;

            case 'B':
                return B;

            case 'C':
                return C;

            default:
                return null;
        }
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
        while (true) {
            line = scanner.nextLine();
            if (line.equals(""))
                break;

            switch (line) {
                case "set":
                    poly1 = scanner.next();
                    arr1D = getArr1D(scanner);
                    arr2D = getArr2D(arr1D);

                    list.setPolynomial(poly1.charAt(0), arr2D);
                    break;

                case "print":
                    poly1 = scanner.nextLine();
                    list.print(poly1.charAt(0));
                    break;

                case "add":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.add(poly1.charAt(0), poly2.charAt(0));
                    break;

                case "sub":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.subtract(poly1.charAt(0), poly2.charAt(0));
                    break;

                case "mult":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    list.multiply(poly1.charAt(0), poly2.charAt(0));
                    break;

                case "clear":
                    poly1 = scanner.nextLine();
                    list.clearPolynomial(poly1.charAt(0));
                    break;

                case "eval":
                    poly1 = scanner.nextLine();
                    float value = scanner.nextFloat();
                    list.evaluatePolynomial(poly1.charAt(0), value);
                    break;

                default:
                    System.out.println("Error");
                    break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        IPolynomialSolver solver = new PolynomialSolver();

        HandleInput(solver);
    }

}