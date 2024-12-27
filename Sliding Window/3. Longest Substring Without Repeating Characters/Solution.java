class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;

        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {
                // Math.max()确保 left 始终向右移动，避免回退 (搞不明白就試試abba)
                left = Math.max(map.get(s.charAt(right)) + 1, left);
            }
            // 覆盖之前 HashMap 中对应的值
            map.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }
}