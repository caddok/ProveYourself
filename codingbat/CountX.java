package codingbat;

public class CountX {
    public static void main(String[] args) {
        String mock = "xxhixx";
        System.out.println(countX(mock));
    }
    public static int countX(String str) {
        if (str.equals("")) {
            return 0;
        }
        String nextChar = str.substring(0,1);
        int count = 0;
        if (nextChar.equals("x")) {
            count++;
        }
        return countX(str.substring(1)) + count;
    }

}
