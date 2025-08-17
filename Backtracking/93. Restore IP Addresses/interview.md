class Solution {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        StringBuilder sb = new StringBuilder(s);
        backtracking(sb, 0, 0);
        return res;
    }

    private void backtracking(StringBuilder s, int startIndex, int dotCount) {
        if (dotCount == 3) {
            // 判断第四段子字符串是否合法，如果合法就放进result中
            if (isValid(s, startIndex, s.length() - 1)) {
                res.add(s.toString());
            }
            return;
        }

        // 判断 [startIndex,i] 这个区间的子串是否合法
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                // 在i的后面插入一个逗点
                s.insert(i + 1, '.');
                backtracking(s, i + 2, dotCount + 1);
                s.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(StringBuilder s, int start, int end) {
        if (start > end) return false;
        if (s.charAt(start) == '0' && start != end) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if (num > 255) return false;
        }
        return true;
    }
}