class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;  // 结果数组长度
        int res[] = new int[len];       // 存储每个窗口的最大值
        int num = 0;                    // 结果数组的索引
        MyQueue que = new MyQueue();    // 初始化自定义队列

        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            que.add(nums[i]);
        }
        res[num] = que.peek();  // 存储第一个窗口的最大值
        num++;

        // 滑动窗口
        for (int i = k; i < nums.length; i++) {
            que.poll(nums[i - k]);  // 移除窗口最左侧的元素
            que.add(nums[i]);       // 加入新元素
            res[num] = que.peek();  // 存储当前窗口的最大值
            num++;
        }

        return res;
    }
}

// 自定义队列，用于管理窗口的最大值
class MyQueue {
    Deque<Integer> que = new LinkedList<>();

    // 弹出元素，只有当弹出的元素是当前最大值时才移除
    // 这是因为在add方法里面，对que里面的最左边处理过了
    void poll(int val) {
        if (!que.isEmpty() && val == que.peek()) {
            que.poll();
        }
    }

    // 添加元素，并移除所有比新元素小的尾部元素
    void add(int val) {
        while (!que.isEmpty() && val > que.peekLast()) {
            que.removeLast();
        }
        que.add(val);
    }

    // 获取队列的头部元素，即当前窗口的最大值
    int peek() {
        return que.peek();
    }
}
