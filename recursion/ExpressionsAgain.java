package recursion;

import java.util.ArrayList;

public class ExpressionsAgain {
    public static ArrayList<Integer> expressions = new ArrayList<>();
    public static int numberIndex;
    public static void main(String[] args) {
        String mock = "123";
        solve(mock);
        for (Integer result: expressions) {
            System.out.println(result);
        }
    }
    static void solve(String input) {
        getExpressions(input, new StringBuilder(String.valueOf(input.charAt(0))), "*", 1);
        getExpressions(input, new StringBuilder(String.valueOf(input.charAt(0))), "+", 1);
        getExpressions(input, new StringBuilder(String.valueOf(input.charAt(0))), "-", 1);
        getExpressions(input, new StringBuilder(String.valueOf(input.charAt(0))), "", 1);
    }

    public static void getExpressions(String input, StringBuilder currentExpression, String sign, int index) {
        if (index == input.length() - 1) {
            currentExpression.append(sign);
            currentExpression.append(input.charAt(index));
            expressions.add(calculateExpression(currentExpression));
            currentExpression.deleteCharAt(currentExpression.length() - 1);
            currentExpression.deleteCharAt(currentExpression.length() - 1);
            return;
        }
        currentExpression.append(sign);
        currentExpression.append(input.charAt(index));
        getExpressions(input, currentExpression, "*", index + 1);
        getExpressions(input, currentExpression, "+", index + 1);
        getExpressions(input, currentExpression, "-", index + 1);
        getExpressions(input, currentExpression, "", index + 1);
        currentExpression.deleteCharAt(currentExpression.length() - 1);
        if (currentExpression.length() > 0) {
            currentExpression.deleteCharAt(currentExpression.length() - 1);
        }
    }


    public static int calculateExpression(StringBuilder expression) {
        numberIndex = 0;
        int lastNumber = getNextNumber(expression);
        int result = lastNumber;
        while (numberIndex < expression.length()) {
            switch (expression.charAt(numberIndex)) {
                case '+':
                    numberIndex++;
                    result += getNextNumber(expression);
                    break;
                case '-':
                    numberIndex++;
                    result -= getNextNumber(expression);
                    break;
                case '*':
                    numberIndex++;
                    result *= getNextNumber(expression);
                    break;
            }
        }
        return result;

    }

    private static int getNextNumber(StringBuilder expression) {
        StringBuilder num = new StringBuilder();
        while (numberIndex < expression.length() && Character.isDigit(expression.charAt(numberIndex))) {
            num.append(expression.charAt(numberIndex));
            numberIndex++;
        }
        int number = Integer.parseInt(String.valueOf(num));
        return number;
    }
}
