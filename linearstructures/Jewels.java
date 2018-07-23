package linearstructures;

import java.util.HashSet;
import java.util.Set;

public class Jewels {
    public static void main(String[] args) {
        String mockJewels = "aA";
        String mockStones = "aAAbbbb";
        System.out.println(numJewelsInStones(mockJewels,mockStones));
    }
    public static int numJewelsInStones(String J, String S) {
        Set<Character> jewels = new HashSet<>();
        for (char jewel : J.toCharArray()){
            jewels.add(jewel);
        }
        int result = 0;
        for (Character jewel:jewels) {
            for (char stone:S.toCharArray()){
                if (stone == jewel) {
                    result++;
                }
            }
        }
        return result;
    }
}
