class Solution {
    public int countPalindromicSubsequence(String s) {
        Set<Character> letters = new HashSet<>();
        for (Character c : s.toCharArray()) {
            letters.add(c);
        }

        int res = 0;
        for (Character letter : letters) {
            int i = -1;
            int j = 0;

            for (int k = 0; k < s.length(); k++) {
                if (s.charAt(k) == letter){
                    if (i == -1) {
                        i = k;  // i 表示字符 letter 在字符串中的首次出现位置
                    }

                    j = k;  // j 表示字符 letter 在字符串中的最后一次出现位置
                }
            }

            Set<Character> between = new HashSet();
            for (int k = i + 1; k < j; k++) {
                between.add(s.charAt(k));
            }

            res += between.size();
        }

        return res;
    }
}