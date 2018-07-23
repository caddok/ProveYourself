package linearstructures;

import java.util.HashSet;
import java.util.Set;

public class Candies {
    public static void main(String[] args) {
        int[] mock = {1, 1, 2, 2, 3, 3};
        System.out.println(distributeCandies(mock));
    }

    public static int distributeCandies(int[] candies) {
        Set<Integer> unique = new HashSet<>();
        int maxCandy;
        for (int candy : candies) {
            unique.add(candy);
        }
        if (candies.length / 2 <= unique.size()) {
            maxCandy = candies.length / 2;
        } else {
            maxCandy = unique.size();
        }
        return maxCandy;
    }
}
