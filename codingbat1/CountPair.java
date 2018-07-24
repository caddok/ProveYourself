package codingbat1;

public class CountPair {
    public static void main(String[] args) {
        String mock = "axax";
        System.out.println(countPairs(mock));
    }
    public static int countPairs(String str) {
        if (str.length() < 3) {
            return 0;
        }
        if (str.charAt(0) == str.charAt(2)) {
            return 1 + countPairs(str.substring(1));
        }
        return countPairs(str.substring(1));
    }

}
