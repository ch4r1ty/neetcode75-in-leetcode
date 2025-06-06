class Solution {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();  // 将字符串转为字符数组
        int n = arr.length;

        for (int start = 0; start < n; start += 2 * k) {
            int left = start;
            int right = Math.min(start + k - 1, n - 1);  // 处理边界情况

            // 交换字符
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return new String(arr);  // 将字符数组转换回字符串
    }
}
