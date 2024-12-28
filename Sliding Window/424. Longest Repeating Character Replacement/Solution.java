class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }

        for (char c : set) {    // O(26)（最多26個字母）
            int count = 0;  // count 的作用是記錄當前滑動窗口內目標字符的出現次數
            int left = 0;
            for (int right = 0; right < s.length(); right++) {
                if (s.charAt(right) == c) {
                    count++;
                }

                // 如果當前窗口不符合要求，那麽count--
                while ((right - left + 1) - count > k) {
                    if (s.charAt(left) == c) {
                        count--;
                    }
                    left++;
                }

                res = Math.max(res, right - left + 1);
            }
        }

        return res;
    }
}
