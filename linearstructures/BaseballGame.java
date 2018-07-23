package linearstructures;

import java.util.Stack;

public class BaseballGame {
    public static void main(String[] args) {
        String[] mock = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(mock));
    }
    public static int calPoints(String[] ops) {
        int result = 0;
        Stack<Integer> validScores = new Stack<>();
        for (String operation : ops) {
            if (isNumberic(operation)) {
                int number = Integer.parseInt(String.valueOf(operation));
                result += number;
                validScores.push(Integer.parseInt(String.valueOf(operation)));
            } else {
                switch (operation) {
                    case "D":
                        int points = validScores.peek() * 2;
                        validScores.push(points);
                        result += points;
                        break;
                    case "+":
                        int firstValidRound = validScores.pop();
                        int secondValidRound = validScores.pop();
                        points = firstValidRound + secondValidRound;
                        result += points;
                        validScores.push(secondValidRound);
                        validScores.push(firstValidRound);
                        validScores.push(points);
                        break;
                    case "C":
                        int invalidScore = validScores.pop();
                        result -= invalidScore;
                        break;
                }
            }
        }
        return result;
    }
    public static boolean isNumberic(String source) {
        return source.matches("-?\\d+");
    }
}
