package recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        ArrayList<Integer> oneToN = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            oneToN.add(i);
        }
        getPermutations(oneToN, 0);
    }

    public static void getPermutations(ArrayList<Integer> numbers, int index) {
        if (index >= numbers.size()) {
            for (int i = 0; i < numbers.size(); i++) {
                if (i == numbers.size() - 1) {
                    System.out.print(numbers.get(i));
                } else {
                    System.out.print(numbers.get(i) + " ");
                }
            }
            System.out.println();
            return;
        }
        for (int i = index; i < numbers.size(); i++) {
            int swap = numbers.get(index);
            numbers.set(index, numbers.get(i));
            numbers.set(i, swap);
            getPermutations(numbers, index + 1);
            swap = numbers.get(index);
            numbers.set(index, numbers.get(i));
            numbers.set(i, swap);
        }
    }
}

