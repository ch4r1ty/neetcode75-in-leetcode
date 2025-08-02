import java.util.*;

public class Solution {

    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    static int ans = 0;

    public static void main(String[] args) {
        int grid[][] = {{1,1,0,0,0},
                        {1,0,0,0,0},
                        {0,1,1,1,0},
                        {0,0,1,0,1},
                        {0,0,0,0,1}};

        boolean visited[][] = new boolean[grid.length][grid[0].length];

        // up
        for (int i = 0; i < grid.length; i++) {
            dfs(i, 0, grid, visited);
        }
        // bottom
        for (int i = 0; i < grid.length; i++) {
            dfs(i, grid.length - 1, grid, visited);
        }
        // left
        for (int j = 0; j < grid[0].length; j++) {
            dfs (0, j, grid, visited);
        }
        // right
        for (int j = 0; j < grid[0].length; j++) {
            dfs (grid[0].length - 1, j, grid, visited);
        }

        ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == 0) continue;
                dfs(i, j, grid, visited);
            }
        }
        System.out.print(ans);

    }

    static void dfs(int x, int y, int[][] grid, boolean[][] visited) {
        if (visited[x][y] || grid[x][y] == 0) return;
        visited[x][y] = true;
        ans++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length) continue;
            dfs(nextX, nextY, grid, visited);
        }
    }
}