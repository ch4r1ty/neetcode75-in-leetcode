class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int slow = 0, fast = 0;
        int res = Integer.MAX_VALUE, sum = 0;
        
        while (fast < n) {  // 这里不用限制slow，因为fast到头之后，在内部那个循环里面走slow
            sum += nums[fast];
            fast++;
            while (sum >= target) {
                sum -= nums[slow];
                slow++;
                res = Math.min(res, fast - slow + 1);
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}