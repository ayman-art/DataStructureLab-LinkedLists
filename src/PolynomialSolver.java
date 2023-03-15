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


public class PolynomialSolver /*implements IPolynomialSolver*/{




    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String op;

        while(sc.hasNextLine()){
            op = sc.nextLine();
            switch(op){

                case "set":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    break;
                case "print":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    break;
                case "add":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    break;
                case "sub":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    break;
                case "mult":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    break;
                case "clear":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    break;
                case "eval":
                    System.out.println(op);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    break;




            }

        }







    }
}