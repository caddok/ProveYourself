package codingbat;

public class Fibonacci {
    public static void main(String[] args) {
        int fibNumber = 6;
        System.out.println(fibonacci(fibNumber));
    }
    public static int fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
