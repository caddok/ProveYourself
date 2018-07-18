import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

//2 tests still not working

public class Validate {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(inStream.readLine());
        for (int i = 0; i < testCases; i++) {
            String expression = inStream.readLine();
            if (expression.equals("")) {
                System.out.println("yes");
                continue;
            }
            int wildCardCounter = 0;
            Stack<Character> bracketKeeper = new Stack<>();
            for (int j = 0; j < expression.length(); j++) {
                char charToCheck = expression.charAt(j);
                if (charToCheck == '(') {
                    bracketKeeper.push(charToCheck);
                }
                else if (charToCheck == '*') {
                    wildCardCounter++;
                }
                else {
                    char inStack;
                    if(!bracketKeeper.isEmpty()) {
                        inStack = bracketKeeper.peek();
                        if (inStack == '(') {
                            bracketKeeper.pop();
                        }
                    }
                    else {
                        bracketKeeper.push(charToCheck);
                    }
                }
            }
            if (bracketKeeper.empty()) {
                System.out.println("yes");
            }
            else {
                while (wildCardCounter > 0) {
                    if (!bracketKeeper.empty()) {
                        wildCardCounter--;
                        bracketKeeper.pop();
                    }
                    else {
                        break;
                    }
                }
                if (bracketKeeper.empty() && wildCardCounter >= 0) {
                    System.out.println("yes");
                }
                else {
                    System.out.println("no");
                }
            }
        }
        System.exit(0);
    }
}
