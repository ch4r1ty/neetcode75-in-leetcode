# Reverse Linked List - Interview Script

Let me explain how I would solve this problem. The main idea is pretty straightforward - we need to reverse all the next pointers of each node to point to their previous nodes.

Here's my approach: we'll use two pointers, let's call them prev and cur. At the start, prev points to null, and cur points to the first node. Let me show you with an example: [1,2,3,4,5]. 

In the original list, we have 1 pointing to 2, 2 pointing to 3, and so on until 5 points to null. What we want is the opposite: 5 pointing to 4, 4 pointing to 3, all the way until 1 points to null.

That's why we initialize prev as null - because at the end, the first node (which was 1) should point to null. Then we'll traverse through the list and reverse each pointer.

Let me show you the code. First, we set up our two pointers:
```java
ListNode prev = null;
ListNode cur = head;
```

Now, here's the tricky part. When we're in the while loop, we need to be careful because once we reverse a pointer, we'll lose track of the next node. So we need to save it first. We use a temporary variable, let's call it temp.

Here's how it works in each iteration:
1. We save the next node in temp
2. We reverse the current node's pointer
3. We move prev to cur's position
4. We move cur to the saved next node

The code looks like this:
```java
while (cur != null) {
    ListNode temp = cur.next;  // save the next node
    cur.next = prev;          // reverse the pointer
    prev = cur;               // move prev forward
    cur = temp;               // move cur forward
}
```

Let me explain the termination condition and return value. We use `cur != null` because we need to process every node in the list. When cur becomes null, it means we've reached the end of the original list. At this point, prev will be at the last node of the original list, which becomes our new first node. That's why we return prev - because cur will be null, but prev will be at what was originally the last node, which is now our new head.

The time complexity is O(n) since we visit each node once, and the space complexity is O(1) because we only use a constant amount of extra space.

Would you like me to explain any part of this in more detail? 