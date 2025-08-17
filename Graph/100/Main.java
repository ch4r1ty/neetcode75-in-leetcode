import java.util.*;

public class Main {
    
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    static int ans = 0;
    static int cur = 0;

    public static void main(String[] args) {
        int[][] grid = {{1,1,0,0,0},
                        {1,1,0,0,0},
                        {0,0,1,1,1},
                        {0,0,0,1,1}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    cur = 0;  // 使用全局变量，不要声明局部变量
                    dfs(i, j, grid, visited);
                    if (cur > ans) ans = cur;
                }
            }
        }

        System.out.println(ans);
        
    }

    private static void dfs(int x, int y, int[][] grid, boolean[][] visited) {
        if (visited[x][y] || grid[x][y] == 0) return;
        visited[x][y] = true;
        cur++;
        // if (cur > ans) ans = cur; 这一步放在main里面

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;
            
            dfs(nextX, nextY, grid, visited);

        }
    }
}