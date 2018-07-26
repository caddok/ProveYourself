package recursion;

import java.util.Scanner;

public class Numerology {
    public static int[] result;
    static int evalExpression(int a, int b) {
        return ((a + b) * (a ^ b)) % 10;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[] digits = new int[8];
        result = new int[10];
        int index = 7;
        while (number > 0) {
            digits[index] = number % 10;
            number /= 10;
            index--;
        }
        getDigits(digits);
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                System.out.print(result[i]);
            } else {
                System.out.print(result[i] + " ");
            }
        }
    }

    private static void getDigits(int[] digits) {
        if (digits.length == 1) {
            ++result[digits[0]];
            return;
        }
        for (int i = 0; i < digits.length - 1; i++) {
            int[] newDigits = new int[digits.length - 1];
            for (int j = 0; j < i; j++) {
                newDigits[j] = digits[j];
            }
            newDigits[i] = evalExpression(digits[i], digits[i + 1]);
            for (int j = i + 2; j < digits.length; j++) {
                newDigits[j - 1] = digits[j];
            }
            getDigits(newDigits);
        }
    }
}
