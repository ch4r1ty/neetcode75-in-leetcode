class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // key为数组元素值,val为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 这是一个小顶堆(min heap)，用来按元素出现次数从小到大排序
        // 每个元素是一个长度为2的数组，[0]存数字本身，[1]存出现次数
        // Lambda表达式(pair1, pair2) -> pair1[1] - pair2[1]定义了比较规则:
        // 按照pair[1](出现次数)从小到大排序
        PriorityQueue<int[]> que = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);

        // 遍历哈希表，将元素加入堆中
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (que.size() < k) {
                que.add(new int[]{entry.getKey(), entry.getValue()});
            } else if (entry.getValue() > que.peek()[1]) {  // que.peek()[1]是堆顶元素的出现次数
                que.poll();
                que.add(new int[]{entry.getKey(), entry.getValue()});
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = que.poll()[0];
        }

        return ans;
    }
}