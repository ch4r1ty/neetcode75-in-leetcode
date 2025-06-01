# Remove Nth Node From End of List - Interview Script

Let me explain how I would solve this problem. The main idea is to find the nth node from the end of the list and remove it. The challenge here is that we need to do this in one pass, which means we can't first count the length of the list.

Here's my approach: we'll use two pointers, let's call them fast and slow. The key insight is that if we want to find the nth node from the end, we can have our fast pointer n steps ahead of our slow pointer. When the fast pointer reaches the end, the slow pointer will be exactly at the node we want to remove.

Let me show you with an example. Say we have a list [1,2,3,4,5] and n = 2. We want to remove 4, which is the 2nd node from the end.

First, we create a dummy node, I like the technique of dummy node because I don't need to handle edge cases, like add or delete the node from the first node from the list. 
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
```

Then we set up our two pointers:
```java
ListNode fast = dummy;
ListNode slow = dummy;
```

Now, we move the fast pointer n steps ahead:
```java
for (int i = 0; i < n; i++) {
    fast = fast.next;
}
```

After this, our pointers will be like this:
```
dummy -> 1 -> 2 -> 3 -> 4 -> 5
   ^
   slow
         ^
         fast
```

Then we move both pointers until fast reaches the end:
```java
while (fast.next != null) {
    fast = fast.next;
    slow = slow.next;
}
```

At this point, slow will be at the node before the one we want to remove. We can simply remove the next node by updating the pointer:
```java
slow.next = slow.next.next;
```

The time complexity is O(n) since we only need one pass through the list, and the space complexity is O(1) because we only use a constant amount of extra space.

Would you like me to explain any part of this in more detail? 