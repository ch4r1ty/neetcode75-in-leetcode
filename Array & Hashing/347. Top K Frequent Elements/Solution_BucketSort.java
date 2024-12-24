class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 把nums[]数组里元素和相应频率的键值对，放进hashmap里
        Map<Integer, Integer> map = new HashMap<>();
        // 表示一个 数组，其中每个元素是一个 List<Integer> 类型的对象
        List<Integer>[] freq = new List[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            freq[entry.getValue()].add(entry.getKey()); // 核心
        }

        int[] res = new int[k];
        int index = 0;
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            for (int n : freq[i]) {
                res[index++] = n;
            }
        }

        return res;
    }
}
