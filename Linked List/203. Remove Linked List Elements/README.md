# 203. Remove Linked List Elements

## Problem Description
Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that has `Node.val == val`, and return the new head.

## Solution Approach
The solution uses a dummy node approach with the following key points:

1. Create a dummy node that points to the head to handle edge cases (like removing the first node)
2. Use a pointer `node` to traverse the list
3. For each node:
   - If the next node's value equals the target value, skip it by updating the pointer
   - Otherwise, move to the next node
4. Return dummy.next as the new head

### Time Complexity: O(n)
- We traverse the linked list once, where n is the number of nodes

### Space Complexity: O(1)
- We only use a constant amount of extra space regardless of input size

## Key Points
- The dummy node technique simplifies edge cases
- Moving the pointer (node = node.next) only in the else block is crucial to handle consecutive target values
- The while loop condition checks both node and node.next to prevent null pointer exceptions 