# Intersection of Two Linked Lists - Interview Script

Let me explain how I would solve this problem. The main idea is to find the intersection node of two linked lists. The challenge here is that the two lists might have different lengths, and we need to handle this carefully.

Here's my approach: First, we'll find the lengths of both lists. Then, we'll adjust the starting point of the longer list so that both pointers can reach the intersection point at the same time.

Let me break down the solution step by step:

1. First, we calculate the lengths of both lists:
```java
int lenA = 0;
int lenB = 0;
ListNode nodeA = headA;
ListNode nodeB = headB;
while (nodeA != null) {
    lenA++;
    nodeA = nodeA.next;
}
while (nodeB != null) {
    lenB++;
    nodeB = nodeB.next;
}
```

2. Then, we determine which list is longer and calculate the difference:
```java
int dif = 0;
if (lenA < lenB) {
    nodeA = headB;
    nodeB = headA;
    dif = lenB - lenA;
} else {
    nodeA = headA;
    nodeB = headB;
    dif = lenA - lenB;
}
```
This step is crucial because:
- We need to know which list is longer to adjust our starting point
- We swap the pointers if list B is longer, so that `nodeA` always points to the longer list
- The `dif` variable tells us how many steps we need to move forward in the longer list
- By doing this, we ensure that both pointers will reach the intersection point at the same time


3. We move the pointer of the longer list forward by the difference in lengths:
```java
while (nodeA != null) {
    if (dif == 0) break;
    nodeA = nodeA.next;
    dif--;
}
```

4. Finally, we move both pointers together until we find the intersection:
```java
while (nodeA != null) {
    if (nodeA == nodeB) {
        return nodeA;
    }
    nodeA = nodeA.next;
    nodeB = nodeB.next;
}
```

The key insights in this solution are:
1. We need to handle lists of different lengths
2. By moving the longer list's pointer forward by the difference in lengths, we ensure both pointers will reach the intersection point at the same time
3. The intersection is found when both pointers point to the same node

The time complexity is O(n + m) where n and m are the lengths of the two lists, as we need to traverse each list once. The space complexity is O(1) as we only use a constant amount of extra space.

Would you like me to explain any part of this in more detail? 