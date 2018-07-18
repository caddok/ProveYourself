import java.util.*;
import java.util.stream.Collectors;
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
        Hashtable<Integer, String> codeTable = new Hashtable<>();
        for (int i = 0; i < letterCodes.size(); i++) {
            codeTable.put(letterCodes.get(i), letters.get(i));
        }
        solve(secretMessage, codeTable);
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

    public static void solve(String secretMessage, Hashtable<Integer, String> codeTable) {
        int currentLength = 1;
        for (int i = 0; i < secretMessage.length(); ) {
            StringBuilder message = new StringBuilder();
            int currentIndex = i;
            while (message.length() < currentLength) {
                message.append(secretMessage.charAt(currentIndex));
                currentIndex++;
            }
            int code = Integer.parseInt(String.valueOf(message.toString()));
            if (codeTable.containsKey(code)) {

            }
        }
    }

    public static void extractPossibleMessages(String secretMessage, int index, StringBuilder currentMessage) {
        int currentLength = 1;
        for (int i = index; i < secretMessage.length(); i++) {

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

