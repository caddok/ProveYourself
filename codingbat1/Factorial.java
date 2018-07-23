package codingbat1;

public class Factorial {
    public static void main(String[] args) {
        int factorial = 5;
        System.out.println(getFactorial(factorial));
    }
    public static int getFactorial(int n) {
        if(n == 1) {
            return 1;
        }
        return n * getFactorial(n -1);
    }
}
