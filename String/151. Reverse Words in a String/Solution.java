class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWord(sb);

        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        // cut zeros in the front and the end
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;

        // 如果當前位置和之後一個位置，不全爲空格，那麽就進行填充
        // 1. 如果當前位置是空格，之後那個不是，那也填充
        // 2. 只有連續兩個位置不是空格時，直接進行下一步：start++
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }

        return sb;
    }

    // 三個方法裏面最簡單的操作：反轉字符串（雙指針）
    public void reverseString(StringBuilder sb, int start, int end) {
        while (start <= end) {
            char temp = sb.charAt(start);
            // ❗這個操作看一下
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }
}
