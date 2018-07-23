package codingbat;

public class BunnyEars2 {
    public static void main(String[] args) {
        int bunnies = 3;
        System.out.println(bunnyEars2(bunnies));
    }
    private static int bunnyEars2(int bunnies) {
        if (bunnies == 0) {
            return 0;
        }
        int currentBunnyEars = bunnies % 2 > 0 ? 2 : 3;
        return bunnyEars2(bunnies - 1) + currentBunnyEars;
    }
}
