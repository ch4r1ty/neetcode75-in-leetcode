class Solution {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        // 這個edgecase是很關鍵的，不然digits.length() == 0 時，返回[""]而不是[]
        if (digits == null || digits.length() == 0) return res;
        backtracking(digits, 0);
        return res;
    }

    private void backtracking(String digits, int num) {
        if (num == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String level = map[digits.charAt(num) - '0'];
        for (int i = 0; i < level.length(); i++) {
            sb.append(level.charAt(i));
            num++;
            backtracking(digits, num);
            num--;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}