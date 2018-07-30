package linearstructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BracketExpressions {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String bigExpression = in.nextLine();
        String trimmedExpression = bigExpression.substring(getStartIndex(bigExpression));

        final ArrayList<String> tokenizedExpression = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trimmedExpression.length(); i++) {
            if (trimmedExpression.charAt(i) == '(' ||
                    trimmedExpression.charAt(i) == ')') {
                if (sb.length() != 0) {
                    tokenizedExpression.add(sb.toString());
                    sb = new StringBuilder();
                }
                tokenizedExpression.add(String.valueOf(trimmedExpression.charAt(i)));
            } else {
                sb.append(trimmedExpression.charAt(i));
            }
        }

        solve(tokenizedExpression);
    }

    private static void solve(ArrayList<String> tokenizedExpression) {
        Stack<String> expressionStack = new Stack<>();
        for (int i = 0; i < tokenizedExpression.size(); i++) {
            if (tokenizedExpression.get(i).equals(")")) {
                StringBuilder newExpression = new StringBuilder(")");
                while (!expressionStack.peek().equals("(")) {
                    newExpression.insert(0, expressionStack.pop());
                }
                newExpression.insert(0, expressionStack.pop());
                System.out.println(newExpression);
                expressionStack.push(newExpression.toString());
            } else {
                expressionStack.push(tokenizedExpression.get(i));
            }
        }
    }

    private static int getStartIndex(String bigExpression) {
        int index = 0;
        while (bigExpression.charAt(index) != '(') {
            ++index;
        }
        return index;
    }
}
