# Design Linked List - Interview Script

Let me explain how I would solve this problem. The main idea here is to implement a linked list data structure with some basic operations. We have two choices here - we can implement either a singly linked list or a doubly linked list. I'll go with the doubly linked list implementation since it's more comprehensive and that's probably why this problem is rated as medium difficulty.

First, let's talk about what we need to implement. We need several key operations:
1. A constructor to initialize our linked list
2. A get function to retrieve a node at a specific index
3. Functions to add nodes at the head, tail, or any specific index
4. A function to delete nodes at a specific index

The tricky part here is handling all the edge cases, especially when we're dealing with the head and tail of the list. To make this easier, I'll use a technique called dummy nodes. We'll create two dummy nodes - one at the beginning and one at the end of our list. This way, we don't have to handle special cases for the head and tail separately. Every operation becomes like inserting or deleting in the middle of the list.

Let me show you how it works. When we have an empty list, it looks like this:
```
[dummy] <-> [dummy]
```

And when we add some real nodes, it becomes:
```
[dummy] <-> [1] <-> [2] <-> [3] <-> [dummy]
```

The get function is pretty straightforward. We start from the first real node (after the left dummy) and keep moving until we reach our target index. We need to be careful about out-of-bounds cases - if the index is invalid, we return -1.

For adding nodes, whether at head, tail, or any index, the process is similar. We create a new node and update the pointers of the surrounding nodes. The dummy nodes make this process much cleaner because we don't have to worry about whether we're at the beginning or end of the list.

The delete operation is also simplified by our dummy nodes. We just need to update the pointers of the nodes before and after the one we're deleting. The node we're deleting will be automatically disconnected from the list.

The time complexity for most operations is O(n) in the worst case, where n is the length of the list, because we might need to traverse the entire list to find the right position. The space complexity is O(1) for each operation since we're only using a constant amount of extra space.

Would you like me to explain any part of this in more detail?
