# 1047. Remove All Adjacent Duplicates In String

## Problem Understanding
Given a string, we need to remove all adjacent duplicate characters. For example:
- Input: "abbaca" → Output: "ca"
  - First, we remove "bb" → "aaca"
  - Then, we remove "aa" → "ca"
- Input: "azxxzy" → Output: "ay"
  - First, we remove "xx" → "azzy"
  - Then, we remove "zz" → "ay"

## My Approach
I'll use a stack to solve this problem because:
1. We need to keep track of the most recent character
2. When we see a duplicate, we need to remove both characters
3. Stack's LIFO property helps us easily access and remove the last character

Here's how it works:
- For each character in the string:
  - If the stack is empty or the current character is different from the top of the stack, push it
  - If the current character is the same as the top of the stack, pop the top character
- Finally, convert the stack back to a string in the correct order

## Time and Space Complexity
- Time Complexity: O(n) where n is the length of the string
  - We process each character exactly once
  - The final string building is also O(n)
- Space Complexity: O(n) in worst case
  - When there are no adjacent duplicates

## Code Implementation
```java
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch != stack.peek()) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }

        return res.toString();
    }
}
```

## Key Points to Remember
1. The stack helps us maintain the correct order of characters
   - When we see a duplicate, we remove both characters
   - When we see a different character, we keep it

2. The final string building is important
   - We need to insert characters at the beginning of the StringBuilder
   - This ensures the correct order of characters in the result

3. The solution handles all cases:
   - Empty string
   - String with no duplicates
   - String with multiple adjacent duplicates
   - String with non-adjacent duplicates

## Common Mistakes to Avoid
1. Not handling the case when the stack is empty
2. Forgetting to reverse the order when building the final string
3. Using string concatenation instead of StringBuilder
4. Not considering the case of multiple adjacent duplicates

Would you like me to explain any part in more detail?
