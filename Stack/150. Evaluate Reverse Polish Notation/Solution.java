import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2;

        for (int i = 0; i < tokens.length; i++) {
            // 这里要用.equals()
            if (tokens[i].equals("+")) {
                // num1, num2的顺序，要想一下
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

        // 返回最后一个数
        return stack.pop();
    }
}