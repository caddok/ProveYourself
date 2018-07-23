package recursion;

import java.util.*;

//not working for one test
public class GirlsGoneWild {
    static HashSet<String> uniqueOutfits = new HashSet<>();
    static ArrayList<String> skirts = new ArrayList<>();
    static ArrayList<Integer> shirts = new ArrayList<>();
    static ArrayList<Boolean> used = new ArrayList<>();
    static int girlsToDress = 0;
    static int fullSet = 0;
    static ArrayList<Boolean> shirtsUsed = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfShirts = in.nextInt();
        in.nextLine();
        String[] lettersOfSkirts = in.nextLine().split("");
        int numberOfGrills = in.nextInt();
        for (int i = 0; i < numberOfShirts; i++) {
            shirts.add(i);
        }
        Arrays.sort(lettersOfSkirts, Comparator.naturalOrder());
        for (int i = 0; i < lettersOfSkirts.length; i++) {
            for (int j = 0; j < lettersOfSkirts[i].length(); j++) {
                skirts.add(String.valueOf(lettersOfSkirts[i].charAt(j)));
            }
        }
        girlsToDress = numberOfGrills;
        for (int i = 0; i < skirts.size(); i++) {
            used.add(false);
        }
        for (int i = 0; i < shirts.size(); i++) {
            shirtsUsed.add(false);
        }
        fullSet = (girlsToDress * 2) + girlsToDress - 1;
        findOutfits(shirts, skirts, new StringBuilder(), 0);
        SortedSet<String> outfits = new TreeSet<>(uniqueOutfits);
        System.out.println(outfits.size());
        for (String out : outfits) {
            System.out.println(out);
        }
    }

    public static void findOutfits(ArrayList<Integer> shirts, ArrayList<String> skirts, StringBuilder set, int shirtIndex) {
        if (set.length() == fullSet) {
            uniqueOutfits.add(set.toString());
            return;
        }

        for (int i = shirtIndex; i < shirts.size(); i++) {
            int test = 0;
            if (set.length() > 0 && set.length() % 3 == 0) {
                test = Integer.parseInt(String.valueOf(set.charAt(set.length() - 3)));
            }

            if (shirtsUsed.get(i) == true || i < test) {
                continue;
            }
            set.append(shirts.get(i));
            shirtsUsed.set(i, true);
            for (int j = 0; j < skirts.size(); j++) {
                if (used.get(j)) {
                    continue;
                }
                used.set(j, true);
                set.append(skirts.get(j));
                if (set.length() < fullSet - 2) {
                    set.append('-');
                }
                findOutfits(shirts, skirts, set, shirtIndex);
                if (set.length() > 0) {
                    set.deleteCharAt(set.length() - 1);
                }
                used.set(j, false);
            }
            if (set.length() > 0) {
                set.deleteCharAt(set.length() - 1);
            }
            shirtsUsed.set(i, false);
        }
        if (set.length() > 0) {
            set.deleteCharAt(set.length() - 1);
            String lastUsedSkirtIndex = String.valueOf(set.charAt(set.length() - 1));
            used.set(skirts.indexOf(lastUsedSkirtIndex), false);
        }
    }
}
