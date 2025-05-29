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