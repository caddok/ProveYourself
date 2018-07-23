package dfsANDbfs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfIslandsDFS {
    public static void main(String[] args) {
        char[][] mock = {new char[]{'1', '1', '1', '1', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(mock));
    }

    public static int numIslands(char[][] grid) {
        int result = 0;
        Set<String> used = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                if (used.contains(i + ";" + j)){
                    continue;
                }
                used.add(i + ";" + j);
                dfs(i, j, grid,used);
                ++result;
            }
        }
        return result;
    }

    private static void dfs(int row, int col, char[][] grid, Set<String> used) {
        int[] dRow = {-1, 1, 0, 0};
        int[] dColumn = {0, 0, -1, 1};
        for (int direction = 0; direction < dRow.length; direction++) {
            int newRow = row + dRow[direction];
            int newCol = col + dColumn[direction];
            if (newRow < 0 || newRow >= grid.length ||
                    newCol < 0 || newCol >= grid[row].length) {
                continue;
            }
            if (grid[newRow][newCol] == '0') {
                continue;
            }
            if (used.contains(newRow + ";" + newCol)) {
                continue;
            }
            used.add(newRow + ";" + newCol);
            dfs(newRow,newCol,grid,used);
        }
    }
}
