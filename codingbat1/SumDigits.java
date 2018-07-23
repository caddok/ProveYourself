package codingbat1;

public class SumDigits {
    public static void main(String[] args) {
        int number = 123;
        System.out.println(sumDigits(number));
    }
    public static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }
        int lastDigit = n % 10;
        return lastDigit + sumDigits(n / 10);
    }

}
