// 这题关键的两点：
//     1. preSum[i] 表示从 nums[0] 到 nums[i-1] 的和，注意下标！
//         所以要 n + 1 的长度
//     2. sumRange给的输入没有nums，所以要在class里面初始化preSum，
//         然后在NumArray里面初始化preSum

class NumArray {

    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        int sum = 0;
        for (int i = 1; i < preSum.length; i++) {
            sum += nums[i - 1];
            preSum[i] += sum;
        }

    }

    public int sumRange(int left, int right) {

        return preSum[right + 1] - preSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
