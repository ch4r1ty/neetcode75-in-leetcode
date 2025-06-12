import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // 底下这里要用Character，new 后面要大写S
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (s.charAt(i) == '{') {
                stack.push('}');
                // 下面對應了兩種錯誤情況：
                // 1. 符號不對：( 變成了[
                // 2. 少了左括號，也就是stack裏面沒有元素了
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                return false;
            } else {
                stack.pop();
            }
        }

        // 这里要加一个判断，不然过不了 s = '[' 的情况
        // 其实这里也就对应了三种里面的一种错误
        // string s 遍歷完了，stack不爲空：對應 s = '[' 的情況（這是一種錯誤情況）
        if (!stack.isEmpty()) return false;
        return true;
    }
}