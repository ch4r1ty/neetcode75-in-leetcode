class Solution {
    public boolean canJump(int[] nums) {
        int max = nums[0];  // max初始化，自己寫錯了
        for (int i = 0; i <= max; i++) {    // i <= max，自己寫錯了
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
