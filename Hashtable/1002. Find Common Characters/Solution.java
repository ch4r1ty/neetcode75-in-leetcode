class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A.length == 0) return res;
        int[] hash = new int[26];
        // 第一個單詞，給他的字母錄入哈希表進去
        for (int i = 0; i < A[0].length(); i++) {
            hash[A[0].charAt(i) - 'a']++;
        }
        // 第i個單詞，第j個字母
        for (int i = 1; i < A.length; i++) {
            // temp數組，用來放當前單詞的字母出現情況
            int[] hashOtherStr = new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                hashOtherStr[A[i].charAt(j) - 'a']++;
            }
            // 比較26個出現次數最小的字母
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k],hashOtherStr[k]);
            }
        }

        // 將26個字母表，轉換成List形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) {
                char c = (char) (i + 'a');
                res.add(String.valueOf(c));
                hash[i]--;
            }
        }

        return res;
    }
}