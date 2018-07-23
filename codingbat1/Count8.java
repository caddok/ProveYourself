package codingbat1;

public class Count8 {
    public static void main(String[] args) {
        int mock = 8818;
        System.out.println(count8(mock));
    }
    public static int count8(int n) {
        if (n == 0) {
            return 0;
        }
        int lastDigit = n % 10;
        int nextDigit = (n / 10) % 10;
        int count = 0;
        if (lastDigit == 8) {
            if (lastDigit == nextDigit){
                count += 2;
            }else {
                count++;
            }
        }

        return count8(n / 10) + count;
    }
}
