class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            // 确保在 Map 中有一个对应键 sortedS 的 ArrayList 存在
            if (!res.containsKey(sortedS)) {
                res.put(sortedS, new ArrayList<>());
            }
            // 在对应键所在的ArrayList加入
            res.get(sortedS).add(s);
        }

        return new ArrayList<>(res.values());
    }
}
