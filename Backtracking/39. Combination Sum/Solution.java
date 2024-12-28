class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(res, new ArrayList<>(), nums, 0, target, 0);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] nums, int sum, int target, int index) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (sum > target) break;
            path.add(nums[i]);
            backtracking(res, path, nums, sum + nums[i], target, i);d
            path.remove(path.size() - 1);
        }
    }
}
