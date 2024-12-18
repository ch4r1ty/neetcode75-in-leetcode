class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for (int num : nums) {
            if (hash.contains(num)) {
                return true;
            }
            hash.add(num);
        }
        return false;
    }
}
