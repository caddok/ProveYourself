package codingbat;

public class PowerN {
    public static void main(String[] args) {
        int mockBase = 3;
        int mockPower = 2;
        System.out.println(powerN(mockBase,mockPower));
    }
    public static int powerN(int base, int n) {
        if (n == 1) {
            return base;
        }
        return base * powerN(base,n - 1);
    }
}