class Solution {
    public boolean isAnagram(String s, String t) {
        int[] dic = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dic[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            dic[t.charAt(i) - 'a']--;
        }

        for (int i : dic) {
            if (i != 0) return false;
        }

        return true;
    }
}