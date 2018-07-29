package dfsANDbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Portals {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int startRow = in.nextInt();
        int startCol = in.nextInt();

        int rows = in.nextInt();
        int cols = in.nextInt();

        in.nextLine();
        List<List<String>> matrix = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            List<String> row = Arrays.asList(in.nextLine().split(" "));
            matrix.add(row);
        }
        int best = dfs(startRow, startCol, matrix, 0);
        System.out.println(best);
    }

    private static int dfs(int row, int col, List<List<String>> matrix, int currentPath) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        int best = currentPath;

        int[] dRows = {0, -1, 0, +1};
        int[] dCols = {-1, 0, +1, 0};

        int directionCount = dRows.length;

        int power = Integer.parseInt(matrix.get(row).get(col));
        matrix.get(row).set(col, "@");
        for (int direction = 0; direction < directionCount; direction++) {

            int nextRow = row + power * dRows[direction];
            int nextCol = col + power * dCols[direction];

            if (!inRange(nextRow, rows) || !inRange(nextCol, cols)) {
                continue;
            }

            if (matrix.get(nextRow).get(nextCol).equals("#")) {
                continue;
            }

            if (matrix.get(nextRow).get(nextCol).equals("@")) {
                best = Math.max(best, currentPath + power);
                continue;
            }

            best = Math.max(best,
                    dfs(nextRow, nextCol, matrix, currentPath + power));
        }
        matrix.get(row).set(col, power + "");
        return best;
    }

    private static boolean inRange(int value, int maxValue) {
        return 0 <= value && value < maxValue;
    }
}
