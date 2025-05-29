class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int value = 1;
        int startX = 0, startY = 0;
        int i = 0, j = 0;
        int offset = 1;  // to count the loop
        int loop = 0;

        // 左閉右開
        while (loop < n / 2) {
            for (j = startY; j < n - offset; j++) {
                matrix[startX][j] = value++;
            }
            for (i = startX; i < n - offset; i++) {
                matrix[i][j] = value++;
            }
            for (; j > startY; j--) {
                matrix[i][j] = value++;
                
            }
            for (; i > startX; i--) {
                matrix[i][j] = value++;
            }
            startX++;
            startY++;
            loop++;
            offset++;

            
        }
        if (n % 2 == 1) {
            matrix[n/2][n/2] = value;
        }

        return matrix;
    }
}