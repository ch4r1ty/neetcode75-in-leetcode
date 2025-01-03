class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        boolean visited[][] = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    dfs(grid, visited, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] == true || grid[x][y] == '0') return;

        visited[x][y] = true;
        dfs(grid, visited, x + 1, y);
        dfs(grid, visited, x - 1, y);
        dfs(grid, visited, x, y + 1);
        dfs(grid, visited, x, y - 1);
    }
}