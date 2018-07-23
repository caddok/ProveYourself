package codingbat;

public class ChangeXY {
    public static void main(String[] args) {
        String mock = "codex";
        System.out.println(changeXY(mock));
    }
    public static String changeXY(String str) {
        if (str.equals("")){
            return "";
        }
        StringBuilder newString = new StringBuilder();
        String currentChar = String.valueOf(str.charAt(str.length() - 1));
        if (currentChar.equals("x")) {
            newString.append("y");
            return changeXY(str.substring(0,str.length() - 1)) + newString;
        }
        else {
            newString.append(currentChar);
        }
        return changeXY(str.substring(0,str.length() - 1)) + newString;
    }

}
