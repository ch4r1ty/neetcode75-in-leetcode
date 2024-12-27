class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 把nums[]数组里元素和相应频率的键值对，放进hashmap里
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 把hashmap存放的键值对，用list来保存，并按频率从高到低排序
        List<int[]> list = new ArrayList<>();
        // Map.Entry是 Map 接口的一个嵌套接口，用于表示 Map 中的键值对
        // .entrySet() 用于获取 Map 中所有键值对的集合
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[] {entry.getValue(), entry.getKey()});  // 在arr里面新建一个键值对
        }
        list.sort((a, b) -> b[0] - a[0]);    // 按频率从高到低排序


        // 按照频率，从高到低放到结果集里
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i)[1];
        }

        return res;
    }
}
