import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class GreaterMoney {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] bag1 = in.nextLine().split(",");
        String[] bag2 = in.nextLine().split(",");

        int[] coinsInBag1 = new int[bag1.length];
        int[] coinsInBag2 = new int[bag2.length];

        for (int i = 0; i < bag1.length; i++) {
            coinsInBag1[i] = Integer.parseInt(bag1[i]);
        }
        for (int i = 0; i < bag2.length; i++) {
            coinsInBag2[i] = Integer.parseInt(bag2[i]);
        }

        Map<Integer, Integer> moreCoins = new HashMap<>();
        Stack<Integer> coinStack = new Stack<>();

        for (int coins : coinsInBag2) {
            while (!coinStack.empty() && coinStack.peek() < coins) {
                moreCoins.put(coinStack.pop(),coins);
            }
            coinStack.push(coins);
        }

        for (int i = 0; i < coinsInBag1.length; i++) {
            if (moreCoins.containsKey(coinsInBag1[i])) {
                coinsInBag1[i] = moreCoins.get(coinsInBag1[i]);
            } else {
                coinsInBag1[i] = -1;
            }
        }

        for (int i = 0; i < coinsInBag1.length; i++) {
            if (i == coinsInBag1.length - 1) {
                System.out.print(coinsInBag1[i]);
            } else {
                System.out.print(coinsInBag1[i] + ",");
            }
        }

    }
}
