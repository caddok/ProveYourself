package codingbat;

public class AllStar {
    public static void main(String[] args) {
        String mock = "";
        System.out.println(allStar(mock));
    }
    public static String allStar(String str) {
        if (str.length() == 1 || str.length() == 0) {
            return str;
        }
        String lastChar = String.valueOf(str.charAt(str.length() - 1));
        StringBuilder newString = new StringBuilder();
        newString.append("*");
        newString.append(lastChar);
        return allStar(str.substring(0,str.length() - 1)) + newString;
    }

}
