class Solution {
    public int[][] generateMatrix(int n) {
        int offset = 1;
        int start = 0;
        int i = 0;
        int j = 0;
        int count = 1;
        int loop = 1;
        int[][] nums = new int[n][n];

        while (loop <= n / 2) {
            for (j = start; j < n - offset; j++) {
                nums[start][j] = count++;
            }

            for (i = start; i < n - offset; i++) {
                nums[i][j] = count++;
            }

            for (; j > start; j--) {
                nums[i][j] = count++;
            }

            for (; i > start; i--) {
                nums[i][j] = count++;
            }

            start++;
            offset++;
            loop++;
        }

        if (n % 2 == 1) {
            nums[start][start] = count;
        }
//
        return nums;
    }
}