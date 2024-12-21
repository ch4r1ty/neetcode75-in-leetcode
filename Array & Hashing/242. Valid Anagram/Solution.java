class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int hash[] = new int[26];
        int length = s.length();

        for (int i = 0; i < length; i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0) return false;
        }

        return true;
    }
}
