public class KthSymbol {
    public static void main(String[] args) {
        int mockNumberOfRows = 4;
        int positions = 5;
        int kSymbol = kthGrammar(mockNumberOfRows,positions);
        System.out.println(kSymbol);
    }
    public static int kthGrammar(int N, int K) {
        int finalString = getFinalString(K);
        return finalString;
    }

    public static int getFinalString(int searchedIndex) {
        if (searchedIndex == 1) {
            return 0;
        }
        int size = 2;
        while (size < searchedIndex) {
            size *= 2;
        }
        int next = getFinalString(searchedIndex - size / 2);
        return next == 0 ? 1 : 0;
    }

}