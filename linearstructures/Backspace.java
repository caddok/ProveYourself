package linearstructures;

import java.util.Stack;

public class Backspace {
    public static void main(String[] args) {
        String mockFirst = "a##c";
        String mockSecond = "#a#c";
        System.out.println(backspaceCompare(mockFirst,mockSecond));
    }
    public static boolean backspaceCompare(String S, String T) {
        String refinedS = refine(S);
        String refinedT = refine(T);
        return refinedS.equals(refinedT);
    }
    public static String refine(String source) {
        StringBuilder refinedVersion = new StringBuilder();
        Stack<Character> refiner = new Stack<>();
        for (char c : source.toCharArray()) {
            if (refiner.empty() && c == '#') {
                continue;
            }
            if (!refiner.empty() && c == '#') {
                refiner.pop();
            } else {
                refiner.push(c);
            }
        }
        while (!refiner.empty()) {
            refinedVersion.append(refiner.pop());
        }
        return refinedVersion.toString();
    }
}
