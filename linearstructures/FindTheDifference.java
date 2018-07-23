package linearstructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class FindTheDifference {
    public static void main(String[] args) {
        String mockFirst = "abcd";
        String mockSecond = "abcde";
        System.out.println(findTheDifference(mockFirst,mockSecond));
    }
    public static char findTheDifference(String s, String t) {
        Map<Character, Integer> mappedOriginal = map(s);
        Map<Character, Integer> mappedChanged = map(t);
        for (Character character : mappedChanged.keySet()) {
            if (mappedOriginal.containsKey(character)) {
                if (mappedOriginal.get(character) - mappedChanged.get(character) < 0){
                    return character;
                }
            } else {
                return character;
            }
        }
        return '(';
    }
    public static Map<Character, Integer> map(String source) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : source.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.computeIfPresent(c, (k, v) -> v + 1);
        }
        return map;
    }
}
