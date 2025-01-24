class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int preSum = 0;
        int res = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            preSum += nums[right];
            while (preSum >= target) {
                // left++;
                preSum -= nums[left];
                res = Math.min(res, right - left + 1);
                left++; // 要放在這個位置，而不是上面注釋的位置
            }
            right++;
        }

        return res != Integer.MAX_VALUE ? res : 0;
    }
}