class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // 1 << i：將整數 1 左移 i 位，生成一個只有第 i 位是 1 的數
            // 這裏比較的不是一位數，而是32位數進行與操作
            if (((1 << i & n) != 0)) {
                res++;
            }
        }

        return res;
    }
}
