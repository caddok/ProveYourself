package codingbat1;

public class BunnyEars {
    public static void main(String[] args) {
        int bunnies = 5;
        System.out.println(bunnyEars(bunnies));
    }
    public static int bunnyEars(int bunnies) {
        if (bunnies == 0) {
            return 0;
        }
        return 2 + bunnyEars(bunnies - 1);
    }
}
