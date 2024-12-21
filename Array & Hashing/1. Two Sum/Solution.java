class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // hash.put(nums[i], i);
            if (hash.containsKey(target - nums[i])) {
                res[0] = hash.get(target - nums[i]);
                res[1] = i;
                break;
            }
            hash.put(nums[i], i);
        }

        return res;
    }
}