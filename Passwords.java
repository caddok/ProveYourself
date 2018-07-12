import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Passwords {
    private static int keysPressed;
    private static int finalCombination;
    private static int counter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        keysPressed = Integer.parseInt(in.readLine());
        char[] directions = in.readLine().toCharArray();
        finalCombination = Integer.parseInt(in.readLine());
        returnFinalCombination(new StringBuilder(), directions, 0);
    }

    private static void returnFinalCombination(StringBuilder current, char[] directions, int directionIndex) {
        if (current.length() < 1) {
            for (int i = 0; i <= 9; i++) {
                returnFinalCombination(current.append(i), directions, directionIndex);
                current.deleteCharAt(0);
            }
        }

        if (current.length() == keysPressed) {
            ++counter;
            if (counter == finalCombination) {
                System.out.println(current);
                System.exit(0);
            } else {
                return;
            }
        }

        char lastChar = current.charAt(current.length() - 1);
        int start;

        if (lastChar == '0') {
            start = 10;
        } else {
            start = lastChar - '0';
        }

        if (directionIndex < directions.length) {
            switch (directions[directionIndex]) {

                case '>':

                    if (lastChar == '0') {
                        return;
                    }

                    for (int i = start; i <= 9; i++) {

                        if (i == start) {
                            current.append(0);
                            returnFinalCombination(current, directions, directionIndex + 1);
                            current.deleteCharAt(current.length() - 1);
                        } else {
                            current.append(i);
                            returnFinalCombination(current, directions, directionIndex + 1);
                            current.deleteCharAt(current.length() - 1);
                        }
                    }

                    break;

                case '<':

                    for (int i = 1; i < start; i++) {
                        current.append(i);
                        returnFinalCombination(current, directions, directionIndex + 1);
                        current.deleteCharAt(current.length() - 1);
                    }

                    break;

                case '=':

                    current.append(lastChar);
                    returnFinalCombination(current, directions, directionIndex + 1);
                    current.deleteCharAt(current.length() - 1);

                    break;
                default:
                    break;
            }

        }

    }

}
