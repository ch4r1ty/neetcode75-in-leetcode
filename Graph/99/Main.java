import java.util.*;

public class Main { 
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    public static void main(String arg[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        boolean[][] visited = new boolean[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(visited, i, j, grid);
                    visited[i][j] = true;
                    ans++;
                }
            }
        }

        System.out.print(ans);
    }

    static void dfs(boolean[][] visited, int x, int y, int[][] grid) {
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX > grid.length - 1 || nextX < 0 || nextY > grid[0].length - 1 || nextY < 0) {
                continue;
            }
            dfs(visited, nextX, nextY, grid);
        }
    }
}