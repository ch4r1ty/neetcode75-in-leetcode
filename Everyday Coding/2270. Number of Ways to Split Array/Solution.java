class Solution {
    public int waysToSplitArray(int[] nums) {
        if (nums.length == 0) return 0;

        int leng = nums.length;
        long[] preSum = new long[leng]; // 如果是int，当输入数组包含大量负值，會溢出
        preSum[0] = nums[0];
        int res = 0;

        for (int i = 1; i < leng; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        for (int i = 0; i < leng - 1; i++) {
            if (preSum[i] >= preSum[leng - 1] - preSum[i]) {
                res++;
                // System.out.println(preSum[i]);
            }
        }

        return res;
    }
}