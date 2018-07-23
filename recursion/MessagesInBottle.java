package recursion;

import java.util.*;
import java.util.stream.Collectors;
//still not working

public class MessagesInBottle {
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
        for (int i = 0; i < secretMessage.length(); i++) {
            StringBuilder message = new StringBuilder();
            int currentIndex = 0;
            while (message.length() < i + 1) {
                message.append(secretMessage.charAt(currentIndex));
                currentIndex++;
            }
            if (letterCodes.contains(message.toString())) {
                StringBuilder current = new StringBuilder();
                current.append(letters.get(letterCodes.indexOf(message.toString())));
                getPossibleMessages(secretMessage,currentIndex,1,current);
            }
        }
    }

    public static void getPossibleMessages(String message, int start,
                                           int currentLength, StringBuilder current) {
        for (int length = currentLength; length < message.length(); length++) {
            if (start >= message.length()) {
                possibleCodes.add(current.toString());
                return;
            }
            StringBuilder builder = new StringBuilder();
            int currentIndex = start;
            while (currentIndex < message.length() && builder.length() < length) {
                builder.append(message.charAt(currentIndex));
                currentIndex++;
            }
            if (letterCodes.contains(builder.toString())) {
                current.append(letters.get(letterCodes.indexOf(builder.toString())));
                start += length;
                getPossibleMessages(message,start,length,current);
                start -= length;
                current.deleteCharAt(current.length() - 1);
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

