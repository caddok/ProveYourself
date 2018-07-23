package codingbat1;

public class changePi {
    public static void main(String[] args) {
        String mock = "pipi";
        System.out.println(changePi(mock));
    }
    public static String changePi(String str) {
        if (str.equals("")) {
            return "";
        }
        StringBuilder newString = new StringBuilder();
        String lastChar = String.valueOf(str.charAt(str.length() - 1));
        if (lastChar.equals("i") && str.length() >= 2) {
            String next = String.valueOf(str.charAt(str.length() - 2));
            if (next.equals("p")) {
                newString.append("3.14");
                return changePi(str.substring(0,str.length() - 2)) + newString;
            }else {
                newString.append(lastChar);
            }
        }else {
            newString.append(lastChar);
        }
        return changePi(str.substring(0,str.length() - 1)) + newString;
    }
}
