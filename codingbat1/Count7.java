package codingbat1;

public class Count7 {
    public static void main(String[] args) {
        int mock = 747;
        System.out.println(count7(mock));
    }
    public static int count7(int n) {
        if (n == 0) {
            return 0;
        }
        int lastDigit = n % 10;
        int count = 0;
        if (lastDigit == 7) {
            count++;
        }
        return count7(n / 10) + count;
    }
}
