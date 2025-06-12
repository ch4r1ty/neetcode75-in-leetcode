# 150. Evaluate Reverse Polish Notation

## Problem Understanding
Reverse Polish Notation (RPN), also known as postfix notation, is a mathematical notation where operators follow their operands. For example:
- "2 1 + 3 *" means (2 + 1) * 3 = 9
- "4 13 5 / +" means 4 + (13 / 5) = 6

The rules are:
1. When we see a number, we push it onto the stack
2. When we see an operator, we pop two numbers from the stack, perform the operation, and push the result back

The four basic arithmetic operators are:
- Plus sign (+) for addition
- Minus sign (-) for subtraction
- Asterisk (*) for multiplication
- Forward slash (/) for division

## My Approach
I'll use a stack to solve this problem because:
1. We need to keep track of the most recent operands
2. The last two operands should be used for the next operation
3. Stack's LIFO property perfectly matches this requirement

Here's how it works:
- For each token in the input array:
  - If it's a number, push it onto the stack
  - If it's an operator:
    - Pop the second number (num2)
    - Pop the first number (num1)
    - Perform the operation: num1 operator num2
    - Push the result back onto the stack
- The final result will be the only number left in the stack

## Time and Space Complexity
- Time Complexity: O(n) where n is the number of tokens
  - We process each token exactly once
- Space Complexity: O(n) in worst case
  - When all tokens are numbers

## Code Implementation
```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 + num2);
            } else if (tokens[i].equals("-")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 - num2);
            } else if (tokens[i].equals("*")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 * num2);
            } else if (tokens[i].equals("/")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 / num2);
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }

        return stack.pop();
    }
}
```

## Key Points to Remember
1. The order of operands is important
   - First pop is the second operand (num2)
   - Second pop is the first operand (num1)
   - This ensures correct order for subtraction and division

2. String comparison
   - Use equals() instead of == for string comparison
   - This is crucial for comparing operators

3. Number conversion
   - Use Integer.valueOf() to convert string tokens to integers
   - This handles both positive and negative numbers

## Common Mistakes to Avoid
1. Wrong order of operands in subtraction and division
2. Using == instead of equals() for string comparison
3. Not handling negative numbers correctly
4. Not considering integer division behavior

Would you like me to explain any part in more detail?
