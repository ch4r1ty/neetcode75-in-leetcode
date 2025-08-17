class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtracking(s, 0, new StringBuilder());
        return res;
    }

    private void backtracking(String s, int startIndex, StringBuilder sb) {
        // 因为是起始位置一个一个加的，所以结束时start一定等于s.length,
        // 因为进入backtracking时一定末尾也是回文，所以cur是满足条件的
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (check(sb)) {
                cur.add(sb.toString());
                backtracking(s, i + 1, new StringBuilder());
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean check(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) return false;
        }
        return true;
    }
}