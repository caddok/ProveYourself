package linearstructures;

import java.util.*;
//TODO:Comment in home for time limit
public class CokiSkoki {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfBuildings = in.nextInt();
        int[] buildingHeights = new int[numberOfBuildings];
        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < numberOfBuildings; i++) {
            buildingHeights[i] = in.nextInt();
            results.add(i,0);
        }
        results.set(buildingHeights.length - 1,0);
        Stack<Integer> heights = new Stack<>();
        heights.push(buildingHeights[numberOfBuildings - 1]);
        for (int i = numberOfBuildings - 2; i > - 1; i--) {
            int currentHeight = buildingHeights[i];
            int jumps = 0;

            while (!heights.empty() && currentHeight >= heights.peek()) {
                heights.pop();
            }
            jumps = heights.size();
            heights.push(currentHeight);
            results.set(i,jumps);
        }
        System.out.println(Collections.max(results));
        for (int i = 0; i < results.size(); i++) {
            if (i == results.size() - 1) {
                System.out.print(results.get(i));
            } else {
                System.out.print(results.get(i) + " ");
            }
        }
    }
}
