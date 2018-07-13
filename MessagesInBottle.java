import java.util.*;
//still not working

public class MessagesInBottle {
    public static HashSet<String> possibleCodes = new HashSet<>();
    public static ArrayList<Integer> letterCodes = new ArrayList<>();
    public static ArrayList<String> letters = new ArrayList<>();
    public static boolean end = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String secretMessage = in.next();
        String cipher = in.next();
        decryptCipher(cipher);
        solve(secretMessage, 1, 0, new StringBuilder());
        System.out.println(possibleCodes.size());
        for (String code : possibleCodes) {
            System.out.println(code);
        }

    }

    public static void solve(String secretMessage, int currentLength,
                             int index, StringBuilder currentMessage) {
        if (currentLength > secretMessage.length()) {
            end = true;
            return;
        }

        for (int i = index; i < secretMessage.length(); i++) {
            StringBuilder code = new StringBuilder();
            int currentIndex = i;

            while (code.length() < currentLength) {
                code.append(secretMessage.charAt(currentIndex));
                currentIndex++;
                if (currentIndex >= secretMessage.length()) {
                    break;
                }
            }

            if (code.length() < currentLength) {
                return;
            }

            int letterCode = Integer.parseInt(String.valueOf(code));
            if (letterCodes.contains(letterCode)) {
                int letterIndex = letterCodes.indexOf(letterCode);
                currentMessage.append(letters.get(letterIndex));
                currentLength = 1;
                solve(secretMessage,currentLength,i + 1,currentMessage);
                if (i == secretMessage.length() - 1) {
                    possibleCodes.add(currentMessage.toString());
                }
                i--;
                currentLength++;
                currentMessage.deleteCharAt(currentMessage.length() - 1);
                solve(secretMessage, currentLength, i, currentMessage);
            } else {
                solve(secretMessage, currentLength + 1, index, currentMessage);
                currentMessage.deleteCharAt(currentMessage.length() - 1);
            }
            /*else {
                i--;
                currentLength++;
                currentMessage.deleteCharAt(currentMessage.length() - 1);
                solve(secretMessage, currentLength, i, currentMessage);
            }*/
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
                int code = Integer.parseInt(String.valueOf(number));
                letterCodes.add(code);
                if (currentIndex == cipher.length() - 1) {
                    i = currentIndex;
                } else {
                    i = currentIndex - 1;
                }
            }
        }
    }
}
