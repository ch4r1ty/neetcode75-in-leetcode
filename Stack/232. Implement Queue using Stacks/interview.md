# 232. Implement Queue using Stacks

## Problem Understanding
As we know, a queue follows First-In-First-Out (FIFO) order, while a stack follows Last-In-First-Out (LIFO) order. The challenge here is to use two stacks to achieve queue-like behavior.

## My Approach
I'll use two stacks to solve this problem:
1. `stackIn` - for handling push operations
2. `stackOut` - for handling pop and peek operations

The key idea is:
- When we push an element, we simply add it to `stackIn`
- When we need to pop or peek, we first check if `stackOut` is empty
  - If it's empty, we transfer all elements from `stackIn` to `stackOut` in reverse order
  - Then we can easily get the first element from `stackOut`

## Time and Space Complexity
Let me break down the complexity for each operation:
- Push: O(1) - we just add to the top of `stackIn`
- Pop: Amortized O(1) - in worst case it's O(n) when we need to transfer elements
- Peek: Amortized O(1) - similar to pop
- Empty: O(1) - just checking if both stacks are empty

Space complexity is O(n) where n is the number of elements in our queue.

## Code Implementation
Here's how I implemented it:

```java
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    public void push(int x) {
        stackIn.push(x);
    }
    
    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }
    
    public int peek() {
        int x = pop();
        stackOut.push(x);
        return x;
    }
    
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}
```

## Key Points to Remember
1. The transfer from `stackIn` to `stackOut` only happens when `stackOut` is empty
2. For peek operation, we can reuse pop by saving the value and pushing it back
3. To check if the queue is empty, we need to check both stacks

## Why This Solution Works
This solution is efficient because:
- Most operations are O(1) in practice
- We only transfer elements when necessary
- The implementation is clean and easy to understand
- It maintains the FIFO property of a queue while using LIFO stacks

## Data Structure Operations Summary

| Operation | Stack (LIFO) | Queue (FIFO) | Deque (Double-ended) |
|-----------|--------------|--------------|---------------------|
| Insert    | push()       | enqueue()    | addFirst()/addLast()|
| Remove    | pop()        | dequeue()    | removeFirst()/removeLast() |
| Remove (No Exception)| -    | poll()      | pollFirst()/pollLast() |
| Peek      | peek()       | peek()       | getFirst()/getLast() |
| Check Empty| isEmpty()   | isEmpty()    | isEmpty()           |
| Time Complexity | O(1)    | O(1)         | O(1)               |
| Space Complexity| O(n)    | O(n)         | O(n)               |

### Key Characteristics:
- **Stack**: Last In First Out (LIFO)
  - Like a stack of plates
  - Only access from one end
  - Common use: function call stack, undo operations

- **Queue**: First In First Out (FIFO)
  - Like a line of people
  - Access from both ends but in specific order
  - Common use: task scheduling, breadth-first search

- **Deque**: Double-ended Queue
  - Like a deck of cards
  - Access from both ends in any order
  - Common use: sliding window, palindrome checking

Would you like me to explain any part in more detail?
