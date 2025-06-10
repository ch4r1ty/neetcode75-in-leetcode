class Solution {
    public String reverseWords(String s) {
        StringBuilder res = removeSpace(s); // 去除多余空格
        reverseString(res, 0, res.length() - 1); // 整体反转字符串
        reverseEachWord(res); // 逐个单词反转
        return res.toString(); // 转成 String 返回
    }

    // 消除多余空格
    private StringBuilder removeSpace(String s) {
        char[] temp = s.toCharArray();
        int fast = 0;
        int slow = 0;
        // 先把最前面的空格消了，快指针先动
        while (temp[fast] == ' ') {
            fast++;
        }

        // 开始快慢指针
        while (fast < temp.length) {
            if (fast > 0 && temp[fast] == ' ' && temp[fast - 1] == ' ') {
                fast++;
                continue;
            }

            temp[slow] = temp[fast];
            fast++;
            slow++;
        }

        while (slow > 0 && temp[slow - 1] == ' ') {
            slow--;
        }

        return new StringBuilder(new String(temp, 0, slow));
    }

    // 反转整个string
    private void reverseString(StringBuilder s, int left, int right) {
        // int left = 0;
        // int right = s.length() - 1;
        char temp;
        while (left < right) {
            temp = s.charAt(left);
            s.setCharAt(left, s.charAt(right));
            s.setCharAt(right, temp);

            left++;
            right--;
        }
    }

    // 反转每个单词
    private void reverseEachWord(StringBuilder s) {
        int start = 0;
        int end = 0;
        while (start < s.length()) {
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }

            reverseString(s, start, end - 1);
            start = end + 1;
            end += 1;
        }
    }
}