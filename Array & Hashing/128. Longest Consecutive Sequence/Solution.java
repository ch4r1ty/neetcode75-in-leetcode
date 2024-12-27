class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));

        int pre[] = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = nums[i] - nums[i - 1];
        }
        // System.out.println(Arrays.toString(pre));

        int res = 1;
        int temp = 1;
        for (int i = 0; i < nums.length; i++) {
            if (pre[i] == 0 || pre[i] == 1) {
                temp += pre[i];
                res = Math.max(res, temp);
            } else {
                temp = 1;
            }
        }

        return res;
    }
}
