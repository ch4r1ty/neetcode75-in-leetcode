import java.util.Stack;

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
            // 从前插入字符，保证顺序
            res.insert(0, stack.pop());
        }

        return res.toString();
    }
}