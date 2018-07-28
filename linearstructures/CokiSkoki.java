package linearstructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CokiSkoki {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numberOfBuildings = Integer.parseInt(in.readLine());

        String[] buildingHeightsString = in.readLine().split(" ");

        int[] buildingHeights = new int[numberOfBuildings];

        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < numberOfBuildings; i++) {
            buildingHeights[i] = Integer.parseInt(buildingHeightsString[i]);
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
