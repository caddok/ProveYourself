package codingbat1;

public class EndX {
    public static void main(String[] args) {
        String mock = "xxhixx";
        System.out.println(endX(mock));
    }
    public static String endX(String str) {
        return onlyX(str,new StringBuilder(), new StringBuilder());
    }

    private static String onlyX(String str, StringBuilder nonX, StringBuilder onlyX) {
        if(str.length() == 0) {
            return nonX.append(onlyX).toString();
        }
        if (str.charAt(0) == 'x') {
            return onlyX(str.substring(1),nonX,onlyX.append("x"));
        } else {
            return onlyX(str.substring(1),nonX.append(str.charAt(0)), onlyX);
        }
    }

}
