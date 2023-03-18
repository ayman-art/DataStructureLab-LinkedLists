
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

    ILinkedList A = new SingleLinkedList();
    ILinkedList B = new SingleLinkedList();
    ILinkedList C = new SingleLinkedList();
    ILinkedList R = new SingleLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        ILinkedList temp = getPoly(poly);

        if (temp == null) {
            throw new RuntimeException();
        }

        temp.clear();
        int n = terms.length;
        for (int i = 0; i < n; ++i) {
            temp.add(terms[i]);
        }
    }

    public String print(char poly) {
        String res = "";
        ILinkedList temp = getPoly(poly);

        if (temp == null || temp.size() == 0) {
            return null;
        }

        int[][] arr = getArr2D(temp);

        res = print(arr);

        return res;
    }

    public void clearPolynomial(char poly) {
        ILinkedList temp = getPoly(poly);

        if (temp == null || temp.size() == 0 || poly == 'R' || print(poly).equals("0")) {
            throw new RuntimeException();
        }
        System.out.println("[]");
        temp.clear();
    }

    public float evaluatePolynomial(char poly, float value) {
        float res = 0.0f;
        ILinkedList temp = getPoly(poly);

        if (temp == null || temp.size() == 0)
            return Float.POSITIVE_INFINITY;

        int n = temp.size();
        for (int i = 0; i < n; ++i) {
            int coef = ((int[]) temp.get(i))[0];
            int exp = ((int[]) temp.get(i))[1];

            res += 1.0 * coef *  Math.pow(value, exp);
        }

        return res;
    }

    public int[][] add(char poly1, char poly2) {
        ILinkedList temp1 = getPoly(poly1);
        ILinkedList temp2 = getPoly(poly2);

        if (temp1 == null || temp2 == null || temp1.size() == 0 || temp2.size() == 0)
            return null;

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
            return null;

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

        return getArr2D(R);
    }

    public int[][] multiply(char poly1, char poly2) {
        ILinkedList temp1 = getPoly(poly1);
        ILinkedList temp2 = getPoly(poly2);

        if (temp1 == null || temp2 == null || temp1.size() == 0 || temp2.size() == 0)
            return null;

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
                int[] res = {oldCoef + coef1* coef2, resExp };
                R.set(index, res);

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

    private String print(int[][] arr) {
        String res = "";

        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            int coef = arr[i][0];
            int exp = arr[i][1];

            if (coef == 0)
                continue;

            if (coef < 0)
                res += "-";
            else
                res += "+";

            if (i + 1 == n || coef != 1)
                res += Math.abs(coef);


            if (exp != 0)
                res += "x";
            if (exp != 1 && exp != 0)
                res += "^" + exp;
        }

        if (res.length() == 0)
            return "0";
        if (res.charAt(0) == '+')
            res = res.substring(1);
        if (res.charAt(0) == '1' && res.length() > 1 && res.charAt(1) == 'x')
            res = res.substring(1);


        return res;
    }

    private static void negativeSign (ILinkedList list) {
        int n = list.size();

        for (int i = 0; i < n; ++i) {
            int coef = ((int[]) list.get(i))[0];
            int exp = ((int[]) list.get(i))[1];

            int[] res = {-1 * coef, exp};
            list.set(i, res);
        }
    }

    private static int[] getArr1D(Scanner scanner) {
        String[] s = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");

        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            return null;
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

    public static void HandleInput(IPolynomialSolver list) {
        Scanner scanner = new Scanner(System.in);

        String line;
        String poly1, poly2;
        int[] arr1D;
        int[][] arr2D;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            switch (line) {
                case "":
                    break;
                case "set":
                    poly1 = scanner.nextLine();
                    arr1D = getArr1D(scanner);
                    if (arr1D == null || poly1.equals("R")) {
                        throw new RuntimeException();
                    }
                    arr2D = getArr2D(arr1D);

                    list.setPolynomial(poly1.charAt(0), arr2D);
                    break;

                case "print":
                    poly1 = scanner.nextLine();
                    String res = list.print(poly1.charAt(0));

                    if (res == null || poly1.equals("R")) {
                        throw new RuntimeException();
                    }

                    System.out.println(res);
                    break;

                case "add":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    arr2D = list.add(poly1.charAt(0), poly2.charAt(0));

                    if (arr2D == null || poly1.equals("R") || poly2.equals("R")) {
                        throw new RuntimeException();
                    }

                    res = list.print('R');
                    System.out.println(res);

                    break;

                case "sub":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    arr2D = list.subtract(poly1.charAt(0), poly2.charAt(0));

                    if (arr2D == null || poly1.equals("R") || poly2.equals("R")) {
                        throw new RuntimeException();
                    }

                    res = list.print('R');
                    System.out.println(res);
                    break;

                case "mult":
                    poly1 = scanner.nextLine();
                    poly2 = scanner.nextLine();
                    arr2D = list.multiply(poly1.charAt(0), poly2.charAt(0));

                    if (arr2D == null || poly1.equals("R") || poly2.equals("R")) {
                        throw new RuntimeException();
                    }

                    res = list.print('R');
                    System.out.println(res);

                    break;

                case "clear":
                    poly1 = scanner.nextLine();

                    list.clearPolynomial(poly1.charAt(0));
                    break;

                case "eval":
                    poly1 = scanner.nextLine();
                    float value = scanner.nextFloat();
                    value = list.evaluatePolynomial(poly1.charAt(0), value);
                    if (value == Float.POSITIVE_INFINITY || poly1.equals("R")) {
                        throw new RuntimeException();
                    }

                    if (value == Math.ceil(value))
                        System.out.println((int)value);
                    else
                        System.out.println(value);

                    break;

                default:
                    throw new RuntimeException();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            IPolynomialSolver solver = new PolynomialSolver();

            HandleInput(solver);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}