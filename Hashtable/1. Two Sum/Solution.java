class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 哈希表只能通过索引去找值，map.containsKey()，所以要想明白哈希表里面要快速找的字段是什么
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
    
        for (int i = 0; i < nums.length; i++) {
            
            int temp = target - nums[i];
            // map.put(nums[i], i);    // 这一步要放在判断下面，不然的话，会出现答案中两个元素相同
            if (map.containsKey(temp)) {
                res[0] = map.get(temp);
                res[1] = i;
                // break;
            }
            map.put(nums[i], i);    // 这一步要放在判断下面，不然的话，会出现答案中两个元素相同
        }

        return res;
    }
}