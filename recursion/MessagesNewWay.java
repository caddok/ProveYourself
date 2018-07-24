package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//solved with Konstantin;TLE for 1 to 3 cases.
public class MessagesNewWay {
    public static HashSet<String> possibleCodes = new HashSet<>();
    public static ArrayList<String> letterCodes = new ArrayList<>();
    public static ArrayList<String> letters = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String secretMessage = in.next();
        String cipher = in.next();
        decryptCipher(cipher);
        solve(secretMessage);
        List<String> sortedCodes = possibleCodes.stream().sorted().collect(Collectors.toList());
        if (sortedCodes.size() > 0) {
            System.out.println(sortedCodes.size());
            for (String code : sortedCodes) {
                System.out.println(code);
            }
        } else {
            System.out.println(sortedCodes.size());
        }
    }

    public static void solve(String secretMessage) {
        getPossibleMessages(secretMessage,"");
    }

    private static void getPossibleMessages(String message, String current) {
        if (message.length() == 0) {
            possibleCodes.add(current);
        }
        for (int i = 1; i <= message.length(); i++) {
            int indexOfHead = letterCodes.indexOf(message.substring(0,i));
            if (indexOfHead != -1) {
                getPossibleMessages(message.substring(i),current + letters.get(indexOfHead));
            }
        }
    }

    public static void decryptCipher(String cipher) {
        for (int i = 0; i < cipher.length(); i++) {
            if (Character.isAlphabetic(cipher.charAt(i))) {
                letters.add(String.valueOf(cipher.charAt(i)));
            } else {
                StringBuilder number = new StringBuilder();
                int currentIndex = i;
                while (Character.isDigit(cipher.charAt(currentIndex))) {
                    number.append(cipher.charAt(currentIndex));
                    if (currentIndex == cipher.length() - 1) {
                        break;
                    } else {
                        currentIndex++;
                    }
                }
                letterCodes.add(String.valueOf(number));
                if (currentIndex == cipher.length() - 1) {
                    i = currentIndex;
                } else {
                    i = currentIndex - 1;
                }
            }
        }
    }
}