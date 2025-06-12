# 20. Valid Parentheses

## Problem Understanding
Given a string containing just the characters:
- Left round bracket '(' and right round bracket ')'
- Left square bracket '[' and right square bracket ']'
- Left curly bracket '{' and right curly bracket '}'

We need to determine if the input string is valid. A string is valid if:
1. Open brackets must be closed by the same type of brackets
2. Open brackets must be closed in the correct order
3. Every close bracket has a corresponding open bracket of the same type

## My Approach
I'll use a stack to solve this problem because:
1. We need to keep track of the most recent opening bracket
2. The last opening bracket should match with the next closing bracket
3. Stack's LIFO property perfectly matches this requirement

Here's how it works:
- When we see an opening bracket, we push its corresponding closing bracket onto the stack
- When we see a closing bracket, we check if it matches the top of the stack
- If it matches, we pop from the stack
- If it doesn't match or the stack is empty, the string is invalid

## Time and Space Complexity
- Time Complexity: O(n) where n is the length of the string
  - We process each character exactly once
- Space Complexity: O(n) in worst case
  - When all characters are opening brackets

## Code Implementation
```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (s.charAt(i) == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
```

## Key Points to Remember
1. We push the corresponding closing bracket instead of the opening bracket
   - This makes the matching check simpler
   - We can directly compare with the current character

2. There are three cases where the string is invalid:
   - When we see a closing bracket but the stack is empty
   - When we see a closing bracket that doesn't match the top of the stack
   - When we finish processing the string but the stack is not empty

3. The final check `return stack.isEmpty()` is crucial
   - It handles cases like "[" where we have unclosed brackets

## Common Mistakes to Avoid
1. Not checking if the stack is empty before peeking
2. Forgetting to check if the stack is empty at the end
3. Pushing opening brackets instead of closing brackets
4. Not handling all three types of brackets

Would you like me to explain any part in more detail? 