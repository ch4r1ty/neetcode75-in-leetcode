class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (!set.contains(n)) {
            // 这个过程是不对的：n = 19为例：
            // set不包含19 -> n = 82, set加入82; 然后下一个循环判断set有没有82？有的！

            // n = getNext(n);
            // set.add(n);

            // 所以顺序应该是：
            set.add(n); // 我之前想：n怎么能直接放进呢？还没有进行getNext()操作，但是请仔细想一下，如果再次出现这个n，说明也是循环起来了
            n = getNext(n);

            
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) { // 这个条件是很关键的，没有想到
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}