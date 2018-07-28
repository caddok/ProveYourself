import java.util.*;

public class Subsequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String searchedWord = in.nextLine();
        String toSearchIn = in.nextLine();

        int searchIndex = 0;
        int searchWordIndex = 0;
        for (int i = 0; i < searchedWord.length(); i++) {
            searchWordIndex = i;
            while (searchIndex < toSearchIn.length()
                    && !(searchedWord.charAt(i) ==
                    toSearchIn.charAt(searchIndex))) {
                searchIndex++;
            }
            if (searchIndex >= toSearchIn.length() - 1) {
                break;
            }
        }
        if (searchWordIndex >= searchedWord.length() - 1){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }
}
