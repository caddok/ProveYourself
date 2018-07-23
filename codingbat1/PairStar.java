package codingbat1;

public class PairStar {
    public static void main(String[] args) {
        String mock = "hello";
        System.out.println(pairStar(mock));
    }
    public static String pairStar(String str) {
        if (str.equals("")) {
            return "";
        }
        String lastChar = String.valueOf(str.charAt(str.length() - 1));
        StringBuilder newString = new StringBuilder();
        if (str.length() >= 2) {
            String next = String.valueOf(str.charAt(str.length() - 2));
            if(lastChar.equals(next)) {
                newString.append("*");
                newString.append(lastChar);
                return pairStar(str.substring(0,str.length() - 1)) + newString;
            }
            else {
                newString.append(lastChar);
            }
        }else {
            newString.append(lastChar);
        }
        return pairStar(str.substring(0,str.length() - 1)) + newString;
    }
}
