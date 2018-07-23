package codingbat1;

public class noX {
    public static void main(String[] args) {
        String mock = "xx";
        System.out.println(noX(mock));
    }
    public static String noX(String str) {
        if (str.equals("")) {
            return "";
        }
        StringBuilder newString = new StringBuilder();
        String lastChar = String.valueOf(str.charAt(str.length() - 1));
        if (!lastChar.equals("x")) {
            newString.append(lastChar);
        }
        return noX(str.substring(0,str.length() - 1)) + newString;
    }
}
