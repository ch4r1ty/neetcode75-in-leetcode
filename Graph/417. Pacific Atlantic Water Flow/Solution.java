class Solution {
    private int m, n;
    private int[][] heights;
    private boolean[][] pacific;
    private boolean[][] atlantic;
    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;

        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];

        // 分别从 pacific, atlantic 逆流而上
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific); // 从 pacific 左往右
            dfs(i, n - 1, atlantic);    // 从 atlantic 右往左
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacific); // 从 pacific 上往下
            dfs(m - 1, j, atlantic);    // 从 atlantic 下往上
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];

            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                continue;
            }

            if (!visited[nextX][nextY] && heights[nextX][nextY] >= heights[x][y]) {
                dfs(nextX, nextY, visited);
            }
        }
    }
}`