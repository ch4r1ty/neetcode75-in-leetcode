# Linked List Cycle II - Interview Script

Let me explain how I would solve this problem. The main idea is to detect if there's a cycle in the linked list and find the node where the cycle begins.

First, let me explain my overall approach:
1. We'll use two pointers, a slow pointer and a fast pointer
2. The slow pointer moves one step at a time, while the fast pointer moves two steps at a time
3. Because the relative speed between fast pointer and slow pointer is one step, if there's a cycle, these pointers will eventually meet inside the cycle
4. Once they meet, we can find the cycle's starting point by moving one pointer back to the head and moving both pointers one step at a time

This approach is based on a mathematical property: when the pointers meet in the cycle, the distance from the meeting point to the cycle start is equal to the distance from the head to the cycle start.

Now, let me show you the implementation step by step:

Step 1: Initialize the pointers
```java
ListNode slow = head;
ListNode fast = head;
```

Step 2: Move the pointers through the list
```java
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
```
Here, we check `fast.next != null` because we need to move fast two steps at a time.

Step 3: Check if we found a cycle
```java
    if (fast == slow) {
        // We found a cycle! Now let's find where it starts
```
When the pointers meet, we know we're inside the cycle.

Step 4: Find the cycle's starting point
```java
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```
This is the clever part. We reset slow to head and move both pointers one step at a time. They will meet at the cycle's starting point.

Finally, if we don't find a cycle:
```java
return null;
```

Let me explain the time complexity in detail:
1. First phase (finding the meeting point):
   - If there's no cycle: O(n) as we'll reach the end of the list
   - If there's a cycle: O(n) as the fast pointer will catch up to the slow pointer within one cycle
2. Second phase (finding the cycle start):
   - O(n) in worst case as we might need to traverse the entire list again
3. Therefore, the total time complexity is O(n)

The space complexity is O(1) since we only use two pointers regardless of the input size.

Would you like me to explain any part of this in more detail?
