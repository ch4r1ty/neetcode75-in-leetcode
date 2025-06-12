import java.util.Stack;

class MyQueue {

    // **变量声明**
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        // **初始化**
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    public void push(int x) {
        stackIn.push(x);
    }
    
    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                int temp = stackIn.pop();
                stackOut.push(temp);
            }
        }

        return stackOut.pop();
    }
    
    // 移出去再放回來可還行
    public int peek() {
        int x = pop();
        stackOut.push(x);
        return x;
    }
    
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */