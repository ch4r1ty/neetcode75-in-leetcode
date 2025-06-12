# 239. Sliding Window Maximum

## Problem Understanding
Given an array of numbers and a window size k, we need to find the maximum number in each sliding window. For example:
- Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
- Output: [3,3,5,5,6,7]
  - Window 1: [1,3,-1] → max = 3
  - Window 2: [3,-1,-3] → max = 3
  - Window 3: [-1,-3,5] → max = 5
  - And so on...

## My Approach
I'll use a custom queue (implemented with a deque) to solve this problem because:
1. We need to keep a queue where numbers only get smaller (from front to back)
2. We need to efficiently remove elements from both ends
3. We need to keep track of the maximum value in the current window

Here's how it works:
1. We create a custom queue that keeps numbers in decreasing order
2. For each window:
   - Remove the element that's going out of the window
   - Add the new element, removing any smaller elements from the back
   - The front of the queue always contains the maximum value

## Time and Space Complexity
- Time Complexity: O(n) where n is the length of the array
  - Each element is pushed and popped at most once
- Space Complexity: O(k) where k is the window size
  - The queue will never contain more than k elements

## Code Implementation
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;  // length of result array
        int res[] = new int[len];       // store max values
        int num = 0;                    // index for result array
        MyQueue que = new MyQueue();    // initialize custom queue

        // Initialize first window
        for (int i = 0; i < k; i++) {
            que.add(nums[i]);
        }
        res[num] = que.peek();  // store first window's max
        num++;

        // Slide window
        for (int i = k; i < nums.length; i++) {
            que.poll(nums[i - k]);  // remove leftmost element
            que.add(nums[i]);       // add new element
            res[num] = que.peek();  // store current window's max
            num++;
        }

        return res;
    }
}

class MyQueue {
    Deque<Integer> que = new LinkedList<>();

    void poll(int val) {
        if (!que.isEmpty() && val == que.peek()) {
            que.poll();
        }
    }

    void add(int val) {
        while (!que.isEmpty() && val > que.peekLast()) {
            que.removeLast();
        }
        que.add(val);
    }

    int peek() {
        return que.peek();
    }
}
```

## Key Points to Remember
1. The custom queue keeps numbers in decreasing order
   - This means each number is smaller than the one before it
   - The front element is always the maximum

2. The queue operations are:
   - poll(): Removes the front element if it's the one leaving the window
   - add(): Removes all smaller elements from the back before adding the new element
   - peek(): Returns the front element (current maximum)

3. The window sliding process:
   - Remove the leftmost element of the previous window
   - Add the new element to the right
   - Get the maximum from the front of the queue

## Common Mistakes to Avoid
1. Not maintaining the decreasing order in the queue
2. Forgetting to remove elements that are no longer in the window
3. Not handling edge cases (empty array, window size equals array length)
4. Using a simple queue instead of a deque for efficient operations

Would you like me to explain any part in more detail?
