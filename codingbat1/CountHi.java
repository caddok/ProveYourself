package codingbat1;

public class CountHi {
    public static void main(String[] args) {
        String mock = "hihih";
        System.out.println(countHi(mock));
    }
    public static int countHi(String str) {
        if (str.equals("") || str.length() < 2) {
            return 0;
        }
        String lastChar = str.substring(0,1);
        int count = 0;
        if (lastChar.equals("h")) {
            String nextChar = str.substring(1,2);
            if (nextChar.equals("i")) {
                count++;
            }
        }
        if (count > 0) {
            str = str.substring(2);
            return countHi(str) + count;
        }
        return countHi(str.substring(1)) + count;
    }
}
