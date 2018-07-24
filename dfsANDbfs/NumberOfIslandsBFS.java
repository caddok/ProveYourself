package dfsANDbfs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfIslandsBFS {
    public static void main(String[] args) {
        char[][] mock = {new char[]{'1', '1', '1', '1', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(mock));
    }

    private static int numIslands(char[][] grid) {
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
                bfs(i, j, grid,used);
                result++;
            }
        }
        return result;
    }

    private static void bfs(int rows, int cols, char[][] grid, Set<String> used) {

    }
}
