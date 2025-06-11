class MyStack {

    Queue<Integer> que;

    public MyStack() {
        que = new LinkedList<>();
    }
    
    public void push(int x) {
        que.offer(x);
    }
    
    public int pop() {
        int size = que.size();
        size--;
        int x = size;
        while (x > 0) {
            int temp = que.poll();
            que.offer(temp);
            x--;
        }

        return que.poll();
    }
    
    public int top() {
        int x = pop();
        que.offer(x);
        return x;
    }
    
    public boolean empty() {
        return que.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */